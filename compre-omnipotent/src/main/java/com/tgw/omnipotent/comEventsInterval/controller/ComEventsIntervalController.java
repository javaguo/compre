package com.tgw.omnipotent.comEventsInterval.controller;

import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.common.utils.config.PlatformSysConstant;
import com.tgw.basic.framework.controller.BaseController;
import com.tgw.basic.framework.model.controller.SysEnController;
import com.tgw.basic.framework.model.controller.SysEnControllerField;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import com.tgw.omnipotent.comEventsInterval.model.ComEventsInterval;
import com.tgw.omnipotent.comEventsInterval.service.ComEventsIntervalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comEventsInterval")
public class ComEventsIntervalController extends BaseController<ComEventsInterval> {

    @Resource
    private ComEventsIntervalService comEventsIntervalService;

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "ComEventsIntervalList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "comEventsInterval/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "comEventsInterval/" );//控制器的请求地址

        controller.setPageSize( 1000 );
        controller.setSearchConditionColNum( 2 );
    }


    @Override
    public void initSearch(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView, SysEnController controller, ComEventsInterval bean ) throws PlatformException {
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
        if( null!=this.getComEventsIntervalService() ){
            super.initService(  this.getComEventsIntervalService()  );
        }else{

        }

        /**
        *此处的配置会覆盖jsp页面中默认的配置。
        * 此处配置也可以写在   initControllerBaseInfo()或initField()或initFunction()方法中
        */
        //String addWindowConfigs = "title: '添加窗口',width:800";
        //String editWindowConfigs = "title: '编辑窗口',width:800";
        //String viewWindowConfigs = "title: '查看详情窗口',width:600";
        //controller.addWindowConfig( addWindowConfigs,editWindowConfigs,viewWindowConfigs );
    }

    @Override
    public void initField( SysEnController controller ) throws PlatformException {
        /**
        * 注意事项：
        * 1.定义的变量中不要包含SavePathHidden。SavePathHidden被框架使用。用来存储上传附件的路径。
        */
        //构造字段
        controller.addFieldId("id","ID",null);

        String fkConstantIdConfigs = "labelWidth:100,width:250,listConfig:{emptyText:'事件类型'}";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTimeConfigs = "emptyText:'开始日期',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01',value:'"+ sdf.format( new Date() )+"'";
        String endTimeConfigs = "emptyText:'结束日期',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01',value:'"+ sdf.format( new Date() )+"'";
        String remarkConfigs = "width:400,height:80,maxLength:200,maxLengthText:'最多输入200个字符'";
        String userNameConfigs = "emptyText:'模糊查询'";
        String loginNameConfigs = "emptyText:'精确匹配'";

        controller.addFieldComboBoxBySQL("fkConstantId","事件类型",true,true,true,true,false,"eventInterval",null,fkConstantIdConfigs);
//        controller.addFieldComboBoxBySQLConstant("fkConstantId","事件类型",true,true,true,true,false,"eventInterval",null,fkConstantIdConfigs);
        SysEnControllerField startTime = controller.addFieldDate("startTime","开始日期",true,true,true,true,false,startTimeConfigs);
        startTime.setSearByRange( true );
        SysEnControllerField endTime = controller.addFieldDate("endTime","结束日期",true,true,true,true,true,endTimeConfigs);
        endTime.setSearByRange( true );
        controller.addFieldTextArea("daysStartToStart","距上次开始天数",true,false,false,false,true,null);
        controller.addFieldTextArea("daysEndToStart","距上次结束天数",true,false,false,false,true,null);

        controller.addFieldTextArea("remark","备注",true,true,true,false,true,remarkConfigs);
        if( PlatformUserUtils.isContainRoleByCode( PlatformSysConstant.SYS_ROLE_CODE_SUPER_ADMIN ) ){//是超级管理员角色
            controller.addFieldText("userName","用户名",true,false,false,true,true,userNameConfigs);
            controller.addFieldText("loginName","登录名",true,false,false,true,true,loginNameConfigs);
        }

        controller.addFieldDatetime("addTime","添加时间",true,false,false,false,false,null);
        controller.addFieldDatetime("updateTime","更新时间",true,false,false,false,false,null);
    }

    @Override
    public void initFunction(SysEnController controller) throws PlatformException {

    }

    @Override
    public void beforeSearchData(HttpServletRequest request, HttpServletResponse response, ComEventsInterval bean) throws PlatformException{
        if( !PlatformUserUtils.isContainRoleByCode( PlatformSysConstant.SYS_ROLE_CODE_SUPER_ADMIN ) ){//非超级管理员角色,只查询当前用户的数据
            bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        }
    }

    @Override
    public List dealListQueryResult(HttpServletRequest request, HttpServletResponse response, ComEventsInterval bean, List dataList) throws PlatformException{
        Date preRecStartDate = null;//上条记录的开始日期
        Date preRecEndDate = null;//上条记录的结束日期

        String preConstantFlag=null;//上条记录的类型id
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for( int i=0;i<dataList.size();i++ ){
            HashMap<String,Object> map = (HashMap<String,Object>)dataList.get(i);
            String currConstantFlag = map.get("constantId")!=null?map.get("constantId").toString():"";

            if( i>0 && StringUtils.isNotBlank( preConstantFlag ) && preConstantFlag.equals( currConstantFlag ) ){

                String currRecStartDateStr = map.get("startTime")!=null?map.get("startTime").toString():"";
                Date currRecStartDate = null;//本条记录开始日期
                try {
                    currRecStartDate = StringUtils.isNotBlank( currRecStartDateStr )?sdf.parse( currRecStartDateStr ):null;
                }catch( Exception e ){

                }

                if( currRecStartDate!=null ){
                    long toStart = currRecStartDate.getTime();//本次开始的毫秒数

                    if( preRecStartDate!=null ){
                        long fromStart = preRecStartDate.getTime();//上次开始的毫秒数
                        int daysStartToStart =  (int)( (toStart-fromStart)/(1000*60*60*24) );//本次开始与上次开始间隔天数

                        map.put("daysStartToStart", daysStartToStart );
                    }

                    if( preRecEndDate!=null ){
                        long fromEnd = preRecEndDate.getTime();//上次结束的毫秒数
                        int daysEndToStart =  (int)( (toStart-fromEnd)/(1000*60*60*24) );//本次开始与上次结束间隔天数

                        map.put("daysEndToStart", daysEndToStart );
                    }

                }

            }

            String preRecStartDateStr = map.get("startTime")!=null?map.get("startTime").toString():"";
            String preRecEndDateStr = map.get("endTime")!=null?map.get("endTime").toString():"";
            try {
                preRecStartDate = StringUtils.isNotBlank( preRecStartDateStr )?sdf.parse( preRecStartDateStr ):null;
                preRecEndDate = StringUtils.isNotBlank( preRecEndDateStr )?sdf.parse( preRecEndDateStr ):null;
            }catch (Exception e){

            }
            preConstantFlag = map.get("constantId")!=null?map.get("constantId").toString():"";

            dataList.set(i,map);
        }
        return dataList;
    }

    @Override
    public void beforeSaveBean(HttpServletRequest request, HttpServletResponse response, ComEventsInterval bean) throws PlatformException{
        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );

        Date date = new Date();
        bean.setAddTime( date );
        bean.setUpdateTime( date );
    }

    @Override
    public void beforeUpdateBean(HttpServletRequest request, HttpServletResponse response,Object bean  ) throws PlatformException{
        ComEventsInterval tempBean = (ComEventsInterval)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void beforeMenuAjaxUpdateBean(HttpServletRequest request, HttpServletResponse response, Object bean  ) throws PlatformException{
        ComEventsInterval tempBean = (ComEventsInterval)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public List<Map<String,Object>> loadComboBoxDataMap(HttpServletRequest request, HttpServletResponse response, ComEventsInterval bean, String parentId) throws PlatformException{
        /**
        * 查询下拉框数据
        *
        * Controller中所有获取下拉框数据的方法都在此方法中实现
        */
        String comboBoxFlag = request.getParameter("comboBoxFlag");
        List<Map<String,Object>> res = null;

        if( "eventInterval".equals( comboBoxFlag ) ){
            res = this.getComEventsIntervalService().loadEventIntervalComboBoxMap();
        }

        return res;
    }

    @Override
    public List<Map<String,Object>> loadTreeNodeDataMap(HttpServletRequest request, HttpServletResponse response, ComEventsInterval bean) throws PlatformException{
        /**
        * 加载树结点需要的数据
        *
        * Controller中所有获取树节点数据的方法都在此方法中实现。
        * 除非自定义了url
        */
        String treeFlag = request.getParameter("treeFlag");
        List<Map<String,Object>> res = null;

        if( "treeFlag".equals( treeFlag ) ){
            //res = getComEventsIntervalService().queryDistrictTreeMap();
        }else{
            throw new PlatformException("获取树节点信息时没有找到匹配的查询方法！");
        }

        return res;
    }

    public ComEventsIntervalService getComEventsIntervalService() {
        return comEventsIntervalService;
    }

    public void setComEventsIntervalService(ComEventsIntervalService comEventsIntervalService) {
        this.comEventsIntervalService = comEventsIntervalService;
    }
}
