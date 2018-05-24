package com.tgw.account.refund.controller;

import com.tgw.account.refund.model.Refund;
import com.tgw.account.refund.service.RefundService;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.controller.BaseController;
import com.tgw.basic.framework.model.controller.SysEnController;
import com.tgw.basic.framework.model.controller.SysEnControllerField;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统摒弃还款功能模块。不再记录还款详情。
 * 简化借还款操作，在借款管理模块中将借还款操作简单化处理。
 */
@Controller
@RequestMapping("/refund")
public class RefundController extends BaseController<Refund> {

    @Resource
    private RefundService refundService;

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "RefundList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "refund/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "refund/" );//控制器的请求地址

        controller.setSearchConditionColNum( 2 );
    }

    @Override
    public void initSearch(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView, SysEnController controller, Refund bean ) throws PlatformException {
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
        if( null!=this.getRefundService() ){
            super.initService(  this.getRefundService()  );
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

        String hasRefundedJson = "[{name:'已还清',value:'1',eleId:'hasRefundedYes'},{name:'未还清',value:'0',eleId:'hasRefundedNo',checked:true}]";
        String hasRefundedGroupConfigs = "labelWidth:100,width:300";
        String hasRefundedConfigs = "width:60";
        String lendOrBorrowJson = "[{name:'借出',value:'1',eleId:'lendOrBorrow1',checked:true},{name:'借入',value:'0',eleId:'lendOrBorrow0'}]";
        String lendOrBorrowGroupConfigs = "labelWidth:100,width:300";
        String lendOrBorrowConfigs = "width:60";
        String userNameConfigs = "emptyText:'模糊查询'";
        String loginNameConfigs = "emptyText:'精确匹配'";

        SysEnControllerField refundDate = controller.addFieldDate("refundDate","还款日期",true,false,false,true,false,null);
        refundDate.setSearByRange( true );
        controller.addFieldNumber("refundSum","还款金额",true,false,false,false,false,null);
        controller.addFieldNumber("hasRefundedData","已还总额",true,false,false,false,true,null);
        controller.addFieldNumber("lendSum","借款总额",true,false,false,false,false,null);
        controller.addFieldRadioInitDataByJson("hasRefunded","是否已还清",true,false,false,false,false,hasRefundedJson,hasRefundedGroupConfigs,hasRefundedConfigs);
        controller.addFieldText("lendPerson","借款人",true,false,false,false,false,null);
        controller.addFieldRadioInitDataByJson("lendOrBorrow","类型",true,false,false,false,false,lendOrBorrowJson,lendOrBorrowGroupConfigs,lendOrBorrowConfigs);
        controller.addFieldText("lendPurpose","用途目的",true,false,false,false,false,null);
        controller.addFieldDate("planRefundDate","计划还款日期",true,false,false,false,false,null);
        controller.addFieldTextArea("remark","备注",true,false,false,false,false,null);
        if( PlatformUserUtils.isContainRoleByCode( "superAdmin" ) ) {//是超级管理员角色
            controller.addFieldText("userName", "用户名", true, false, false, true, true, userNameConfigs);
            controller.addFieldText("loginName", "登录名", true, false, false, true, true, loginNameConfigs);
        }
        controller.addFieldDatetime("addTime","添加时间",true,false,false,false,false,null);
        controller.addFieldDatetime("updateTime","更新时间",true,false,false,false,false,null);
    }

    @Override
    public void initFunction(SysEnController controller) throws PlatformException {

    }

    @Override
    public void beforeSearchData(HttpServletRequest request, HttpServletResponse response, Refund bean) throws PlatformException{
        if( !PlatformUserUtils.isContainRoleByCode( "superAdmin" ) ){//非超级管理员角色,只查询当前用户的数据
            bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        }
    }


    @Override
    public void beforeSaveBean(HttpServletRequest request, HttpServletResponse response, Refund bean) throws PlatformException{
        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );

        Date date = new Date();
        bean.setAddTime( date );
        bean.setUpdateTime( date );
    }

    @Override
    public void beforeUpdateBean(HttpServletRequest request, HttpServletResponse response,Object bean  ) throws PlatformException{
        Refund tempBean = (Refund)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void beforeMenuAjaxUpdateBean(HttpServletRequest request, HttpServletResponse response, Object bean  ) throws PlatformException{
        Refund tempBean = (Refund)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public List<Map<String,Object>> loadComboBoxDataMap(HttpServletRequest request, HttpServletResponse response, Refund bean, String parentId) throws PlatformException{
        /**
        * 查询下拉框数据
        *
        * Controller中所有获取下拉框数据的方法都在此方法中实现
        */
        String comboBoxFlag = request.getParameter("comboBoxFlag");
        List<Map<String,Object>> res = null;

        /**if( "loadDistrict".equals( comboBoxFlag ) ){
            res = this.getRefundService().queryDistrictComboBoxMap( parentId );
        }*/

        return res;
    }

    @Override
    public List<Map<String,Object>> loadTreeNodeDataMap(HttpServletRequest request, HttpServletResponse response, Refund bean) throws PlatformException{
        /**
        * 加载树结点需要的数据
        *
        * Controller中所有获取树节点数据的方法都在此方法中实现。
        * 除非自定义了url
        */
        String treeFlag = request.getParameter("treeFlag");
        List<Map<String,Object>> res = null;

        /**if( "district".equals( treeFlag ) ){//行政区划树
            res = getRefundService().queryDistrictTreeMap();
        }*/

        return res;
    }

    public RefundService getRefundService() {
        return refundService;
    }

    public void setRefundService(RefundService refundService) {
        this.refundService = refundService;
    }
}
