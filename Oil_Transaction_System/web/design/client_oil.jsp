<%-- 
    Document   : oil transaction
    Created on : Nov 10, 2015, 7:23:36 PM
    Author     : Nidhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OTS-Purchase Oil</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu_style.css"/>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
		
                <script>
                $(document).ready(function(){
                        $.getJSON("../client_info", function(result){
                         //   $.each(result, function(i, field){
                         //      $("#jsonResults").append(field + " ");
                         //   });
                            $("#clilevel").append("["+result.level+"]");
                        });
                        
                        $.getJSON("../user_info", function(result){
                         //   $.each(result, function(i, field){
                         //      $("#jsonResults").append(field + " ");
                         //   });
                             $("#cliname").append(result.fname+" "+result.lname);
                            $("#cliuname").append("#"+result.uname);
                        });
                        
                        $.getJSON("../fetch_activetransactions", function(result){
                            $.each(result, function(i, field){  
                                if(field.buyorsell===true)
                               $("#activeorder").append("<tr><td><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: green\">Order #"+field.tid.substring(0,8)+"/ Date:"+field.tdate.substring(0,12)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">Purchase = "+field.quantity+" barrel,Price = $"+(field.rate * field.quantity)+"</h5></td><td valign=\"bottom\"><form id=\"paycancel\" action=\"/Oil_Transaction_System/payment_oil\" ><input type=\"hidden\" name=\"tid\" value=\""+field.tid+"\" /><input name=\"opt\" class=\"button\" type=\"submit\" value=\"Pay\"  style=\"margin-left: 5pt\"/><input name=\"opt\" class=\"button\" type=\"submit\" value=\"Cancel\"  style=\"margin-left: 5pt\"/></form></td></tr><tr><td colspan=\"2\"><hr align=\"left\" style=\"margin-left: 10pt;width: 95%\"></td></tr>");
                             
                        });
                        });
                        
                        $.getJSON("../fetch_inactivetransactions", function(result){
                            $.each(result, function(i, field){
                                if(field.buyorsell===true)
                               $("#processedorder").append("<tr><td><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: green\">Order #"+field.tid.substring(0,8)+"/ Date:"+field.tdate.substring(0,12)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">Purchase = "+field.quantity+" barrel,Price = $"+(field.rate * field.quantity)+"</h5></td><td valign=\"bottom\"><form id=\"clear\" action=\"/Oil_Transaction_System/payment_oil\" ><input type=\"hidden\" name=\"tid\" value=\""+field.tid+"\"/><input name=\"opt3\" class=\"button\" type=\"submit\" value=\"Clear\"  style=\"margin-left: 10pt\"/></form></td></tr><tr><td colspan=\"2\"><hr align=\"left\" style=\"margin-left: 10pt;width: 95%\"></td></tr>");
                        });
                        });
                });
                function calculate(){
                  $.getJSON("../month_info", function(result){
                                $("#totalprice").text("@ $"+result.currpur+"/barrel");
                                $("#totalcomm").text("@ "+result.commissionrate+"% of price");
                        });   
                        
                }
                </script>
    </head>

    <body style="margin: 0">
        <div id="header" style="width: 100%; height: 55pt">
             <img src="${pageContext.request.contextPath}/images/oil1.png" align="left" style="margin-left: 80pt"/>
            <table align="right" style="margin-right: 80pt">
                <tr>
                    <td style="width: 170pt">
                        <h3 style="color: steelblue;margin-bottom:  30pt">Oil Transactions [Buy]</h3>
                    </td>
                    <td>
                        <div style="background-color:  lightgray;height: 30pt;width: 1pt;margin-right: 10pt"></div>
                    </td>
                    <td style="width: 120pt">
                        <h3 id="cliname" style="color: steelblue;margin-bottom:  0pt"></h3>
                        <h4 id="cliuname" style="color: steelblue;margin-top: 0pt"></h4>
                    </td>
                    <td style="width: 70pt">
                        <h4 id="clilevel" style="color: steelblue;margin-top: 0pt"></h4>
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
                                        <a id="home" href="client_dashboard.jsp">Home</a>
                                    </li>
                                    <li>
                                        <a id="profile" href="client_profile.jsp">Profile</a>
                                    </li>
                                    <li>
                                        <a class="sel" id="oil" href="client_oil.jsp">Oil Transaction</a>
                                    </li>
                                    <li>
                                        <a id="pay" href="paymenthistory.jsp">Payment History</a>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    </table>
                    <br><br><br>
                                <ul>
                                   <li>
                                        <a id="buyoil" class="sel" href="client_oil.jsp">Purchase Oil</a>
                                    </li>
                                    <li>
                                        <a id="selloil" href="selloil.jsp">Sell Oil</a>
                                    </li>
                                    <li>
                                        <h3 style="margin-left: 180pt;margin-bottom: 1pt;margin-top: 2pt;color: steelblue">Active Orders</h3>
                                    </li>
                                    <li>
                                        <h3 style="margin-left: 195pt;margin-bottom: 1pt;margin-top: 2pt;color: steelblue">Order History</h3>
                                    </li>
                                </ul>
                    <br>
                    <table style="width:97%">
                        <tr>
                            <td style="width:35%">
                                <div id="result" style="margin-left: 20pt;width: 100%;height: 350pt;border:1px solid silver">
                                <form id="buyform" action="/Oil_Transaction_System/buysell_oil" >
                                    <table align="left" cellspacing="20" style="margin-top: 30pt">
                                        <tr>
                                            <td valign="top">
                                                Oil Amount
                                            </td>
                                            <td valign="top"><input type="text" name="oilamount" style="width: 100pt" onkeypress="calculate()" ></td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Purchase Rate
                                            </td>
                                            <td valign="top">
                                                <h4 id="totalprice" style="margin-top: 0pt; margin-bottom: 0pt; color: green"></h4>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Commission Rate
                                            </td>
                                            <td valign="top">
                                                <h4 id="totalcomm" style="margin-top: 0pt; margin-bottom: 0pt; color: green"></h4>
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
                                            <td valign="top"><input name="opt" class="button" type="submit" value="Purchase"/></td>
                                            <td valign="top"></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            </td>
                            <td style="width: 30%">
                            <div  style="margin-left: 10pt;width: 100%;height: 350pt;border:1px solid silver;background-color: whitesmoke">
                            
                                <table class="scrollable" id="activeorder"  >
                                    
                                </table>
                               
                            </div>
                            </td>
                            <td style="width: 25%">
                            <div style="margin-left: 10pt;width: 100%;height: 350pt;border:1px solid silver;background-color: whitesmoke">
                            
                                
                                <table class="scrollable" id="processedorder" >
                                    
                                </table>
                                
                            </div>
                            </td>
                        </tr>
                    </table>
                    
                    
                </div>
            </div>
    </body>
</html>
