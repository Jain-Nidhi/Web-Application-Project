<%-- 
    Document   : client_dashboard
    Created on : Nov 10, 2015, 7:23:36 PM
    Author     : Nidhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OTS-Manager Dashboard</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu_style.css"/>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/fusioncharts.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/fusion.charts.js" type="text/javascript"></script>
                <script>
                $(document).ready(function(){
                        $.getJSON("../user_info", function(result){
                         //   $.each(result, function(i, field){
                         //      $("#jsonResults").append(field + " ");
                         //   });
                             $("#cliname").append(result.fname+" "+result.lname);
                            $("#cliuname").append("#"+result.uname);
                        });
                    
        $.getJSON("../daygraph", function(result){
            var lb=[0,0,0,0,0,0,0,0,0,0];
            var vl=[0,0,0,0,0,0,0,0,0,0]
            var j=0;
            
                         $.each(result, function(i, field){
                             lb[j]=field.label.substring(5,10);
                             vl[j]=field.value;
                             j++;
                            });
                         var revenueChart = new FusionCharts({
        type: 'column2d',
        renderAt: 'graph1',
        width: '450',
        height: '250',
        dataFormat: 'json',
        dataSource: {
            "chart": {
                "caption": "Last 10 days transactions",
                "subCaption": "",
                "xAxisName": "Day",
                "yAxisName": "No. of Oil Transactions",
                "numberPrefix": "",
                "paletteColors": "#0075c2",
                "bgColor": "#ffffff",
                "borderAlpha": "20",
                "canvasBorderAlpha": "0",
                "usePlotGradientColor": "0",
                "plotBorderAlpha": "10",
                "placevaluesInside": "1",
                "rotatevalues": "1",
                "valueFontColor": "#ffffff",                
                "showXAxisLine": "1",
                "xAxisLineColor": "#999999",
                "divlineColor": "#999999",               
                "divLineIsDashed": "1",
                "showAlternateHGridColor": "0",
                "subcaptionFontBold": "0",
                "subcaptionFontSize": "14"
            },            
            "data": [
                {
                    "label":lb[0],
                    "value":vl[0]
                }, 
                {
                    "label":lb[1],
                    "value":vl[1]
                }, 
                {
                    "label":lb[2],
                    "value":vl[2]
                }, 
                {
                    "label":lb[3],
                    "value":vl[3]
                }, 
                {
                    "label":lb[4],
                    "value":vl[4]
                }, 
                {
                    "label":lb[5],
                    "value":vl[5]
                }, 
                {
                    "label":lb[6],
                    "value":vl[6]
                },
                {
                    "label":lb[7],
                    "value":vl[7]
                }, 
                {
                    "label":lb[8],
                    "value":vl[8]
                }, 
                {
                    "label":lb[9],
                    "value":vl[9]
                }
            ],
            "trendlines": [
                {
                    "line": [
                        {
                            "startvalue": "50",
                            "color": "#1aaf5d",
                            "valueOnRight": "1",
                            "displayvalue": "Daily Target"
                        }
                    ]
                }
            ]
        }
    }).render();
                        });
                        
                        
                        
                        
        $.getJSON("../weekgraph", function(result){
            var lb=[0,0,0,0,0];
            var vl=[0,0,0,0,0];
            
                         $.each(result, function(i, field){
                             lb[field.label-1]=field.label;
                             vl[field.label-1]=field.value;
                            });
                         var revenueChart = new FusionCharts({
        type: 'column2d',
        renderAt: 'graph2',
        width: '450',
        height: '250',
        dataFormat: 'json',
        dataSource: {
            "chart": {
                "caption": "Last 5 Weeks transactions",
                "subCaption": "",
                "xAxisName": "Week",
                "yAxisName": "No. of Oil Transactions",
                "numberPrefix": "",
                "paletteColors": "#0075c2",
                "bgColor": "#ffffff",
                "borderAlpha": "20",
                "canvasBorderAlpha": "0",
                "usePlotGradientColor": "0",
                "plotBorderAlpha": "10",
                "placevaluesInside": "1",
                "rotatevalues": "1",
                "valueFontColor": "#ffffff",                
                "showXAxisLine": "1",
                "xAxisLineColor": "#999999",
                "divlineColor": "#999999",               
                "divLineIsDashed": "1",
                "showAlternateHGridColor": "0",
                "subcaptionFontBold": "0",
                "subcaptionFontSize": "14"
            },            
            "data": [
                {
                    "label":"1",
                    "value":vl[0]
                }, 
                {
                    "label":"2",
                    "value":vl[1]
                }, 
                {
                    "label":"3",
                    "value":vl[2]
                }, 
                {
                    "label":"4",
                    "value":vl[3]
                }, 
                {
                    "label":"5",
                    "value":vl[4]
                } 
                
            ],
            "trendlines": [
                {
                    "line": [
                        {
                            "startvalue": "50",
                            "color": "#1aaf5d",
                            "valueOnRight": "1",
                            "displayvalue": "Daily Target"
                        }
                    ]
                }
            ]
        }
    }).render();
                        });
                        
           $.getJSON("../monthgraph", function(result){
            var lb=[0,0,0,0,0,0,0,0,0,0,0,0];
            var vl=[0,0,0,0,0,0,0,0,0,0,0,0];
            
                         $.each(result, function(i, field){
                             lb[field.label-1]=field.label;
                             vl[field.label-1]=field.value;
                            });
                         var revenueChart = new FusionCharts({
        type: 'column2d',
        renderAt: 'graph3',
        width: '450',
        height: '250',
        dataFormat: 'json',
        dataSource: {
            "chart": {
                "caption": "Monthly Transactions in this Year",
                "subCaption": "",
                "xAxisName": "Month",
                "yAxisName": "No. of Oil Transactions",
                "numberPrefix": "",
                "paletteColors": "#0075c2",
                "bgColor": "#ffffff",
                "borderAlpha": "20",
                "canvasBorderAlpha": "0",
                "usePlotGradientColor": "0",
                "plotBorderAlpha": "10",
                "placevaluesInside": "1",
                "rotatevalues": "1",
                "valueFontColor": "#ffffff",                
                "showXAxisLine": "1",
                "xAxisLineColor": "#999999",
                "divlineColor": "#999999",               
                "divLineIsDashed": "1",
                "showAlternateHGridColor": "0",
                "subcaptionFontBold": "0",
                "subcaptionFontSize": "14"
            },            
            "data": [
                {
                    "label":"1",
                    "value":vl[0]
                }, 
                {
                    "label":"2",
                    "value":vl[1]
                }, 
                {
                    "label":"3",
                    "value":vl[2]
                }, 
                {
                    "label":"4",
                    "value":vl[3]
                },
                {
                    "label":"5",
                    "value":vl[4]
                }, 
                {
                    "label":"6",
                    "value":vl[5]
                }, 
                {
                    "label":"7",
                    "value":vl[6]
                },
                {
                    "label":"8",
                    "value":vl[7]
                }, 
                {
                    "label":"9",
                    "value":vl[8]
                }, 
                {
                    "label":"10",
                    "value":vl[9]
                }, 
                {
                    "label":"11",
                    "value":vl[10]
                }, 
                {
                    "label":"12",
                    "value":vl[11]
                }
                
            ],
            "trendlines": [
                {
                    "line": [
                        {
                            "startvalue": "50",
                            "color": "#1aaf5d",
                            "valueOnRight": "1",
                            "displayvalue": "Daily Target"
                        }
                    ]
                }
            ]
        }
    }).render();
                        });
                        
            $.getJSON("../yeargraph", function(result){
            var lb=[0,0,0,0,0];
            var vl=[0,0,0,0,0];
            var j=0;
                         $.each(result, function(i, field){
                             lb[j]=field.label;
                             vl[j]=field.value;
                             j++;
                            });
                         var revenueChart = new FusionCharts({
        type: 'column2d',
        renderAt: 'graph4',
        width: '450',
        height: '250',
        dataFormat: 'json',
        dataSource: {
            "chart": {
                "caption": "Last 5 Year Transactions",
                "subCaption": "",
                "xAxisName": "Year",
                "yAxisName": "No. of Oil Transactions",
                "numberPrefix": "",
                "paletteColors": "#0075c2",
                "bgColor": "#ffffff",
                "borderAlpha": "20",
                "canvasBorderAlpha": "0",
                "usePlotGradientColor": "0",
                "plotBorderAlpha": "10",
                "placevaluesInside": "1",
                "rotatevalues": "1",
                "valueFontColor": "#ffffff",                
                "showXAxisLine": "1",
                "xAxisLineColor": "#999999",
                "divlineColor": "#999999",               
                "divLineIsDashed": "1",
                "showAlternateHGridColor": "0",
                "subcaptionFontBold": "0",
                "subcaptionFontSize": "14"
            },            
            "data": [
                {
                    "label":lb[0],
                    "value":vl[0]
                }, 
                {
                    "label":lb[1],
                    "value":vl[1]
                }, 
                {
                    "label":lb[2],
                    "value":vl[2]
                }, 
                {
                    "label":lb[3],
                    "value":vl[3]
                },
                {
                    "label":lb[4],
                    "value":vl[4]
                }
                
            ],
            "trendlines": [
                {
                    "line": [
                        {
                            "startvalue": "50",
                            "color": "#1aaf5d",
                            "valueOnRight": "1",
                            "displayvalue": "Daily Target"
                        }
                    ]
                }
            ]
        }
    }).render();
                        });
                        
        
                });
                </script>
    </head>

    <body style="margin: 0">
        <div id="header" style="width: 100%; height: 55pt">
             <img src="${pageContext.request.contextPath}/images/oil1.png" align="left" style="margin-left: 80pt"/>
            <table align="right" style="margin-right: 80pt">
                <tr>
                    <td style="width: 130pt">
                        <h3 style="color: steelblue;margin-bottom:  30pt">Manager Dashboard</h3>
                    </td>
                    <td>
                        <div style="background-color:  lightgray;height: 30pt;width: 1pt;margin-right: 10pt"></div>
                    </td>
                    <td style="width: 120pt">
                        <h3 id="cliname" style="color: steelblue;margin-bottom:  0pt"></h3>
                        <h4 id="cliuname" style="color: steelblue;margin-top: 0pt"></h4>
                    </td>
                    <td>
                        <form id="logout" action="/Oil_Transaction_System/login_logout">
                            <table align="right">
                                <tr style="height: 10pt"><td></td></tr>
                                <tr>
                                    <td><input class="button" type="submit" value="Logout"/><input type="hidden" name="opt1" value="logout"></td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
        
            <div style="width: 100%; height: 442pt; background-color: lavender"> 
                <div style="width: 85%;height: 1pt;background-color: lavender;margin-top: 0pt;margin-bottom: 0pt;margin-left: 80pt"></div>
                <div style="width: 85%; height: 440pt; background-color: white;margin-left: 80pt">
                <br>
                                <ul>
                                    <li>
                                        <h3 style="margin-left: 5pt;margin-bottom: 1pt;margin-top: 2pt;color: steelblue">Statistical Graph</h3>
                                    </li>
                                </ul>
                    <br>
                    <table style="width:97%">
                        <tr>
                            <td style="width: 45%">
                            
                            <div id="graph1" style="margin-left: 30pt;width: 90%;height: 200pt">
                                
                            </div>
                            </td>
                            <td style="width: 45%">
                            
                            <div id="graph2" style="width: 90%;height: 200pt">
                                
                            </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 45%">
                            
                            <div id="graph3" style="margin-left: 30pt;width: 90%;height: 200pt">
                                
                            </div>
                            </td>
                            <td style="width: 45%">
                            
                            <div id="graph4" style="width: 90%;height: 200pt">
                                
                            </div>
                            </td>
                        </tr>
                    </table>
                    
                    
                </div>
            </div>
    </body>
</html>