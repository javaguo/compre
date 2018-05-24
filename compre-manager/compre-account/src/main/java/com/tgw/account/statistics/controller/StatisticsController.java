package com.tgw.account.statistics.controller;

import com.tgw.account.statistics.model.Statistics;
import com.tgw.account.statistics.service.StatisticsService;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.controller.BaseController;
import com.tgw.basic.framework.model.controller.SysEnController;
import com.tgw.basic.framework.model.controller.SysEnControllerField;
import com.tgw.basic.framework.model.form.field.SysEnFieldDate;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/statistics")
public class StatisticsController extends BaseController<Statistics> {

    @Resource
    private StatisticsService statisticsService;

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "StatisticsList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "statistics/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "statistics/" );//控制器的请求地址

        controller.setSearchConditionColNum( 2 );
    }

    @Override
    public void initSearch(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView, SysEnController controller, Statistics bean ) throws PlatformException {
        /**
        * 将具体的业务的service对象赋值给baseservice，必须的操作。
        * service层需要将具体业务的mapper赋值给BaseModelMapper
        *
        * 此操作主要解决的问题是BaseModelMapper无法注入到BaseServiceImpl中的问题。手动赋值。
        *
        * 要点：
        * 1.BaseController会调用统一的searchData()接口查询具体的业务数据。
        * 2.具体业务的mapper文件中实现searchData查询语句
        */
        if( null!=this.getStatisticsService() ){
            super.initService(  this.getStatisticsService()  );
        }else{

        }

    }

    @Override
    public void initField( SysEnController controller ) throws PlatformException {
        /**
        * 注意事项：
        * 1.定义的变量中不要包含SavePathHidden。SavePathHidden被框架使用。用来存储上传附件的路径。
        */
        //构造字段
        controller.addFieldId("id","ID",null);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfYM = new SimpleDateFormat("yyyy-MM");
        Date currDate = new Date();
        String statiDate1Configs = "emptyText:'收支日期1',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01',value:'"+ sdf.format( new Date() )+"'";
        String statiDate2Configs = "emptyText:'收支日期2',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01',value:'"+ sdf.format( new Date() )+"'";
        String statiDate3Configs = "emptyText:'借款日期',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01',value:'"+ sdf.format( new Date() )+"'";
        SysEnControllerField statiDate1Field = controller.addFieldDate("statiDate1","收支日期1",true,false,false,false,true,true,statiDate1Configs,null);
        statiDate1Field.setSearByRange( true );
        SysEnFieldDate statiDate1FieldAttr = (SysEnFieldDate)statiDate1Field.getSysEnFieldAttr();
        statiDate1FieldAttr.setStartTimeForRange( sdfY.format(currDate)+"-01-01" );//默认当年
        statiDate1FieldAttr.setEndTimeForRange( sdf.format( currDate ) );

        SysEnControllerField statiDate2Field = controller.addFieldDate("statiDate2","收支日期2",true,false,false,false,true,true,statiDate2Configs,null);
        statiDate2Field.setSearByRange( true );
        SysEnFieldDate statiDate2FieldAttr = (SysEnFieldDate)statiDate2Field.getSysEnFieldAttr();
        statiDate2FieldAttr.setStartTimeForRange( sdfYM.format(currDate)+"-01" );//默认当月
        statiDate2FieldAttr.setEndTimeForRange( sdf.format( currDate ) );

        SysEnControllerField statiDate3Field = controller.addFieldDate("statiDate3","借款日期",true,false,false,false,true,true,statiDate3Configs,null);
        statiDate3Field.setSearByRange( true );
        SysEnFieldDate statiDate3FieldAttr = (SysEnFieldDate)statiDate3Field.getSysEnFieldAttr();
        statiDate3FieldAttr.setStartTimeForRange( sdfY.format(currDate)+"-01-01" );//默认当年
        statiDate3FieldAttr.setEndTimeForRange( sdf.format( currDate ) );

        controller.addFieldTextArea("startTime","开始日期",true,false,false,false,true,null);
        controller.addFieldTextArea("endTime","结束日期",true,false,false,false,true,null);
        controller.addFieldTextArea("title1","项目1",true,false,false,false,true,null);
        controller.addFieldTextArea("value1","值",true,false,false,false,true,null);
        controller.addFieldTextArea("title2","项目2",true,false,false,false,true,null);
        controller.addFieldTextArea("value2","值",true,false,false,false,true,null);
        controller.addFieldTextArea("title2A","差额",true,false,false,false,true,null);
        controller.addFieldTextArea("value2A","值(项目1-项目2)",true,false,false,false,true,null);
        controller.addFieldTextArea("title3","项目3",true,false,false,false,true,null);
        controller.addFieldTextArea("value3","值",true,false,false,false,true,null);
        controller.addFieldTextArea("title4","项目4",true,false,false,false,true,null);
        controller.addFieldTextArea("value4","值",true,false,false,false,true,null);
    }

    @Override
    public void beforeSearchData(HttpServletRequest request, HttpServletResponse response, Statistics bean) throws PlatformException{
        if( !PlatformUserUtils.isContainRoleByCode( "superAdmin" ) ){//非超级管理员角色,只查询当前用户的数据
            bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        }
    }

    @Override
    public List dealListQueryResult(HttpServletRequest request, HttpServletResponse response, Statistics bean, List dataList) throws PlatformException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for( int i=0;i<dataList.size();i++ ){
            HashMap<String,Object> map = (HashMap<String,Object>)dataList.get(i);

            if( i==1 ){
                map.put("startTime",sdf.format( bean.getStatiDate1Start() ) );
                map.put("endTime",sdf.format( bean.getStatiDate1End() ) );
            }else if( i==2 ){
                map.put("startTime",sdf.format( bean.getStatiDate2Start() ) );
                map.put("endTime",sdf.format( bean.getStatiDate2End() ) );
            }else if( i==4 ){
                map.put("startTime",sdf.format( bean.getStatiDate3Start() ) );
                map.put("endTime",sdf.format( bean.getStatiDate3End() ) );
            }

            String d1 = map.get("value1")!=null?map.get("value1").toString():"";
            String d2 = map.get("value2")!=null?map.get("value2").toString():"";
            if( StringUtils.isNotBlank( d1 ) && StringUtils.isNotBlank( d2 ) ){
                BigDecimal bd1 = new BigDecimal( Double.parseDouble( d1 ) );
                BigDecimal bd2 = new BigDecimal( Double.parseDouble( d2 ) );
                map.put("value2A",  bd1.subtract( bd2 ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()  );
            }

            dataList.set(i,map);
        }
        return dataList;
    }

    @Override
    public List<Map<String,Object>> loadComboBoxDataMap(HttpServletRequest request, HttpServletResponse response, Statistics bean, String parentId) throws PlatformException{
        /**
        * 查询下拉框数据
        *
        * Controller中所有获取下拉框数据的方法都在此方法中实现
        */
        String comboBoxFlag = request.getParameter("comboBoxFlag");
        List<Map<String,Object>> res = null;

        /**if( "loadDistrict".equals( comboBoxFlag ) ){
            res = this.getReceiptsTypeSysService().queryDistrictComboBoxMap( parentId );
        }*/

        return res;
    }

    @Override
    public List<Map<String,Object>> loadTreeNodeDataMap(HttpServletRequest request, HttpServletResponse response, Statistics bean) throws PlatformException{
        /**
        * 加载树结点需要的数据
        *
        * Controller中所有获取树节点数据的方法都在此方法中实现。
        * 除非自定义了url
        */
        String treeFlag = request.getParameter("treeFlag");
        List<Map<String,Object>> res = null;

        /*if( "receiptsTypeSys".equals( treeFlag ) ){

        }else{
            throw new PlatformException("获取树节点信息时没有找到匹配的查询方法！");
        }*/

        return res;
    }

    public StatisticsService getStatisticsService() {
        return statisticsService;
    }

    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
}
