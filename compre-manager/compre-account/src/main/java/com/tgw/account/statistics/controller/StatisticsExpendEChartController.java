package com.tgw.account.statistics.controller;

import com.tgw.account.expend.model.Expend;
import com.tgw.account.expend.service.ExpendService;
import com.tgw.account.expendType.model.ExpendType;
import com.tgw.account.expendType.service.ExpendTypeService;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.common.utils.PlatformUtils;
import com.tgw.basic.common.utils.config.PlatformSysConstant;
import com.tgw.basic.framework.controller.BaseController;
import com.tgw.basic.framework.model.constant.FrameworkConstant;
import com.tgw.basic.framework.model.controller.SysEnController;
import com.tgw.basic.framework.model.controller.SysEnControllerField;
import com.tgw.basic.framework.model.form.field.SysEnFieldDate;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import com.tgw.omnipotent.comEvent.service.ComEventService;
import com.tgw.omnipotent.comPerson.service.ComPersonService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhaojg on 2017/03/25
 */
@Controller
@RequestMapping("/statisticsExpendEChart")
public class StatisticsExpendEChartController extends BaseController<Expend> {
    @Resource
    private ExpendTypeService expendTypeService;
    @Resource
    private ExpendService expendService;
    @Resource
    private ComEventService comEventService;
    @Resource
    private ComPersonService comPersonService;

