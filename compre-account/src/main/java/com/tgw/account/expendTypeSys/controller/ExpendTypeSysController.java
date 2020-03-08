package com.tgw.account.expendTypeSys.controller;

import com.tgw.account.expendTypeSys.model.ExpendTypeSys;
import com.tgw.account.expendTypeSys.service.ExpendTypeSysService;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.common.utils.PlatformUtils;
import com.tgw.basic.framework.controller.BaseController;
import com.tgw.basic.framework.model.controller.SysEnController;
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
@RequestMapping("/expendTypeSys")
public class ExpendTypeSysController extends BaseController<ExpendTypeSys> {

    @Resource
    private ExpendTypeSysService expendTypeSysService;

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "ExpendTypeSysList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "expendTypeSys/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "expendTypeSys/" );//控制器的请求地址

        controller.setSearchConditionColNum( 4 );
    }


    @Override
    public void initSearch(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView, SysEnController controller, ExpendTypeSys bean ) throws PlatformException {
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
        if( null!=this.getExpendTypeSysService() ){
            super.initService(  this.getExpendTypeSysService()  );
        }else{

        }

        /**
        *此处的配置会覆盖jsp页面中默认的配置。
        * 此处配置也可以写在   initControllerBaseInfo()或initField()或initFunction()方法中
        */
        String width = "500";
        String height = "260";
        String addWindowConfigs = "title: '添加系统默认支出类型',width:"+width+",height:"+height;
        String editWindowConfigs = "title: '编辑系统默认支出类型',width:"+width+",height:"+height;
        String viewWindowConfigs = "title: '查看系统默认支出类型',width:"+width+",height:"+height;
        controller.addWindowConfig( addWindowConfigs,editWindowConfigs,viewWindowConfigs );
    }

    @Override
    public void initField( SysEnController controller ) throws PlatformException {
        /**
        * 注意事项：
        * 1.定义的变量中不要包含SavePathHidden。SavePathHidden被框架使用。用来存储上传附件的路径。
        */
        //构造字段
        controller.addFieldId("id","ID",null);

        String expendTypeNameConfigs = "maxLength:10,maxLengthText:'最多输入10个字符'";
        String expendParentTypeConfigs = "multiSelect:false,emptyText:'请选择所属父类型'";
        String expendParentTypeTreeUrl = "expendTypeSys/loadTreeData.do?fieldMap=id:id,text:expend_type_name,parentId:fk_parent_id&treeRootVal=-1&treeFlag=expendTypeSys&resType=map&multiSelect=false";
        String orderNumConfigs = "minValue:0,maxValue:99999999";
        String remarkConfigs = "width:400,height:80,maxLength:100,maxLengthText:'最多输入100个字符'";

        controller.addFieldComboBoxTree( "fkParentId","支出父类型",true,true,true,true,true,expendParentTypeConfigs,expendParentTypeTreeUrl );
        controller.addFieldText("expendTypeName","支出名称",true,true,true,true,false,expendTypeNameConfigs);
        controller.addFieldNumber("orderNum","序号",true,true,true,false,false,orderNumConfigs);
        controller.addFieldTextArea("remark","备注",true,true,true,false,true,remarkConfigs);

        controller.addFieldDatetime("addTime","添加时间",true,false,false,false,false,null);
        controller.addFieldDatetime("updateTime","更新时间",true,false,false,false,false,null);
    }

    @Override
    public void initFunction(SysEnController controller) throws PlatformException {
        StringBuffer strIns = new StringBuffer();
        strIns.append("   1、本列表中的支出类型为系统默认支出类型，各用户生成的默认支出类型数据来自于此列表。");
        strIns.append("   2、可以通过调整序号调整支出类型在下拉框(树)中显示顺序。");
        strIns.append("   3、支出类型最多为二级结构。");

        controller.addFunctionInstructions("expendTypeSysInstructions","功能说明","Zoom",10,strIns.toString());
    }

    @Override
    public void beforeSaveBean(HttpServletRequest request, HttpServletResponse response, ExpendTypeSys bean) throws PlatformException{
        if( bean.getFkParentId()==null  ){
            bean.setFkParentId(-1);
        }else{
            ExpendTypeSys type_p = new ExpendTypeSys();
            type_p.setId( bean.getFkParentId() );
            type_p = (ExpendTypeSys)this.getExpendTypeSysService().selectUniqueBeanByPrimaryKey( type_p );
            if( type_p.getFkParentId()!=-1 ){
                throw new PlatformException("支出类型只支持二级结构，请选择一级节点作为父类型！");
            }
        }

        bean.setIsSysOwn(1);
        Date date = new Date();
        bean.setAddTime( date );
        bean.setUpdateTime( date );
    }

    @Override
    public void beforeUpdateBean(HttpServletRequest request, HttpServletResponse response,Object bean  ) throws PlatformException{
        ExpendTypeSys tempBean = (ExpendTypeSys)bean;

        if( tempBean.getFkParentId()==null ){
            tempBean.setFkParentId(-1);
        }else{
            ExpendTypeSys type_p = new ExpendTypeSys();
            type_p.setId( tempBean.getFkParentId() );
            type_p = (ExpendTypeSys)this.getExpendTypeSysService().selectUniqueBeanByPrimaryKey( type_p );
            if( type_p.getFkParentId()!=-1 ){
                throw new PlatformException("支出类型只支持二级结构，请选择一级节点作为父类型！");
            }
        }

        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void beforeMenuAjaxUpdateBean(HttpServletRequest request, HttpServletResponse response, Object bean  ) throws PlatformException{
        ExpendTypeSys tempBean = (ExpendTypeSys)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void beforeDelete(HttpServletRequest request, HttpServletResponse response,  ExpendTypeSys bean) throws PlatformException{
        List<String> idList = PlatformUtils.idsToList( request );
        this.getExpendTypeSysService().checkBeforeDelete( idList );
    }

    @Override
    public List<Map<String,Object>> loadComboBoxDataMap(HttpServletRequest request, HttpServletResponse response, ExpendTypeSys bean, String parentId) throws PlatformException{
        /**
        * 查询下拉框数据
        *
        * Controller中所有获取下拉框数据的方法都在此方法中实现
        */
        String comboBoxFlag = request.getParameter("comboBoxFlag");
        List<Map<String,Object>> res = null;

        /**if( "loadDistrict".equals( comboBoxFlag ) ){
            res = this.getExpendTypeSysService().queryDistrictComboBoxMap( parentId );
        }*/

        return res;
    }

    @Override
    public List<Map<String,Object>> loadTreeNodeDataMap(HttpServletRequest request, HttpServletResponse response, ExpendTypeSys bean) throws PlatformException{
        /**
        * 加载树结点需要的数据
        *
        * Controller中所有获取树节点数据的方法都在此方法中实现。
        * 除非自定义了url
        */
        String treeFlag = request.getParameter("treeFlag");
        List<Map<String,Object>> res = null;

        if( "expendTypeSys".equals( treeFlag ) ){//支出类型
            res = getExpendTypeSysService().queryExpendTypeSysTreeMap();
        }

        return res;
    }

    public ExpendTypeSysService getExpendTypeSysService() {
        return expendTypeSysService;
    }

    public void setExpendTypeSysService(ExpendTypeSysService expendTypeSysService) {
        this.expendTypeSysService = expendTypeSysService;
    }
}
