$(function(){
    searSubmitStatisticsReceiptEChart();
});
function statisticsReceiptEChartCallback( freeAreaEleId,searForm ){
    var lineChartEleId = "receiptLineChartEle";
    var barChartEleId = "receiptBarChartEle";
    var pieChartEleId = "receiptPieChartEle";

    if( !$("#"+lineChartEleId).length > 0 || !$("#"+barChartEleId).length > 0 || !$("#"+pieChartEleId).length > 0  ){
        $("#"+freeAreaEleId).html("<div id='"+lineChartEleId+"'></div><div id='"+barChartEleId+"'></div><div id='"+pieChartEleId+"'>");
        $("#"+lineChartEleId).css("height","350px");
        $("#"+lineChartEleId).css("width","100%");
        $("#"+lineChartEleId).css("margin-top","10px");

        $("#"+barChartEleId).css("height","350px");
        $("#"+barChartEleId).css("width","100%");
        $("#"+barChartEleId).css("margin-top","30px");

        $("#"+pieChartEleId).css("height","350px");
        $("#"+pieChartEleId).css("width","100%");
        $("#"+pieChartEleId).css("margin-top","30px");
    }

    var lineChart = echarts.init(document.getElementById(lineChartEleId));
    var barChart = echarts.init(document.getElementById(barChartEleId));
    var pieChart = echarts.init(document.getElementById(pieChartEleId));

    searForm.submit({
        submitEmptyText :false,
        waitMsg :'正在查询，请耐心等待......',
        success: function(form, action) {
            action.result.echartInfo.echart_titleText="收入统计图";
            renderEchartStatisticsReceipt( lineChart,"line",action.result.echartInfo );

            action.result.echartInfo.echart_titleText="收入统计图";
            renderEchartStatisticsReceipt( barChart,"bar",action.result.echartInfo );

            action.result.echartInfo.echart_titleText="收入饼状统计图";
            renderEchartStatisticsReceiptPie( pieChart,action.result.echartInfo );
        },
        failure: function(form, action) {
            lineChart.clear();
            barChart.clear();
            pieChart.clear();
            Ext.Msg.alert('错误提示', "抱歉，出错了！"+action.result.msg);
        }
    });

}

function renderEchartStatisticsReceipt( myChart,seriesType,resData){
    var xAxisData = resData.echart_xAxisData;
    var legendData = resData.echart_legendData;
    var items = resData.echart_seriesData;

    var seriesData = [];
    items.forEach(function (item,index) {
            var obj = {};
            obj.type = seriesType;
            obj.name = item.name;
            obj.data = item.data;
            seriesData.push( obj );
    });


    // 指定图表的配置项和数据
    var option = {
        title: {
            text: resData.echart_titleText,
            x:'center'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            type: 'scroll',
            orient: 'horizontal',
            left: 20,
            right:20,
            top: 30,
            data:legendData
        },
        grid: {
            top:70,
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            right:20,
            feature: {
                magicType:{
                    type: ['line', 'bar', 'stack', 'tiled']
                },
                dataZoom:{},
                dataView:{},
                restore:{},
                saveAsImage: {}
            }
        },
        dataZoom: [
            {
                id: 'dataZoomX',
                type: 'slider',
                xAxisIndex: [0],
                filterMode: 'filter'
            },
            {
                id: 'dataZoomY',
                type: 'slider',
                yAxisIndex: [0],
                filterMode: 'empty'
            }
        ],
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: xAxisData
        },
        yAxis: {
            type: 'value'
        },
        series: seriesData
    };

    //myChart.hideLoading();
    myChart.setOption(option,true);
}

function renderEchartStatisticsReceiptPie( myChart,resData){
    var legendData = resData.echart_legendData;
    var seriesData = resData.echart_seriesDataPie;
    var text = resData.echart_titleText+"（总额："+resData.echart_count+"）";

    option = {
        title : {
            text: text,
            x:'center'
        },
        tooltip : {
            trigger: 'item'
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 50,
            top: 30,
            bottom: 20,
            data: legendData
        },
        toolbox: {
            right: 50,
            feature: {
                dataView:{},
                saveAsImage: {}
            }
        },
        series : [
            {
                name: '收入',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:seriesData,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    //myChart.hideLoading();
    myChart.setOption(option,true);
}