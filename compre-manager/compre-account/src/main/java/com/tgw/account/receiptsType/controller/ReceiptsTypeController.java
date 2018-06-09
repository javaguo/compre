package com.tgw.account.receiptsType.controller;

import com.tgw.account.receiptsType.model.ReceiptsType;
import com.tgw.account.receiptsType.service.ReceiptsTypeService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/receiptsType")
public class ReceiptsTypeController extends BaseController<ReceiptsType> {

    @Resource
    private ReceiptsTypeService receiptsTypeService;

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "ReceiptsTypeList" );//每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "receiptsType/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "receiptsType/" );//控制器的请求地址
    }

    @Override
    public void initSearch(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView, SysEnController controller, ReceiptsType bean ) throws PlatformException {
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
        if( null!=this.getReceiptsTypeService() ){
            super.initService(  this.getReceiptsTypeService()  );
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

        String receiptsTypeNameConfigs = "maxLength:10,maxLengthText:'最多输入10个字符'";
        String receiptsParentTypeConfigs = "multiSelect:false,emptyText:'请选择所属父类型'";
        String receiptsParentTypeTreeUrl = "receiptsType/loadTreeData.do?fieldMap=id:id,text:receipts_type_name,parentId:fk_parent_id&treeRootVal=-1&treeFlag=receiptsType&resType=map&multiSelect=false";
        String orderNumConfigs = "minValue:0,maxValue:99999999";
        String remarkConfigs = "width:400,height:80,maxLength:100,maxLengthText:'最多输入100个字符'";
        String userNameConfigs = "emptyText:'模糊查询'";
        String loginNameConfigs = "emptyText:'精确匹配'";

        //超级管理员不做按类型搜索功能，因为各用户收入类型都不一样。
        controller.addFieldComboBoxTree( "fkParentId","收入父类型",true,true,true,!superAdminFlag,true,receiptsParentTypeConfigs,receiptsParentTypeTreeUrl );
        controller.addFieldText("receiptsTypeName","收入名称",true,true,true,true,false,receiptsTypeNameConfigs);
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
        controller.addFunctionBaseAjaxIndepe( "copyReceiptsTypeFromSys","一键生成默认收入类型","receiptsType/ajaxCopyReceiptsTypeFromSys.do",true,"Application",1 );

        StringBuffer strIns = new StringBuffer();
        strIns.append("   1、可以对自己的收入类型进行维护管理，可以对生成的默认收入类型进行维护管理。");
        strIns.append("   2、可以通过调整序号调整收入类型在下拉框(树)中显示顺序。");
        strIns.append("   3、收入类型最多为二级结构。");

        controller.addFunctionInstructions("receiptsTypeInstructions","功能说明","Zoom",10,strIns.toString());
    }

    @Override
    public void beforeSearchData(HttpServletRequest request, HttpServletResponse response, ReceiptsType bean) throws PlatformException{
        if( !PlatformUserUtils.isContainRoleByCode( PlatformSysConstant.SYS_ROLE_CODE_SUPER_ADMIN ) ){//非超级管理员角色,只查询当前用户的数据
            bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        }
    }

    @Override
    public void beforeSaveBean(HttpServletRequest request, HttpServletResponse response, ReceiptsType bean) throws PlatformException{
        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        if( bean.getFkParentId()==null ){
            bean.setFkParentId(-1);
        }else{
            ReceiptsType type_p = new ReceiptsType();
            type_p.setId( bean.getFkParentId() );
            type_p = (ReceiptsType)this.getReceiptsTypeService().selectUniqueBeanByPrimaryKey( type_p );
            if( type_p.getFkParentId()!=-1 ){
                throw new PlatformException("收入类型只支持二级结构，请选择一级节点作为父类型！");
            }
        }
        bean.setIsSysOwn(0);

        Date date = new Date();
        bean.setAddTime( date );
        bean.setUpdateTime( date );
    }

    @Override
    public void beforeUpdateBean(HttpServletRequest request, HttpServletResponse response,Object bean  ) throws PlatformException{
        ReceiptsType tempBean = (ReceiptsType)bean;
        if( tempBean.getFkParentId()==null ){
            tempBean.setFkParentId(-1);
        }else{
            ReceiptsType type_p = new ReceiptsType();
            type_p.setId( tempBean.getFkParentId() );
            type_p = (ReceiptsType)this.getReceiptsTypeService().selectUniqueBeanByPrimaryKey( type_p );
            if( type_p.getFkParentId()!=-1 ){
                throw new PlatformException("收入类型只支持二级结构，请选择一级节点作为父类型！");
            }
        }

        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void beforeMenuAjaxUpdateBean(HttpServletRequest request, HttpServletResponse response, Object bean  ) throws PlatformException{
        ReceiptsType tempBean = (ReceiptsType)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void beforeDelete(HttpServletRequest request, HttpServletResponse response,  ReceiptsType bean) throws PlatformException{
        List<String> idList = PlatformUtils.idsToList( request );
        this.getReceiptsTypeService().checkBeforeDelete( idList );
    }

    @RequestMapping("/ajaxCopyReceiptsTypeFromSys.do")
    public ModelAndView ajaxCopyReceiptsTypeFromSys(){
        ModelAndView modelAndView = new ModelAndView();
        JSONObject jo = JSONObject.fromObject("{}");

        try {
            this.getReceiptsTypeService().saveReceiptsTypeFromSys();
            jo.put("success",true);
            jo.put("msg","生成默认收入类型成功！");
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
    public List<Map<String,Object>> loadComboBoxDataMap(HttpServletRequest request, HttpServletResponse response, ReceiptsType bean, String parentId) throws PlatformException{
        /**
        * 查询下拉框数据
        *
        * Controller中所有获取下拉框数据的方法都在此方法中实现
        */
        String comboBoxFlag = request.getParameter("comboBoxFlag");
        List<Map<String,Object>> res = null;

        /**if( "loadDistrict".equals( comboBoxFlag ) ){
            res = this.getReceiptsTypeService().queryDistrictComboBoxMap( parentId );
        }*/

        return res;
    }

    @Override
    public List<Map<String,Object>> loadTreeNodeDataMap(HttpServletRequest request, HttpServletResponse response, ReceiptsType bean) throws PlatformException{
        /**
        * 加载树结点需要的数据
        *
        * Controller中所有获取树节点数据的方法都在此方法中实现。
        * 除非自定义了url
        */
        String treeFlag = request.getParameter("treeFlag");
        List<Map<String,Object>> res = null;

        if( "receiptsType".equals( treeFlag ) ){//收入类型
            res = getReceiptsTypeService().queryReceiptsTypeMap( String.valueOf( PlatformUserUtils.getLoginUserInfo().getId() ) );
        }else{
            throw new PlatformException("获取树节点信息时没有找到匹配的查询方法！");
        }

        return res;
    }

    public ReceiptsTypeService getReceiptsTypeService() {
        return receiptsTypeService;
    }

    public void setReceiptsTypeService(ReceiptsTypeService receiptsTypeService) {
        this.receiptsTypeService = receiptsTypeService;
    }
}
