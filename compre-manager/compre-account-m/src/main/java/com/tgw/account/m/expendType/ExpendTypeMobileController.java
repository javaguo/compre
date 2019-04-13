package com.tgw.account.m.expendType;

import com.tgw.account.expendType.model.ExpendType;
import com.tgw.account.expendType.service.ExpendTypeService;
import com.tgw.account.m.utils.TypeUtil;
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
@RequestMapping("/m/expendType")
public class ExpendTypeMobileController extends BaseController<ExpendType> {

    @Resource
    private ExpendTypeService expendTypeService;

    @PostConstruct
    public void initExpendTypeMobile(){
        if( null!=this.getExpendTypeService() ){
            super.initService(  this.getExpendTypeService()  );
        }
    }

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "ExpendTypeList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "m/expendType/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "m/expendType/" );//控制器的请求地址
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
            res = TypeUtil.removeNoChildNode(res);
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
