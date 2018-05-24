package com.tgw.omnipotent.comPerson.controller;

import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.common.utils.config.PlatformSysConstant;
import com.tgw.basic.framework.controller.BaseController;
import com.tgw.basic.framework.model.controller.SysEnController;
import com.tgw.basic.framework.model.controller.SysEnControllerField;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import com.tgw.omnipotent.comPerson.model.ComPerson;
import com.tgw.omnipotent.comPerson.service.ComPersonService;
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
@RequestMapping("/comPerson")
public class ComPersonController extends BaseController<ComPerson> {

    @Resource
    private ComPersonService comPersonService;

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "ComPersonList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "comPerson/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "comPerson/" );//控制器的请求地址

        controller.setSearchConditionColNum( 2 );
    }

    @Override
    public void initSearch(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView, SysEnController controller, ComPerson bean ) throws PlatformException {

        if( null!=this.getComPersonService() ){
            super.initService(  this.getComPersonService()  );
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

        String nameConfigs = "maxLength:20,maxLengthText:'最多输入20个字符'";
        String sexJson = "[{name:'男',value:'0',eleId:'sexRadioMan'},{name:'女',value:'1',eleId:'sexRadioWoman'}]";
        String sexGroupConfigs = "labelWidth:100,width:300";
        String sexConfigs = "width:60";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String birthdayConfigs = "emptyText:'公历生日',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01',maxValue:'"+sdf.format( new Date() )+"',maxText:'最大为"+sdf.format( new Date() )+"'";
        String birthdayLunarConfigs = "emptyText:'农历生日',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01',maxValue:'"+sdf.format( new Date() )+"',maxText:'最大为"+sdf.format( new Date() )+"'";
        String identityCardConfigs = "maxLength:20,maxLengthText:'最多输入20个字符',vtype:'letterNumUnderline'";
        String professionConfigs = "maxLength:20,maxLengthText:'最多输入20个字符'";
        String specialityConfigs = "width:400,height:80,maxLength:200,maxLengthText:'最多输入200个字符'";
        String mobilePhoneConfigs = "maxLength:15,maxLengthText:'最多输入15个字符',vtype:'phoneNo'";
        String placeOfDomicileConfigs = "maxLength:200,maxLengthText:'最多输入200个字符'";
        String addressConfigs = "maxLength:200,maxLengthText:'最多输入200个字符'";
        String addressWorkConfigs = "maxLength:200,maxLengthText:'最多输入200个字符'";
        String lastGraduateSchoolConfigs = "maxLength:100,maxLengthText:'最多输入100个字符'";
        String lastGraduateDateConfigs = "emptyText:'最后毕业日期',editable:false,minValue:'1900-01-01',minText:'最小为1900-01-01',maxValue:'"+sdf.format( new Date() )+"',maxText:'最大为"+sdf.format( new Date() )+"'";
        String remarkConfigs = "width:400,height:80,maxLength:200,maxLengthText:'最多输入200个字符'";
        String userNameConfigs = "emptyText:'模糊查询'";
        String loginNameConfigs = "emptyText:'精确匹配'";

        controller.addFieldText("name","姓名",true,true,true,true,false,nameConfigs);
        controller.addFieldRadioInitDataByJson("sex","性别",true,true,true,true,true,sexJson,sexGroupConfigs,sexConfigs);
        SysEnControllerField birthday = controller.addFieldDate("birthday","公历生日",true,true,true,true,true,birthdayConfigs);
        birthday.setSearByRange( true );
        SysEnControllerField birthdayLunar = controller.addFieldDate("birthdayLunar","农历生日",true,true,true,true,true,birthdayLunarConfigs);
        birthdayLunar.setSearByRange( true );
        controller.addFieldText("identityCard","证件号码",true,true,true,true,true,identityCardConfigs);
        controller.addFieldText("profession","职业",true,true,true,false,true,professionConfigs);
        controller.addFieldTextArea("speciality","特长",true,true,true,false,true,specialityConfigs);
        controller.addFieldText("mobilePhone1","联系电话1",true,true,true,false,true,mobilePhoneConfigs);
        controller.addFieldText("mobilePhone2","联系电话2",true,true,true,false,true,mobilePhoneConfigs);
        controller.addFieldText("mobilePhone3","联系电话3",true,true,true,false,true,mobilePhoneConfigs);
        controller.addFieldText("placeOfDomicile","户籍所在地",true,true,true,false,true,placeOfDomicileConfigs);
        controller.addFieldText("address","家庭住址",true,true,true,false,true,addressConfigs);
        controller.addFieldText("addressWork","工作地址",true,true,true,false,true,addressWorkConfigs);
        controller.addFieldText("lastGraduateSchool","最后毕业学校",true,true,true,false,true,lastGraduateSchoolConfigs);
        controller.addFieldDate("lastGraduateDate","最后毕业日期",true,true,true,false,true,lastGraduateDateConfigs);
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
    public void beforeSearchData(HttpServletRequest request, HttpServletResponse response, ComPerson bean) throws PlatformException{
        if( !PlatformUserUtils.isContainRoleByCode( PlatformSysConstant.SYS_ROLE_CODE_SUPER_ADMIN ) ){//非超级管理员角色,只查询当前用户的数据
            bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        }
    }

    @Override
    public void beforeSaveBean(HttpServletRequest request, HttpServletResponse response, ComPerson bean) throws PlatformException{
        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );

        Date date = new Date();
        bean.setAddTime( date );
        bean.setUpdateTime( date );
    }

    @Override
    public void beforeUpdateBean(HttpServletRequest request, HttpServletResponse response,Object bean  ) throws PlatformException{
        ComPerson tempBean = (ComPerson)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void beforeMenuAjaxUpdateBean(HttpServletRequest request, HttpServletResponse response, Object bean  ) throws PlatformException{
        ComPerson tempBean = (ComPerson)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public List<Map<String,Object>> loadComboBoxDataMap(HttpServletRequest request, HttpServletResponse response, ComPerson bean, String parentId) throws PlatformException{
        /**
        * 查询下拉框数据
        *
        * Controller中所有获取下拉框数据的方法都在此方法中实现
        */
        String comboBoxFlag = request.getParameter("comboBoxFlag");
        List<Map<String,Object>> res = null;

        /**if( "loadDistrict".equals( comboBoxFlag ) ){
            res = this.getComPersonService().queryDistrictComboBoxMap( parentId );
        }*/

        return res;
    }

    @Override
    public List<Map<String,Object>> loadTreeNodeDataMap(HttpServletRequest request, HttpServletResponse response, ComPerson bean) throws PlatformException{
        /**
        * 加载树结点需要的数据
        *
        * Controller中所有获取树节点数据的方法都在此方法中实现。
        * 除非自定义了url
        */
        String treeFlag = request.getParameter("treeFlag");
        List<Map<String,Object>> res = null;

        if( "treeFlag".equals( treeFlag ) ){
            //res = getComPersonService().queryDistrictTreeMap();
        }else{
            throw new PlatformException("获取树节点信息时没有找到匹配的查询方法！");
        }

        return res;
    }

    public ComPersonService getComPersonService() {
        return comPersonService;
    }

    public void setComPersonService(ComPersonService comPersonService) {
        this.comPersonService = comPersonService;
    }
}
