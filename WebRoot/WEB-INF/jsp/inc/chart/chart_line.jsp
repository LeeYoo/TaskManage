<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.paging.entity.PageView"%>
<%@page import="com.chart.entity.ChartInfo"%>
<%
	PageView<ChartInfo> pageview = (PageView<ChartInfo>)request.getAttribute("pageView");
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" href="/style/line_style.css" type="text/css">
        <script src="/js/amcharts.js" type="text/javascript"></script>
        <script src="/js/serial.js" type="text/javascript"></script>
        <script src="/js/dark.js" type="text/javascript"></script>
       
        <script type="text/javascript">
            var chart = AmCharts.makeChart("chartdiv", {
                "type": "serial",
                "theme": "dark",
                /* "dataDateFormat": "YYYY-MM-DD HH:NN:SS", */
                "dataDateFormat": "YYYY-MM-DD",
                "dataProvider": [
                
	                /* {"date":"2014-09-10","value":104}, 
	                	{"date": "2013-12-01","value":130} */
	                
	                <%
		                List<ChartInfo> list = pageview.getRecords();
		                
		                for (int i = 0; i < list.size(); i++)
		                {
		                	out.print("{'date':'" +  list.get(i).getUpdateTime() + "', 'value': " + list.get(i).getProgress() + "}");
		                	if (i < list.size()){
		                		out.print(",");
		                	}
		                }
	                %>
	             ],
                "valueAxes": [{
                    "maximum": 100,
                    "minimum": 0,
                    "axisAlpha": 0,
                    "guides": [{
                        "fillAlpha": 0.1,
                        "fillColor": "#CC0000",
                        "lineAlpha": 0,
                        "toValue": 50,		/* 背景从0——50 */
                        "value": 0
                    }, {
                        "fillAlpha": 0.1,
                        "fillColor": "#0000cc",
                        "lineAlpha": 0,
                        "toValue": 100,	/* 背景从60——100 */
                        "value": 60
                    }]
                }],
                "graphs": [{
                    "bullet": "round",
                    "dashLength": 4,
                    "valueField": "value"
                }],
                "chartCursor": {
                    "cursorAlpha": 0
                },
                "categoryField": "date",
                "categoryAxis": {
                    "parseDates": true
                    /* ,"minPeriod" : "mm" */
                }
            });
        </script>
</head>
<body>
	<div id="chartdiv" style="width: 100%; height: 400px;"></div>
</body>
</html>