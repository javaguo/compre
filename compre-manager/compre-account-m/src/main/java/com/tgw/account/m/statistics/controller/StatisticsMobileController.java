package com.tgw.account.m.statistics.controller;

import com.tgw.account.statistics.model.Statistics;
import com.tgw.account.statistics.service.StatisticsService;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.common.utils.date.PlatformDateUtils;
import com.tgw.basic.framework.controller.BaseController;
import com.tgw.basic.framework.model.controller.SysEnController;
import com.tgw.basic.framework.model.controller.SysEnControllerField;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/m/statistics")
public class StatisticsMobileController extends BaseController<Statistics> {

    @Resource
    private StatisticsService statisticsService;

    @Override
    public void initControllerBaseInfo(SysEnController controller) throws PlatformException {
        controller.setIdentifier( "StatisticsList" );// 每一个列表页面的唯一身份id
        controller.setLoadDataUrl( "m/statistics/searchData.do" );//加载列表页面数据的方法
        controller.setControllerBaseUrl( "m/statistics/" );//控制器的请求地址
    }

    @PostConstruct
    public void initReceiptsTypeMobile(){
        if( null!=this.getStatisticsService() ){
            super.initService(  this.getStatisticsService()  );
        }
    }

    @Override
    public void initField( SysEnController controller ) throws PlatformException {
        //构造字段
        controller.addFieldId("id","ID",null);

        // 当年收支
        SysEnControllerField statiDate1Field = controller.addFieldDate("statiDate1","收支日期1",true,false,false,false,true,true,null,null);
        // 当月收支
        SysEnControllerField statiDate2Field = controller.addFieldDate("statiDate2","收支日期2",true,false,false,false,true,true,null,null);
        // 当年贷款
        SysEnControllerField statiDate3Field = controller.addFieldDate("statiDate3","借款日期",true,false,false,false,true,true,null,null);

        controller.addFieldTextArea("startTime","开始日期",true,false,false,false,true,null);
        controller.addFieldTextArea("endTime","结束日期",true,false,false,false,true,null);
        controller.addFieldTextArea("title1","项目1",true,false,false,false,true,null);
        controller.addFieldTextArea("value1","值",true,false,false,false,true,null);
        controller.addFieldTextArea("title2","项目2",true,false,false,false,true,null);
        controller.addFieldTextArea("value2","值",true,false,false,false,true,null);
        controller.addFieldTextArea("title2A","差额",true,false,false,false,true,null);
        controller.addFieldTextArea("value2A","值(项目1-项目2)",true,false,false,false,true,null);
        controller.addFieldTextArea("title3","项目3",true,false,false,false,true,null);
        controller.addFieldTextArea("value3","值",true,false,false,false,true,null);
        controller.addFieldTextArea("title4","项目4",true,false,false,false,true,null);
        controller.addFieldTextArea("value4","值",true,false,false,false,true,null);
    }

    @Override
    public void beforeSearchData(HttpServletRequest request, HttpServletResponse response, Statistics bean) throws PlatformException{
        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );

        bean.setStatiDate1Start(PlatformDateUtils.getYearStart());
        bean.setStatiDate1End(PlatformDateUtils.getYearEnd());
        bean.setStatiDate2Start(PlatformDateUtils.getMonthStart());
        bean.setStatiDate2End(PlatformDateUtils.getMonthEnd());
        bean.setStatiDate3Start(PlatformDateUtils.getYearStart());
        bean.setStatiDate3End(PlatformDateUtils.getYearEnd());
    }

    @Override
    public List dealListQueryResult(HttpServletRequest request, HttpServletResponse response, Statistics bean, List dataList) throws PlatformException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for( int i=0;i<dataList.size();i++ ){
            HashMap<String,Object> map = (HashMap<String,Object>)dataList.get(i);

            if( i==1 ){
                map.put("startTime",sdf.format( bean.getStatiDate1Start() ) );
                map.put("endTime",sdf.format( bean.getStatiDate1End() ) );
            }else if( i==2 ){
                map.put("startTime",sdf.format( bean.getStatiDate2Start() ) );
                map.put("endTime",sdf.format( bean.getStatiDate2End() ) );
            }else if( i==4 ){
                map.put("startTime",sdf.format( bean.getStatiDate3Start() ) );
                map.put("endTime",sdf.format( bean.getStatiDate3End() ) );
            }

            String d1 = map.get("value1")!=null?map.get("value1").toString():"0";
            String d2 = map.get("value2")!=null?map.get("value2").toString():"0";
            map.put("value1",d1);
            map.put("value2",d2);
            if( StringUtils.isNotBlank( d1 ) && StringUtils.isNotBlank( d2 ) ){
                BigDecimal bd1 = new BigDecimal( Double.parseDouble( d1 ) );
                BigDecimal bd2 = new BigDecimal( Double.parseDouble( d2 ) );
                map.put("value2A",  bd1.subtract( bd2 ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()  );
            }

            dataList.set(i,map);
        }
        return dataList;
    }

    public StatisticsService getStatisticsService() {
        return statisticsService;
    }

    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
}
