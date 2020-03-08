package com.tgw.omnipotent.comEvent.controller;

import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.common.utils.config.PlatformSysConstant;
import com.tgw.basic.framework.controller.BaseController;
import com.tgw.basic.framework.model.controller.SysEnController;
import com.tgw.basic.framework.model.controller.SysEnControllerField;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import com.tgw.omnipotent.comEvent.model.ComEvent;
import com.tgw.omnipotent.comEvent.service.ComEventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comEvent")
public class ComEventController extends BaseController<ComEvent> {

    @Resource
    private ComEventService comEventService;

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "ComEventList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "comEvent/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "comEvent/" );//控制器的请求地址

        controller.setSearchConditionColNum( 2 );
    }

    @Override
    public void initSearch(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView, SysEnController controller, ComEvent bean ) throws PlatformException {
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
        if( null!=this.getComEventService() ){
            super.initService(  this.getComEventService()  );
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTimeConfigs = "emptyText:'开始日期',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01',value:'"+ sdf.format( new Date() )+"'";
        String endTimeConfigs = "emptyText:'结束日期',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01',value:'"+ sdf.format( new Date() )+"'";
        String titleConfigs = "maxLength:100,maxLengthText:'最多输入100个字符'";
        String descriptionConfigs = "width:400,height:150,maxLength:1000,maxLengthText:'最多输入1000个字符'";
        String remarkConfigs = "width:400,height:80,maxLength:200,maxLengthText:'最多输入200个字符'";
        String userNameConfigs = "emptyText:'模糊查询'";
        String loginNameConfigs = "emptyText:'精确匹配'";

        SysEnControllerField startTime = controller.addFieldDate("startTime","开始日期",true,true,true,true,false,startTimeConfigs);
        startTime.setSearByRange( true );
        SysEnControllerField endTime = controller.addFieldDate("endTime","结束日期",true,true,true,true,false,endTimeConfigs);
        endTime.setSearByRange( true );
        controller.addFieldText("title","事件名称",true,true,true,true,false,titleConfigs);
        controller.addFieldTextArea("description","描述",true,true,true,false,true,descriptionConfigs);
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
    public void beforeSearchData(HttpServletRequest request, HttpServletResponse response, ComEvent bean) throws PlatformException{
        if( !PlatformUserUtils.isContainRoleByCode( PlatformSysConstant.SYS_ROLE_CODE_SUPER_ADMIN ) ){//非超级管理员角色,只查询当前用户的数据
            bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        }
    }

    @Override
    public void beforeSaveBean(HttpServletRequest request, HttpServletResponse response, ComEvent bean) throws PlatformException{
        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );

        Date date = new Date();
        bean.setAddTime( date );
        bean.setUpdateTime( date );
    }

    @Override
    public void beforeUpdateBean(HttpServletRequest request, HttpServletResponse response,Object bean  ) throws PlatformException{
        ComEvent tempBean = (ComEvent)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void beforeMenuAjaxUpdateBean(HttpServletRequest request, HttpServletResponse response, Object bean  ) throws PlatformException{
        ComEvent tempBean = (ComEvent)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public List<Map<String,Object>> loadComboBoxDataMap(HttpServletRequest request, HttpServletResponse response, ComEvent bean, String parentId) throws PlatformException{
        /**
        * 查询下拉框数据
        *
        * Controller中所有获取下拉框数据的方法都在此方法中实现
        */
        String comboBoxFlag = request.getParameter("comboBoxFlag");
        List<Map<String,Object>> res = null;

        /**if( "loadDistrict".equals( comboBoxFlag ) ){
            res = this.getComEventService().queryDistrictComboBoxMap( parentId );
        }*/

        return res;
    }

    @Override
    public List<Map<String,Object>> loadTreeNodeDataMap(HttpServletRequest request, HttpServletResponse response, ComEvent bean) throws PlatformException{
        /**
        * 加载树结点需要的数据
        *
        * Controller中所有获取树节点数据的方法都在此方法中实现。
        * 除非自定义了url
        */
        String treeFlag = request.getParameter("treeFlag");
        List<Map<String,Object>> res = null;

        if( "treeFlag".equals( treeFlag ) ){
            //res = getComEventService().queryDistrictTreeMap();
        }else{
            throw new PlatformException("获取树节点信息时没有找到匹配的查询方法！");
        }

        return res;
    }

    public ComEventService getComEventService() {
        return comEventService;
    }

    public void setComEventService(ComEventService comEventService) {
        this.comEventService = comEventService;
    }
}
