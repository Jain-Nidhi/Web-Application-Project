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
        <title>OTS-Trader Dashboard</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu_style.css"/>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
		
                <script>
                $(document).ready(function(){
                        $.getJSON("../user_info", function(result){
                         //   $.each(result, function(i, field){
                         //      $("#jsonResults").append(field + " ");
                         //   });
                             $("#cliname").append(result.fname+" "+result.lname);
                            $("#cliuname").append("#"+result.uname);
                        });
                        
                         $.getJSON("../fetch_traderactive", function(result){
                            $.each(result, function(i, field){  
                               if(field.buyorsell===true)
                               $("#activebuy").append("<tr><td><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: green\">Order #"+field.tid.substring(0,8)+" / Client #"+field.clientid.substring(0,8)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">"+field.tdate.substring(0,12)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">Purchase = "+field.quantity+" barrel,Price = $"+(field.rate * field.quantity)+"</h5></td><td valign=\"bottom\"><form id=\"paycancel\" action=\"/Oil_Transaction_System/payment_oil\" ><input type=\"hidden\" name=\"tid\" value=\""+field.tid+"\" /><input name=\"opt5\" class=\"button\" type=\"submit\" value=\"Cancel\"  style=\"margin-left: 5pt\"/></form></td></tr><tr><td colspan=\"2\"><hr align=\"left\" style=\"margin-left: 10pt;width: 95%\"></td></tr>");
                                else
                               $("#activesell").append("<tr><td><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: green\">Order #"+field.tid.substring(0,8)+" / Client #"+field.clientid.substring(0,8)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">"+field.tdate.substring(0,12)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">Sale = "+field.quantity+" barrel,Price = $"+(field.rate * field.quantity)+"</h5></td><td valign=\"bottom\"><form id=\"paycancel\" action=\"/Oil_Transaction_System/payment_oil\" ><input type=\"hidden\" name=\"tid\" value=\""+field.tid+"\" /><input name=\"opt5\" class=\"button\" type=\"submit\" value=\"Cancel\"  style=\"margin-left: 5pt\"/></form></td></tr><tr><td colspan=\"2\"><hr align=\"left\" style=\"margin-left: 10pt;width: 95%\"></td></tr>");
                                   
                                     });
                        });
                        
                        $.getJSON("../fetch_traderinactive", function(result){
                            $.each(result, function(i, field){
                               if(field.buyorsell===true)
                               $("#inactivebuy").append("<tr><td><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: green\">Order #"+field.tid.substring(0,8)+" / Client #"+field.clientid.substring(0,8)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">"+field.tdate.substring(0,12)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">Purchase = "+field.quantity+" barrel,Price = $"+(field.rate * field.quantity)+"</h5></td><td valign=\"bottom\"><form id=\"clear\" action=\"/Oil_Transaction_System/payment_oil\" ><input type=\"hidden\" name=\"tid\" value=\""+field.tid+"\" /><input name=\"opt5\" class=\"button\" type=\"submit\" value=\"Clear\"  style=\"margin-left: 5pt\"/></form></td></tr><tr><td colspan=\"2\"><hr align=\"left\" style=\"margin-left: 10pt;width: 95%\"></td></tr>");
                                else
                               $("#inactivesell").append("<tr><td><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: green\">Order #"+field.tid.substring(0,8)+" / Client #"+field.clientid.substring(0,8)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">"+field.tdate.substring(0,12)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">Sale = "+field.quantity+" barrel,Price = $"+(field.rate * field.quantity)+"</h5></td><td valign=\"bottom\"><form id=\"clear\" action=\"/Oil_Transaction_System/payment_oil\" ><input type=\"hidden\" name=\"tid\" value=\""+field.tid+"\" /><input name=\"opt5\" class=\"button\" type=\"submit\" value=\"Clear\"  style=\"margin-left: 5pt\"/></form></td></tr><tr><td colspan=\"2\"><hr align=\"left\" style=\"margin-left: 10pt;width: 95%\"></td></tr>");
                          });
                        });
                });
                
              
                function abc(){
                    $.getJSON("../comm_info", function(result){
                          $("#oilrate").text("@ $"+result.currpur+"/barrel");
                        });
                }
                
                function abc1(){
                    $.getJSON("../comm_info", function(result){
                          $("#oilrate").text("@ $"+result.currsale+"/barrel");
                        });
                }
                </script>
    </head>

    <body style="margin: 0">
        <div id="header" style="width: 100%; height: 55pt">
             <img src="${pageContext.request.contextPath}/images/oil1.png" align="left" style="margin-left: 80pt"/>
            <table align="right" style="margin-right: 80pt">
                <tr>
                    <td style="width: 130pt">
                        <h3 style="color: steelblue;margin-bottom:  30pt">Trader Dashboard</h3>
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
                    
                    <table align="left" style="margin:0pt">
                        <tr>
                            <td>
                                <ul>
                                    
                                    <li>
                                        <a id="oil" class="sel" href="trader_dashboard.jsp">Oil Transaction</a>
                                    </li>
                                    <li>
                                        <a id="pay" href="trader_payment.jsp">Client Payments</a>
                                    </li>
                                    <li>
                                        <a id="clienthistory" href="trader_client.jsp">Client Search</a>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    </table>
               <br><br><br>
                                <ul>
                                   <li>
                                        <h3 style="margin-bottom: 1pt;margin-top: 2pt;color: steelblue">Issue Oil Transaction</h3>
                                    </li>
                                    <li>
                                        <h3 style="margin-left: 200pt;margin-bottom: 1pt;margin-top: 2pt;color: steelblue">Unpaid Orders[Buy]</h3>
                                    </li>
                                    <li>
                                        <h3 style="margin-left: 130pt;margin-bottom: 1pt;margin-top: 2pt;color: steelblue">Unpaid Orders[Sell]</h3>
                                    </li>
                                </ul>
                    <br>
                    <table style="width:97%">
                        <tr>
                            <td style="width:20%">
                                <div id="result" style="margin-left: 25pt;width: 95%;height: 350pt;border:1px solid silver">
                                <form action="/Oil_Transaction_System/traderbuysell_oil" >
                                    <table align="left" cellspacing="20" style="margin-top: 30pt">
                                        
                                        <tr>
                                            <td valign="top">
                                                Client Username/E-mail
                                            </td>
                                            <td valign="top"><input type="text" id="clientmail" name="clientmail" style="width: 100pt"></td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Oil Amount
                                            </td>
                                            <td valign="top"><input type="text" id="oilamount" name="oilamount" style="width: 100pt"></td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Choose action
                                            </td>
                                            <td valign="top">
                                                <input id="buysell" type="radio" name="buysell" value="buy" onclick="abc()"/>Purchase<input id="buysell1" type="radio" name="buysell" value="sell" onclick="abc1()"/>Sell
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Oil Rate
                                            </td>
                                            <td valign="top">
                                                <h4 id="oilrate" style="margin-top: 0pt; margin-bottom: 0pt; color: green"></h4>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Mode of commission payment
                                            </td>
                                            <td valign="top">
                                                <input type="radio" name="mode" value="cash"/>cash<input type="radio" name="mode" value="oil"/>oil
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top"><input class="button" type="submit" value="Confirm order"/></td>
                                            <td valign="top"></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            </td>
                            <td style="width: 19%">
                            
                             <div style="margin-left: 10pt;width: 100%;height: 157pt;border:1px solid silver; background-color: whitesmoke">
                                 <table class="scrollable" id="activebuy" style="height:157pt">
                                    
                                </table>
                            </div>
                                <br>
                            <h3 style="margin-left: 10pt;background-color: white;width: 100%;margin-bottom: 1pt;margin-top: 2pt;color: steelblue">Paid Orders[Buy]</h3>
                            <div style="margin-left: 10pt;width: 100%;height: 157pt;border:1px solid silver; background-color: whitesmoke">
                                <table class="scrollable" id="inactivebuy"  style="height:157pt">
                                    
                                </table>
                            </div>
                            </td>
                            <td style="width: 19%">
                             <div style="margin-left: 10pt;width: 100%;height: 157pt;border:1px solid silver; background-color: whitesmoke">
                                <table class="scrollable" id="activesell"  style="height:157pt">
                                    
                                </table>
                            </div><br>
                            <h3 style="margin-left: 10pt;background-color: white;width: 100%;margin-bottom: 1pt;margin-top: 2pt;color: steelblue">Paid Orders[Sell]</h3>
                            <div style="margin-left: 10pt;width: 100%;height: 157pt;border:1px solid silver; background-color: whitesmoke">
                                <table class="scrollable" id="inactivesell"  style="height:157pt">
                                    
                                </table>
                            </div>
                            </td>
                        </tr>
                    </table>
                    
                    
                </div>
            </div>
    </body>
</html>
