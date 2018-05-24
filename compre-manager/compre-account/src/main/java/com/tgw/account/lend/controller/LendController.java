package com.tgw.account.lend.controller;

import com.tgw.account.lend.model.Lend;
import com.tgw.account.lend.service.LendService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lend")
public class LendController extends BaseController<Lend> {

    @Resource
    private LendService lendService;

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "LendList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "lend/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "lend/" );//控制器的请求地址

        controller.setSearchConditionColNum(2);
    }

    @Override
    public void initSearch(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView, SysEnController controller, Lend bean ) throws PlatformException {
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
        if( null!=this.getLendService() ){
            super.initService(  this.getLendService()  );
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
        String lendDateConfigs = "emptyText:'借款日期',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01',value:'"+ sdf.format( new Date() )+"'";
        String lendOrBorrowJson = "[{name:'借出',value:'1',eleId:'lendOrBorrow1',checked:true},{name:'借入',value:'0',eleId:'lendOrBorrow0'}]";
        String lendOrBorrowGroupConfigs = "labelWidth:100,width:300";
        String lendOrBorrowConfigs = "width:60";
        String lendPersonConfigs = "maxLength:10,maxLengthText:'最多输入10个字符'";
        String lendSumConfigs = "emptyText:'借款金额',maxValue:99999999.9,maxText:'最大为999,999,99.9',minValue:0.1,minText:'最小为0.1',step:100";
        String hasRefundedDataConfigs = "emptyText:'已还金额',maxValue:99999999.9,maxText:'最大为999,999,99.9',minValue:0.1,minText:'最小为0.1',step:100";
        String lendPurposeConfigs = "maxLength:50,maxLengthText:'最多输入50个字符'";
        String planRefundDateConfigs = "emptyText:'计划还款日期',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01'";
        String refundDateConfigs = "emptyText:'实际还款日期',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01'";
        String hasRefundedJson = "[{name:'已还清',value:'1',eleId:'hasRefundedYes'},{name:'未还清',value:'0',eleId:'hasRefundedNo',checked:true}]";
        String hasRefundedGroupConfigs = "labelWidth:100,width:300";
        String hasRefundedConfigs = "width:60";
        String remarkConfigs = "width:400,height:80,maxLength:100,maxLengthText:'最多输入100个字符'";
        String userNameConfigs = "emptyText:'模糊查询'";
        String loginNameConfigs = "emptyText:'精确匹配'";

        SysEnControllerField lendDate = controller.addFieldDate("lendDate","借款日期",true,true,true,true,false,lendDateConfigs);
        lendDate.setSearByRange( true );
        controller.addFieldRadioInitDataByJson("lendOrBorrow","类型",true,true,true,true,false,lendOrBorrowJson,lendOrBorrowGroupConfigs,lendOrBorrowConfigs);
        SysEnControllerField lendSum = controller.addFieldNumber("lendSum","借款金额",true,true,true,true,false,lendSumConfigs);
        lendSum.setSearByRange( true );
        controller.addFieldText("lendPerson","借款人",true,true,true,true,false,lendPersonConfigs);
        controller.addFieldDate("refundDate","实际还款日期",true,true,true,true,true,refundDateConfigs).setSearByRange(true);
        controller.addFieldNumber("hasRefundedData","已还金额",true,true,true,false,true,hasRefundedDataConfigs);
        controller.addFieldRadioInitDataByJson("hasRefunded","是否已还清",true,true,true,true,true,hasRefundedJson,hasRefundedGroupConfigs,hasRefundedConfigs);
        controller.addFieldTextArea("remark","备注",true,true,true,false,true,remarkConfigs);
        controller.addFieldText("lendPurpose","用途目的",true,true,true,false,true,lendPurposeConfigs);
        controller.addFieldDate("planRefundDate","计划还款日期",true,true,true,false,true,planRefundDateConfigs);
        if( PlatformUserUtils.isContainRoleByCode( "superAdmin" ) ) {//是超级管理员角色
            controller.addFieldText("userName", "用户名", true, false, false, true, true, userNameConfigs);
            controller.addFieldText("loginName", "登录名", true, false, false, true, true, loginNameConfigs);
        }

        controller.addFieldDatetime("addTime","添加时间",true,false,false,false,false,null);
        controller.addFieldDatetime("updateTime","更新时间",true,false,false,false,false,null);
    }

    @Override
    public void initFunction(SysEnController controller) throws PlatformException {
        /*String refundAllFields = "refundDate,remark";
        SysEnControllerFunction refundAllFun = controller.addFunctionAjaxUpdateFields("lendRefundMenu","还清借款","lend/menuAjaxUpdate.do",true,"Applicationedit",2,controller,refundAllFields);
        refundAllFun.setAjaxUpdateWindowConfigs( "title: '还清借款'" );

        String refunFields = "hasRefundedData,refundDate,remark";
        SysEnControllerFunction refunFieldsFun = controller.addFunctionAjaxUpdateFields("lendRefundMenu","部分还款","lend/menuAjaxUpdate.do",true,"Applicationedit",2,controller,refunFields);
        refunFieldsFun.setAjaxUpdateWindowConfigs( "title: '部分还款'" );

        String refunFields = "hasRefundedData,refundDate,remark";
        SysEnControllerFunction refunFieldsFun = controller.addFunctionAjaxUpdateFields("lendRefundMenu","部分还款","lend/menuAjaxUpdate.do",true,"Applicationedit",2,controller,refunFields);
        refunFieldsFun.setAjaxUpdateWindowConfigs( "title: '撤销还款'" );*/
    }

    @Override
    public void beforeSearchData(HttpServletRequest request, HttpServletResponse response, Lend bean) throws PlatformException{
        if( !PlatformUserUtils.isContainRoleByCode( "superAdmin" ) ){//非超级管理员角色,只查询当前用户的数据
            bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        }
    }

    @Override
    public void beforeSaveBean(HttpServletRequest request, HttpServletResponse response, Lend bean) throws PlatformException{
        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        Date date = new Date();
        bean.setAddTime( date );
        bean.setUpdateTime( date );
    }

    @Override
    public void beforeUpdateBean(HttpServletRequest request, HttpServletResponse response,Object bean  ) throws PlatformException{
        Lend tempBean = (Lend)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void beforeMenuAjaxUpdateBean(HttpServletRequest request, HttpServletResponse response, Object bean  ) throws PlatformException{
        Lend tempBean = (Lend)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public List<Map<String,Object>> loadComboBoxDataMap(HttpServletRequest request, HttpServletResponse response, Lend bean, String parentId) throws PlatformException{
        /**
        * 查询下拉框数据
        *
        * Controller中所有获取下拉框数据的方法都在此方法中实现
        */
        String comboBoxFlag = request.getParameter("comboBoxFlag");
        List<Map<String,Object>> res = null;

        /**if( "loadDistrict".equals( comboBoxFlag ) ){
            res = this.getLendService().queryDistrictComboBoxMap( parentId );
        }*/

        return res;
    }

    @Override
    public List<Map<String,Object>> loadTreeNodeDataMap(HttpServletRequest request, HttpServletResponse response, Lend bean) throws PlatformException{
        /**
        * 加载树结点需要的数据
        *
        * Controller中所有获取树节点数据的方法都在此方法中实现。
        * 除非自定义了url
        */
        String treeFlag = request.getParameter("treeFlag");
        List<Map<String,Object>> res = null;

        /**if( "district".equals( treeFlag ) ){//行政区划树
            res = getLendService().queryDistrictTreeMap();
        }*/

        return res;
    }

    public LendService getLendService() {
        return lendService;
    }

    public void setLendService(LendService lendService) {
        this.lendService = lendService;
    }
}