    @Override
    public void initControllerBaseInfoSearchModel(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "StatisticsExpendEChart" );// 每一个列表页面的唯一身份id
        controller.setControllerBaseUrl( "statisticsExpendEChart/" );//控制器的请求地址
        //设置搜索区域字段列数。不设置时使用系统默认值。
        controller.setSearchConditionColNum( 3 );
    }

    @Override
    public void initSearchModel(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView, SysEnController controller, Expend bean) throws PlatformException {
        controller.addJsFileNameUserDefinePath( "page/manage/compreAccount/statistics/js/statisticsExpendEchart.js" );

        modelAndView.addObject(FrameworkConstant.FW_SEARCH_MODEL_SEARCH_CALLBACK_FUNCTION_NAME,"statisticsExpendEChartCallback");
        modelAndView.addObject(FrameworkConstant.FW_SEARCH_MODEL_FREE_AREA_ELEMENT_ID,"statisticsExpendEChartShowResEle");
        modelAndView.addObject(FrameworkConstant.FW_SEARCH_MODEL_SEARCH_URL,"statisticsExpendEChart/expendEChartData.do");
    }

    @Override
    public void initFieldSearchModel( SysEnController controller ) throws PlatformException {
        boolean superAdminFlag = PlatformUserUtils.isContainRoleByCode( PlatformSysConstant.SYS_ROLE_CODE_SUPER_ADMIN )?true:false;

        SimpleDateFormat sdf = new SimpleDateFormat( PlatformSysConstant.DATE_FORMAT_JAVA_YMD );
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.add( Calendar.MONTH,-11 );
        startCalendar.set( Calendar.DAY_OF_MONTH,1);

        String expendTypeSearConfigs = "emptyText:'请选择支出类型',multiSelect:true,multiCascade:false";
        String expendTypeTreeSearUrl = "expendType/loadTreeData.do?fieldMap=id:id,text:expend_type_name,parentId:fk_parent_id&treeRootVal=-1&treeFlag=expendType&resType=map&multiSelect=true";
        String expDateConfigs = "emptyText:'支出日期',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01'";
        String fkComEventIdConfigs = "listConfig:{emptyText:'相关事件'}";
        String fkComPersonConfigs = "listConfig:{emptyText:'相关人'}";

        controller.addFieldComboBoxTree( "typeIds","支出类型",true,false,false,!superAdminFlag,false,expendTypeSearConfigs,expendTypeTreeSearUrl ).setShowList( false );
        SysEnControllerField expDate = controller.addFieldDate("expDate","支出日期",true,true,true,true,false,expDateConfigs);
        expDate.setSearByRange( true );
        SysEnFieldDate fieldDate = (SysEnFieldDate)expDate.getSysEnFieldAttr();
        fieldDate.setStartTimeForRange( sdf.format( startCalendar.getTime() ) );
        fieldDate.setEndTimeForRange( sdf.format( new Date() ) );
        controller.addFieldComboBoxBySQL("fkComEventId","相关事件",true,true,true,!superAdminFlag,true,"loadComEvent",null,fkComEventIdConfigs);
        controller.addFieldComboBoxBySQL("fkComPersonId","相关人员",true,true,true,!superAdminFlag,true,"loadComPerson",null,fkComPersonConfigs);

    }

    @RequestMapping("/expendEChartData.do")
    public ModelAndView expendEChartData(Expend bean){
        ModelAndView modelAndView = new ModelAndView();
        JSONObject jo = JSONObject.fromObject("{}");

        try {
            //处理查询条件
            if ( bean.getExpDateStart()==null || bean.getExpDateEnd()==null ){
                throw new PlatformException("统计开始、结束时间不能为空！");
            }
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM" );
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime( sdf.parse( sdf.format( bean.getExpDateStart() ) ) );
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime( sdf.parse( sdf.format( bean.getExpDateEnd() ) ) );

            if(StringUtils.isNotBlank( bean.getTypeIds() )){
                bean.setIdList( PlatformUtils.stringToList( bean.getTypeIds(),"," ) );
            }
            bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );

            //要统计的类型
            List<ExpendType> statisticTypeList = getExpendTypeService().queryStatisticsExpendType( bean );
            if( statisticTypeList==null || statisticTypeList.isEmpty() ){
                throw new PlatformException("没有可以统计的类型！");
            }

            //统计图的图例
            JSONArray legendDataJA = new JSONArray();
            //统计图的横轴项目
            JSONArray xAxisDataJA = new JSONArray();
            //统计图数据
            JSONArray seriesJA = new JSONArray();

            //处理图例
            for( ExpendType expendType:statisticTypeList ){
                legendDataJA.add( expendType.getExpendTypeName() );
            }
            //处理横轴数据项
            while ( !startCalendar.after( endCalendar ) ){
                xAxisDataJA.add( sdf.format( startCalendar.getTime() ) );
                startCalendar.add( Calendar.MONTH,1 );
            }
            //循环完后恢复初始化开始时间
            startCalendar.setTime( sdf.parse( sdf.format( bean.getExpDateStart() ) ) );

            //查询统计结果
            List<Map<String,Object>> res = getExpendService().queryStatisticsExpendData( bean );
            //统计数据结果转换
            Map<String,Object> resMap = new HashMap<String, Object>();
            if( res!=null ){
                for(Map<String,Object> map : res ){
                    resMap.put( map.get("f_date").toString()+"--"+map.get("t_id").toString() ,map.get("sta_sum"));
                }
            }

            BigDecimal count = new BigDecimal( 0 );//所有类型总额，饼状图使用
            JSONArray seriesDataPieJA = new JSONArray();//各类型金额总额，饼状图使用
            //遍历整理查询结果
            for( ExpendType expendType:statisticTypeList ){
                JSONObject seriesSingleJO = new JSONObject();
                seriesSingleJO.put("name",expendType.getExpendTypeName());

                //单个类型总金额，饼状图使用
                BigDecimal receTypeCount = new BigDecimal( 0 );

                JSONArray seriesSingleJA = new JSONArray();
                while ( !startCalendar.after( endCalendar ) ){
                    Object tempObj = resMap.get( sdf.format( startCalendar.getTime() )+"--"+expendType.getId() );
                    if( tempObj!=null ){
                        seriesSingleJA.add( tempObj.toString() );

                        //单个类型单个时间段总金额，饼状图使用
                        BigDecimal receTypeNumGroupByDate = new BigDecimal( Double.parseDouble( tempObj.toString() ) );
                        receTypeCount = receTypeCount.add( receTypeNumGroupByDate );

                        count = count.add( receTypeNumGroupByDate  );
                    }else{
                        seriesSingleJA.add( "0" );
                    }
                    startCalendar.add( Calendar.MONTH,1 );
                }
                //循环完后恢复初始化开始时间
                startCalendar.setTime( sdf.parse( sdf.format( bean.getExpDateStart() ) ) );
                seriesSingleJO.put("data",seriesSingleJA);

                seriesJA.add( seriesSingleJO );

                //饼状图使用
                JSONObject seriesDataPie = new JSONObject();
                seriesDataPie.put("name",expendType.getExpendTypeName());
                seriesDataPie.put("value", receTypeCount.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                seriesDataPieJA.add( seriesDataPie );
            }

            JSONObject echartJO = new JSONObject();
            echartJO.put("echart_legendData",legendDataJA);
            echartJO.put("echart_xAxisData",xAxisDataJA);
            echartJO.put("echart_seriesData",seriesJA);
            echartJO.put("echart_titleText","统计图");
            echartJO.put("echart_seriesDataPie",seriesDataPieJA );
            echartJO.put("echart_count",count.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() );
            jo.put( "echartInfo",echartJO );

            jo.put("success",true);
            jo.put("msg","查询统计完成！");
        }catch (PlatformException e){
            jo.put("success",false);
            jo.put("msg",e.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            jo.put("success",false);
            jo.put("msg","发生异常！");
        }

        modelAndView.addObject( PlatformSysConstant.JSONSTR, jo.toString() );
        modelAndView.setViewName( this.getJsonView() );

        return  modelAndView;
    }

    @Override
    public List<Map<String,Object>> loadComboBoxDataMap(HttpServletRequest request, HttpServletResponse response, Expend bean, String parentId) throws PlatformException{
        /**
         * 查询下拉框数据
         *
         * Controller中所有获取下拉框数据的方法都在此方法中实现
         */
        String comboBoxFlag = request.getParameter("comboBoxFlag");
        List<Map<String,Object>> res = null;

        if( "loadComEvent".equals( comboBoxFlag ) ){
            res = this.getComEventService().loadComEventComboBoxMap();
        }else if( "loadComPerson".equals( comboBoxFlag ) ){
            res = this.getComPersonService().loadComPersonComboBoxMap();
        }

        return res;
    }

    public ExpendTypeService getExpendTypeService() {
        return expendTypeService;
    }

    public void setExpendTypeService(ExpendTypeService expendTypeService) {
        this.expendTypeService = expendTypeService;
    }

    public ExpendService getExpendService() {
        return expendService;
    }

    public void setExpendService(ExpendService expendService) {
        this.expendService = expendService;
    }

    public ComPersonService getComPersonService() {
        return comPersonService;
    }

    public void setComPersonService(ComPersonService comPersonService) {
        this.comPersonService = comPersonService;
    }

    public ComEventService getComEventService() {
        return comEventService;
    }

    public void setComEventService(ComEventService comEventService) {
        this.comEventService = comEventService;
    }
}
