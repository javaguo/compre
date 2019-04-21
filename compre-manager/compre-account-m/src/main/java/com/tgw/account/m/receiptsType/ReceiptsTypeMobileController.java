package com.tgw.account.m.receiptsType;

import com.tgw.account.receiptsType.model.ReceiptsType;
import com.tgw.account.receiptsType.service.ReceiptsTypeService;
import com.tgw.account.util.type.TypeUtil;
import com.tgw.basic.common.exception.PlatformException;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/m/receiptsType")
public class ReceiptsTypeMobileController extends BaseController<ReceiptsType> {

    @Resource
    private ReceiptsTypeService receiptsTypeService;

    @PostConstruct
    public void initReceiptsTypeMobile(){
        if( null!=this.getReceiptsTypeService() ){
            super.initService(  this.getReceiptsTypeService()  );
        }
    }

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "ReceiptsTypeList" );//每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "m/receiptsType/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "m/receiptsType/" );//控制器的请求地址
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
            res = TypeUtil.removeNoChildNode(res);
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
