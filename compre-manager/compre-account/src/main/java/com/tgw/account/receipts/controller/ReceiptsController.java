package com.tgw.account.receipts.controller;

import com.tgw.account.receipts.model.Receipts;
import com.tgw.account.receipts.service.ReceiptsService;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.common.utils.PlatformUtils;
import com.tgw.basic.common.utils.config.PlatformSysConstant;
import com.tgw.basic.framework.controller.BaseController;
import com.tgw.basic.framework.model.controller.SysEnController;
import com.tgw.basic.framework.model.controller.SysEnControllerField;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import com.tgw.omnipotent.comEvent.service.ComEventService;
import com.tgw.omnipotent.comPerson.service.ComPersonService;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/receipts")
public class ReceiptsController extends BaseController<Receipts> {

    @Resource
    private ReceiptsService receiptsService;
    @Resource
    private ComEventService comEventService;
    @Resource
    private ComPersonService comPersonService;

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "ReceiptsList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "receipts/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "receipts/" );//控制器的请求地址

        controller.setSearchConditionColNum(2);
    }

    @Override
    public void initSearch(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView, SysEnController controller, Receipts bean ) throws PlatformException {
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
        if( null!=this.getReceiptsService() ){
            super.initService(  this.getReceiptsService()  );
        }else{

        }

    }

    @Override
    public void initField( SysEnController controller ) throws PlatformException {
        /**
        * 注意事项：
        * 1.定义的变量中不要包含SavePathHidden。SavePathHidden被框架使用。用来存储上传附件的路径。
        */
        boolean superAdminFlag = PlatformUserUtils.isContainRoleByCode( PlatformSysConstant.SYS_ROLE_CODE_SUPER_ADMIN )?true:false;

        //构造字段
        controller.addFieldId("id","ID",null);

        String receiptsTypeConfigs = "multiSelect:false,emptyText:'请选择收入类型'";
        String receiptsTypeTreeUrl = "receiptsType/loadTreeData.do?fieldMap=id:id,text:receipts_type_name,parentId:fk_parent_id&treeRootVal=-1&treeFlag=receiptsType&resType=map&multiSelect=false";
        String receiptsTypeSearConfigs = "multiSelect:false,emptyText:'请选择收入类型',multiSelect:true,multiCascade:false";
        String receiptsTypeTreeSearUrl = "receiptsType/loadTreeData.do?fieldMap=id:id,text:receipts_type_name,parentId:fk_parent_id&treeRootVal=-1&treeFlag=receiptsType&resType=map&multiSelect=true";
        String recSumConfigs = "emptyText:'收入金额',maxValue:99999999.9,maxText:'最大为999,999,99.9',minValue:0.1,minText:'最小为0.1',step:100";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String recDateConfigs = "emptyText:'收入日期',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01',value:'"+ sdf.format( new Date() )+"'";
        String remarkConfigs = "width:400,height:80,maxLength:100,maxLengthText:'最多输入100个字符'";
        String fkComEventIdConfigs = "listConfig:{emptyText:'相关事件'}";
        String fkComPersonConfigs = "listConfig:{emptyText:'相关人'}";
        String userNameConfigs = "emptyText:'模糊查询'";
        String loginNameConfigs = "emptyText:'精确匹配'";

        SysEnControllerField recDate = controller.addFieldDate("recDate","收入日期",true,true,true,true,false,recDateConfigs);
        recDate.setSearByRange( true );
        controller.addFieldComboBoxTree( "receiptsParentTypeName","父类型",true,false,false,false,true,null,receiptsTypeTreeUrl );
        //添加编辑使用此字段,树单选
        controller.addFieldComboBoxTree( "fkReceiptsTypeId","收入类型",true,true,true,false,false,receiptsTypeConfigs,receiptsTypeTreeUrl );
        //搜索使用此字段，树多选
        controller.addFieldComboBoxTree( "typeIds","收入类型",true,false,false,!superAdminFlag,false,receiptsTypeSearConfigs,receiptsTypeTreeSearUrl ).setShowList(false);
        SysEnControllerField recSum = controller.addFieldNumber("recSum","收入金额",true,true,true,true,false,recSumConfigs);
        recSum.setSearByRange( true );
        controller.addFieldTextArea("remark","备注",true,true,true,false,true,remarkConfigs);
        controller.addFieldComboBoxBySQL("fkComEventId","相关事件",true,true,true,!superAdminFlag,true,"loadComEvent",null,fkComEventIdConfigs);
        controller.addFieldComboBoxBySQL("fkComPersonId","相关人员",true,true,true,!superAdminFlag,true,"loadComPerson",null,fkComPersonConfigs);
        if( PlatformUserUtils.isContainRoleByCode( "superAdmin" ) ) {//是超级管理员角色
            controller.addFieldText("userName", "用户名", true, false, false, true, true, userNameConfigs);
            controller.addFieldText("loginName", "登录名", true, false, false, true, true, loginNameConfigs);
        }

        controller.addFieldDatetime("addTime","添加时间",true,false,false,false,false,null);
        controller.addFieldDatetime("updateTime","更新时间",true,false,false,false,false,null);
    }

    @Override
    public void initFunction(SysEnController controller) throws PlatformException {
        StringBuffer strIns = new StringBuffer();
        strIns.append("   1、首次使用需要在“收入类型”功能中添加自己的收入类型。");
        strIns.append("   2、相关事件在“事记”功能中维护，下拉框只显示近一年内的事件。");
        strIns.append("   3、相关人员在“通讯录”功能中维护。");

        controller.addFunctionInstructions("receiptsInstructions","功能说明","Zoom",10,strIns.toString());
    }

    @Override
    public void beforeSearchData(HttpServletRequest request, HttpServletResponse response, Receipts bean) throws PlatformException{
        if(StringUtils.isNotBlank( bean.getTypeIds() )){
            bean.setIdList( PlatformUtils.stringToList( bean.getTypeIds(),"," ) );
        }

        if( !PlatformUserUtils.isContainRoleByCode( "superAdmin" ) ){//非超级管理员角色,只查询当前用户的数据
            bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        }
    }

    @Override
    public void beforeSaveBean(HttpServletRequest request, HttpServletResponse response, Receipts bean) throws PlatformException{
        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );

        Date date = new Date();
        bean.setAddTime( date );
        bean.setUpdateTime( date );
    }

    @Override
    public void beforeUpdateBean(HttpServletRequest request, HttpServletResponse response,Object bean  ) throws PlatformException{
        Receipts tempBean = (Receipts)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void beforeMenuAjaxUpdateBean(HttpServletRequest request, HttpServletResponse response, Object bean  ) throws PlatformException{
        Receipts tempBean = (Receipts)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public List<Map<String,Object>> loadComboBoxDataMap(HttpServletRequest request, HttpServletResponse response, Receipts bean, String parentId) throws PlatformException{
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

    @Override
    public List<Map<String,Object>> loadTreeNodeDataMap(HttpServletRequest request, HttpServletResponse response, Receipts bean) throws PlatformException{
        /**
        * 加载树结点需要的数据
        *
        * Controller中所有获取树节点数据的方法都在此方法中实现。
        * 除非自定义了url
        */
        String treeFlag = request.getParameter("treeFlag");
        List<Map<String,Object>> res = null;

        /**if( "district".equals( treeFlag ) ){//行政区划树
            res = getReceiptsService().queryDistrictTreeMap();
        }*/

        return res;
    }

    public ReceiptsService getReceiptsService() {
        return receiptsService;
    }

    public void setReceiptsService(ReceiptsService receiptsService) {
        this.receiptsService = receiptsService;
    }

    public ComEventService getComEventService() {
        return comEventService;
    }

    public void setComEventService(ComEventService comEventService) {
        this.comEventService = comEventService;
    }

    public ComPersonService getComPersonService() {
        return comPersonService;
    }

    public void setComPersonService(ComPersonService comPersonService) {
        this.comPersonService = comPersonService;
    }
}
