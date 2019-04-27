package com.tgw.account.m.receipts;

import com.tgw.account.receipts.model.Receipts;
import com.tgw.account.receipts.service.ReceiptsService;
import com.tgw.account.receiptsType.model.ReceiptsType;
import com.tgw.account.receiptsType.service.ReceiptsTypeService;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.common.utils.PlatformUtils;
import com.tgw.basic.common.utils.config.PlatformSysConstant;
import com.tgw.basic.framework.controller.BaseController;
import com.tgw.basic.framework.model.controller.SysEnController;
import com.tgw.basic.framework.model.controller.SysEnControllerField;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import com.tgw.omnipotent.comEvent.service.ComEventService;
import com.tgw.omnipotent.comPerson.service.ComPersonService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/m/receipts")
public class ReceiptsMobileController extends BaseController<Receipts> {

    @Resource
    private ReceiptsService receiptsService;
    @Resource
    private ComEventService comEventService;
    @Resource
    private ComPersonService comPersonService;
    @Resource
    private ReceiptsTypeService receiptsTypeService;

    @PostConstruct
    public void initReceiptsMobile(){
        if( null!=this.getReceiptsService() ){
            super.initService(  this.getReceiptsService()  );
        }
    }

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "ReceiptsList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "m/receipts/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "m/receipts/" );//控制器的请求地址

        controller.setSearchConditionColNum(2);
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
        SysEnControllerField recDate = controller.addFieldDate("recDate","收入日期",true,true,true,true,false,null);
        controller.addFieldComboBoxTree( "receiptsParentTypeName","父类型",true,false,false,false,true,null,null );
        controller.addFieldComboBoxTree( "fkReceiptsTypeId","收入类型",true,true,true,false,false,null,null );
        controller.addFieldNumber("recSum","收入金额",true,true,true,true,false,null);
        controller.addFieldTextArea("remark","备注",true,true,true,false,true,null);
        controller.addFieldComboBoxBySQL("fkComEventId","相关事件",true,true,true,!superAdminFlag,true,"loadComEvent",null,null);
        controller.addFieldComboBoxBySQL("fkComPersonId","相关人员",true,true,true,!superAdminFlag,true,"loadComPerson",null,null);

        controller.addFieldDatetime("addTime","添加时间",true,false,true,false,false,null);
        controller.addFieldDatetime("updateTime","更新时间",true,false,true,false,false,null);
    }

    @Override
    public void beforeSearchData(HttpServletRequest request, HttpServletResponse response, Receipts bean) throws PlatformException{
        if(StringUtils.isNotBlank( bean.getTypeIds() )){
            bean.setIdList( PlatformUtils.stringToList( bean.getTypeIds(),"," ) );
        }

        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
    }

    @Override
    public void beforeSaveBean(HttpServletRequest request, HttpServletResponse response, Receipts bean) throws PlatformException{
        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );

        this.getReceiptsService().checkReceiptsBeforSaveOrUpdate(bean);

        Date date = new Date();
        bean.setAddTime( date );
        bean.setUpdateTime( date );
    }

    @Override
    public void beforeUpdateBean(HttpServletRequest request, HttpServletResponse response,Object bean  ) throws PlatformException{
        Receipts tempBean = (Receipts)bean;

        this.getReceiptsService().checkReceiptsBeforSaveOrUpdate(tempBean);

        Receipts receipts = new Receipts();
        receipts.setId(tempBean.getId());
        receipts = (Receipts)receiptsService.selectUniqueBeanByPrimaryKey(receipts);

        tempBean.setAddTime(receipts.getAddTime());
        tempBean.setUpdateTime( new Date() );
    }

    @Override
    public void afterEdit(HttpServletRequest request, HttpServletResponse response, Receipts bean, JSONObject jo) throws PlatformException{
        ReceiptsType receiptsType = new ReceiptsType();
        receiptsType.setId(bean.getFkReceiptsTypeId());
        receiptsType = (ReceiptsType)receiptsTypeService.selectUniqueBeanByPrimaryKey(receiptsType);
        jo.put("receiptsTypeName",receiptsType.getReceiptsTypeName());

        try {
            ReceiptsType receiptsTypeP = new ReceiptsType();
            receiptsTypeP.setId(receiptsType.getFkParentId());
            receiptsTypeP = (ReceiptsType)receiptsTypeService.selectUniqueBeanByPrimaryKey(receiptsTypeP);
            jo.put("receiptsTypeNameParent",receiptsTypeP.getReceiptsTypeName());
        } catch (PlatformException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeMenuAjaxUpdateBean(HttpServletRequest request, HttpServletResponse response, Object bean  ) throws PlatformException{
        Receipts tempBean = (Receipts)bean;
        tempBean.setUpdateTime( new Date() );
    }

    @RequestMapping("/statisticSum.do")
    public ModelAndView statisticSum(HttpServletRequest request, HttpServletResponse response, Receipts receipts){
        ModelAndView modelAndView = new ModelAndView(this.getJsonView());
        JSONObject jo = JSONObject.fromObject("{}");

        receipts.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        Double sum = this.getReceiptsService().statisticSum(receipts);
        jo.put("success",true);
        jo.put("statisticSum",sum==null?"":sum.toString());

        modelAndView.addObject( PlatformSysConstant.JSONSTR, jo.toString() );
        return  modelAndView;
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

    public ReceiptsTypeService getReceiptsTypeService() {
        return receiptsTypeService;
    }

    public void setReceiptsTypeService(ReceiptsTypeService receiptsTypeService) {
        this.receiptsTypeService = receiptsTypeService;
    }
}
