package com.tgw.account.expendType.controller;

import com.tgw.account.expendType.model.ExpendType;
import com.tgw.account.expendType.service.ExpendTypeService;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.common.utils.PlatformUtils;
import com.tgw.basic.common.utils.config.PlatformSysConstant;
import com.tgw.basic.framework.controller.BaseController;
import com.tgw.basic.framework.model.controller.SysEnController;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/expendType")
public class ExpendTypeController extends BaseController<ExpendType> {

    @Resource
    private ExpendTypeService expendTypeService;

    @PostConstruct
    public void initExpendMobile(){
        if( null!=this.getExpendTypeService() ){
            super.initService(  this.getExpendTypeService()  );
        }
    }

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "ExpendTypeList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "expendType/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "expendType/" );//控制器的请求地址

        controller.setSearchConditionColNum( 4 );
    }

    @Override
    public void initSearch(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView, SysEnController controller, ExpendType bean ) throws PlatformException {
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
        if( null!=this.getExpendTypeService() ){
            super.initService(  this.getExpendTypeService()  );
        }else{

        }

        String width = "500";
        String height = "260";
        String addWindowConfigs = "title: '添加支出类型',width:"+width+",height:"+height;
        String editWindowConfigs = "title: '编辑支出类型',width:"+width+",height:"+height;
        String viewWindowConfigs = "title: '查看支出类型',width:"+width+",height:"+height;
        controller.addWindowConfig( addWindowConfigs,editWindowConfigs,viewWindowConfigs );
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

        String expendTypeNameConfigs = "maxLength:10,maxLengthText:'最多输入10个字符'";
        String expendParentTypeConfigs = "multiSelect:false,emptyText:'请选择所属父类型'";
        String expendParentTypeTreeUrl = "expendType/loadTreeData.do?fieldMap=id:id,text:expend_type_name,parentId:fk_parent_id&treeRootVal=-1&treeFlag=expendType&resType=map&multiSelect=false";
        String orderNumConfigs = "minValue:0,maxValue:99999999";
        String remarkConfigs = "width:400,height:80,maxLength:100,maxLengthText:'最多输入100个字符'";
        String userNameConfigs = "emptyText:'模糊查询'";
        String loginNameConfigs = "emptyText:'精确匹配'";

        //超级管理员不做按类型搜索功能，因为各用户支出类型都不一样。
        controller.addFieldComboBoxTree( "fkParentId","支出父类型",true,true,true,!superAdminFlag,true,expendParentTypeConfigs,expendParentTypeTreeUrl );
        controller.addFieldText("expendTypeName","支出名称",true,true,true,true,false,expendTypeNameConfigs);
        controller.addFieldNumber("orderNum","序号",true,true,true,false,false,orderNumConfigs);
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
        controller.addFunctionBaseAjaxIndepe( "copyExpendTypeFromSys","一键生成默认支出类型","expendType/ajaxCopyExpendTypeFromSys.do",true,"Application",1 );

        StringBuffer strIns = new StringBuffer();
        strIns.append("   1、可以对自己的支出类型进行维护管理，可以对生成的默认支出类型进行维护管理。");
        strIns.append("   2、可以通过调整序号调整支出类型在下拉框(树)中显示顺序。");
        strIns.append("   3、支出类型最多为二级结构。");

        controller.addFunctionInstructions("receiptsTypeInstructions","功能说明","Zoom",10,strIns.toString());
    }

    @Override
    public void beforeSearchData(HttpServletRequest request, HttpServletResponse response, ExpendType bean) throws PlatformException{
        if( !PlatformUserUtils.isContainRoleByCode( PlatformSysConstant.SYS_ROLE_CODE_SUPER_ADMIN ) ){//非超级管理员角色,只查询当前用户的数据
            bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        }
    }

    @Override
    public void beforeSaveBean(HttpServletRequest request, HttpServletResponse response, ExpendType bean) throws PlatformException{
        this.getExpendTypeService().beforeSaveBean(bean);
    }

    @Override
    public void beforeUpdateBean(HttpServletRequest request, HttpServletResponse response,Object bean  ) throws PlatformException{
        ExpendType tempBean = (ExpendType)bean;
        this.getExpendTypeService().beforeUpdateBean(tempBean);
    }

    @Override
    public void beforeMenuAjaxUpdateBean(HttpServletRequest request, HttpServletResponse response, Object bean  ) throws PlatformException{
        ExpendType tempBean = (ExpendType)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void beforeDelete(HttpServletRequest request, HttpServletResponse response,  ExpendType bean) throws PlatformException{
        List<String> idList = PlatformUtils.idsToList( request );
        this.getExpendTypeService().checkBeforeDelete( idList );
    }

    @RequestMapping("/ajaxCopyExpendTypeFromSys.do")
    public ModelAndView ajaxCopyExpendTypeFromSys(){
        ModelAndView modelAndView = new ModelAndView();
        JSONObject jo = JSONObject.fromObject("{}");

        try {
            this.getExpendTypeService().saveExpendTypeFromSys();
            jo.put("success",true);
            jo.put("msg","生成默认支出类型成功！");
        }catch (PlatformException e){
            jo.put("success",false);
            jo.put("msg",e.getMsg());
        }catch (Exception e){
            jo.put("success",true);
            jo.put("msg","发生异常！");
        }

        modelAndView.addObject( PlatformSysConstant.JSONSTR, jo.toString() );
        modelAndView.setViewName( this.getJsonView() );

        return  modelAndView;
    }

    @Override
    public List<Map<String,Object>> loadComboBoxDataMap(HttpServletRequest request, HttpServletResponse response, ExpendType bean, String parentId) throws PlatformException{
        /**
        * 查询下拉框数据
        *
        * Controller中所有获取下拉框数据的方法都在此方法中实现
        */
        String comboBoxFlag = request.getParameter("comboBoxFlag");
        List<Map<String,Object>> res = null;

        return res;
    }

    @Override
    public List<Map<String,Object>> loadTreeNodeDataMap(HttpServletRequest request, HttpServletResponse response, ExpendType bean) throws PlatformException{
        /**
        * 加载树结点需要的数据
        *
        * Controller中所有获取树节点数据的方法都在此方法中实现。
        * 除非自定义了url
        */
        String treeFlag = request.getParameter("treeFlag");
        List<Map<String,Object>> res = null;

        if( "expendType".equals( treeFlag ) ){//支出类型
            res = getExpendTypeService().queryExpendTypeTreeMap(  String.valueOf( PlatformUserUtils.getLoginUserInfo().getId() )  );
        }

        return res;
    }

    public ExpendTypeService getExpendTypeService() {
        return expendTypeService;
    }

    public void setExpendTypeService(ExpendTypeService expendTypeService) {
        this.expendTypeService = expendTypeService;
    }

}
