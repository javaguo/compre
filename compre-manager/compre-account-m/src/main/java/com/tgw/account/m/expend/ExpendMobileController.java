package com.tgw.account.m.expend;

import com.tgw.account.expend.model.Expend;
import com.tgw.account.expend.service.ExpendService;
import com.tgw.account.expendType.model.ExpendType;
import com.tgw.account.expendType.service.ExpendTypeService;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.common.utils.PlatformUtils;
import com.tgw.basic.common.utils.config.PlatformSysConstant;
import com.tgw.basic.framework.controller.BaseController;
import com.tgw.basic.framework.model.controller.SysEnController;
import com.tgw.basic.system.constant.model.SysEnConstant;
import com.tgw.basic.system.constant.service.SysEnConstantService;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import com.tgw.omnipotent.comEvent.service.ComEventService;
import com.tgw.omnipotent.comPerson.service.ComPersonService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/m/expend")
public class ExpendMobileController extends BaseController<Expend> {

    @Resource
    private ExpendService expendService;
    @Resource
    private ComEventService comEventService;
    @Resource
    private ComPersonService comPersonService;
    @Resource
    private ExpendTypeService expendTypeService;
    @Resource
    private SysEnConstantService sysEnConstantService;

    @PostConstruct
    public void initExpendMobile(){
        if( null!=this.getExpendService() ){
            super.initService(  this.getExpendService()  );
        }
    }

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "ExpendList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "m/expend/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "m/expend/" );//控制器的请求地址

        controller.setSearchConditionColNum(2);
    }

    @Override
    public void initField( SysEnController controller ) throws PlatformException {
        //构造字段
        controller.addFieldId("id","ID",null);
        controller.addFieldDate("expDate","支出日期",true,true,true,true,false,null);
        controller.addFieldComboBoxTree( "expendParentTypeName","父类型",true,false,false,false,true,null,null );
        controller.addFieldComboBoxTree( "fkExpendTypeId","支出类型",true,true,true,false,false,null,null );
        controller.addFieldComboBoxBySQL("fkExpendWayId","支出方式",true,true,true,false,true,"expendWay",null,null);
        controller.addFieldNumber("expSum","支出金额",true,true,true,true,false,null);
        controller.addFieldTextArea("remark","备注",true,true,true,false,true,null);
        controller.addFieldComboBoxBySQL("fkComEventId","相关事件",true,true,true,false,true,"loadComEvent",null,null);
        controller.addFieldComboBoxBySQL("fkComPersonId","相关人员",true,true,true,false,true,"loadComPerson",null,null);

        controller.addFieldDatetime("addTime","添加时间",true,false,true,false,false,null);
        controller.addFieldDatetime("updateTime","更新时间",true,false,true,false,false,null);
    }


    @Override
    public void beforeSearchData(HttpServletRequest request, HttpServletResponse response, Expend bean) throws PlatformException{
        if(StringUtils.isNotBlank( bean.getTypeIds() )){
            bean.setIdList( PlatformUtils.stringToList( bean.getTypeIds(),"," ) );
        }

        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
    }

    @Override
    public void beforeSaveBean(HttpServletRequest request, HttpServletResponse response, Expend bean) throws PlatformException{
        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );

        this.getExpendService().checkExpendBeforSaveOrUpdate(bean);

        Date date = new Date();
        bean.setAddTime( date );
        bean.setUpdateTime( date );
    }

    @Override
    public void beforeUpdateBean(HttpServletRequest request, HttpServletResponse response,Object bean  ) throws PlatformException{
        Expend tempBean = (Expend)bean;

        this.getExpendService().checkExpendBeforSaveOrUpdate(tempBean);

        Expend expend = new Expend();
        expend.setId(tempBean.getId());
        expend = (Expend)expendService.selectUniqueBeanByPrimaryKey(expend);

        tempBean.setAddTime(expend.getAddTime());
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void afterEdit(HttpServletRequest request, HttpServletResponse response, Expend bean,JSONObject jo) throws PlatformException{
        ExpendType expendType = new ExpendType();
        expendType.setId(bean.getFkExpendTypeId());
        expendType = (ExpendType)expendTypeService.selectUniqueBeanByPrimaryKey(expendType);
        jo.put("expendTypeName",expendType.getExpendTypeName());

        try {
            ExpendType expendTypeP = new ExpendType();
            expendTypeP.setId(expendType.getFkParentId());
            expendTypeP = (ExpendType)expendTypeService.selectUniqueBeanByPrimaryKey(expendTypeP);
            jo.put("expendTypeNameParent",expendTypeP.getExpendTypeName());
        } catch (PlatformException e) {
            e.printStackTrace();
        }

        if (bean.getFkExpendWayId()!=null){
            SysEnConstant con = new SysEnConstant();
            con.setId( bean.getFkExpendWayId() );
            con = (SysEnConstant)this.getSysEnConstantService().selectUniqueBeanByPrimaryKey(con);
            jo.put("expendWayName",con.getName());
        }

    }

    @Override
    public void beforeMenuAjaxUpdateBean(HttpServletRequest request, HttpServletResponse response, Object bean  ) throws PlatformException{
        Expend tempBean = (Expend)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @RequestMapping("/statisticSum.do")
    public ModelAndView statisticSum(HttpServletRequest request, HttpServletResponse response,Expend expend){
        ModelAndView modelAndView = new ModelAndView(this.getJsonView());
        JSONObject jo = JSONObject.fromObject("{}");

        if(StringUtils.isNotBlank( expend.getTypeIds() )){
            expend.setIdList( PlatformUtils.stringToList( expend.getTypeIds(),"," ) );
        }
        expend.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        Double sum = this.getExpendService().statisticSum(expend);
        jo.put("success",true);
        jo.put("statisticSum",sum==null?"":sum.toString());

        modelAndView.addObject( PlatformSysConstant.JSONSTR, jo.toString() );
        return  modelAndView;
    }

    @Override
    public List<Map<String,Object>> loadComboBoxDataMap(HttpServletRequest request, HttpServletResponse response, Expend bean, String parentId) throws PlatformException{
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
        } else  if( "expendWay".equals( comboBoxFlag ) ){
            res = this.getSysEnConstantService().loadConstantByNamespace("expendWay");
            List<Map<String,Object>> convertResult = new ArrayList<Map<String, Object>>(res.size());
            if (CollectionUtils.isNotEmpty(res)){
                for (Map<String,Object> map : res){
                    Map<String,Object> convertMap = new HashMap<String,Object>(2);
                    convertMap.put( "value",map.get("id") );
                    convertMap.put( "label",map.get("name") );
                    convertResult.add(convertMap);
                }
            }
            res = convertResult;
        }

        return res;
    }


    public ExpendService getExpendService() {
        return expendService;
    }

    public void setExpendService(ExpendService expendService) {
        this.expendService = expendService;
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

    public ExpendTypeService getExpendTypeService() {
        return expendTypeService;
    }

    public void setExpendTypeService(ExpendTypeService expendTypeService) {
        this.expendTypeService = expendTypeService;
    }

    public SysEnConstantService getSysEnConstantService() {
        return sysEnConstantService;
    }

    public void setSysEnConstantService(SysEnConstantService sysEnConstantService) {
        this.sysEnConstantService = sysEnConstantService;
    }
}
