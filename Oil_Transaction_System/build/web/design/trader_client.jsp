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
        <title>OTS-Client Search</title>
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
                        
                         $.getJSON("../display_client_pool", function(result){
                         $.each(result, function(i, field){
                               $("#clientpool").append("<tr><td><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 15pt;color: green\">ClientId #"+field.clientid.substring(0,8)+" / "+field.name+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">"+field.address+"</h5></td><td valign=\"bottom\"><form id=\"history\" action=\"/Oil_Transaction_System/gethistory\" ><input class=\"button\" type=\"submit\" value=\"View history\"   style=\"margin-left: 20pt\" /><input type=\"hidden\" name=\"clientid\" value=\""+field.clientid+"\" /></form></td></tr><tr><hr align=\"left\" style=\"margin-left: 10pt;width: 90%\"></tr>");
                            });
                        });
                        
                         $.getJSON("../display_client_history", function(result){
                            $.each(result, function(i, field){  
                                if(field.buyorsell===true)
                               $("#displayhistory").append("<tr><td><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: green\">Order #"+field.tid.substring(0,8)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">Purchase = "+field.quantity+" barrel,Price = $"+(field.rate * field.quantity)+"</h5></td><td valign=\"bottom\"><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">"+field.tdate.substring(0,12)+"</h5></td></tr><tr><td colspan=\"2\"><hr align=\"left\" style=\"margin-left: 10pt;width: 95%\"></td></tr>");
                                else
                                $("#displayhistory").append("<tr><td><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: green\">Order #"+field.tid.substring(0,8)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">Sale = "+field.quantity+" barrel,Price = $"+(field.rate * field.quantity)+"</h5></td><td valign=\"bottom\"><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">"+field.tdate.substring(0,12)+"</h5></td></tr><tr><td colspan=\"2\"><hr align=\"left\" style=\"margin-left: 10pt;width: 95%\"></td></tr>");
                                    
                        });
                        });
                        
                });
                /*Client#001565 / Nidhi Jain
                 * 17817, Coit Rd, Dallas*/
                
                
                </script>
    </head>

    <body style="margin: 0">
        <div id="header" style="width: 100%; height: 55pt">
             <img src="${pageContext.request.contextPath}/images/oil1.png" align="left" style="margin-left: 80pt"/>
            <table align="right" style="margin-right: 80pt">
                <tr>
                    <td style="width: 130pt">
                        <h3 style="color: steelblue;margin-bottom:  30pt">Client Search</h3>
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
                                        <a id="oil" href="trader_dashboard.jsp">Oil Transaction</a>
                                    </li>
                                    <li>
                                        <a id="pay" href="trader_payment.jsp">Handled Payments</a>
                                    </li>
                                    <li>
                                        <a id="clienthistory"  class="sel" href="trader_client.jsp">Client Search</a>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    </table>
               <br><br><br>
                               <ul>
                                   <li>
                                        <h3 style="margin-bottom: 1pt;margin-top: 2pt;color: steelblue">1. Enter Client Info</h3>
                                    </li>
                                    <li>
                                        <h3 style="margin-left: 138pt;margin-bottom: 1pt;margin-top: 2pt;color: steelblue">2. Select Client</h3>
                                    </li>
                                    <li>
                                        <h3 style="margin-left: 148pt;margin-bottom: 1pt;margin-top: 2pt;color: steelblue">3. View Client History</h3>
                                    </li>
                                </ul>
                    <br>
                    <table style="width:97%">
                        <tr>
                            <td style="width:15%">
                                <div id="result" style="margin-left: 25pt;width: 95%;height: 350pt;border:1px solid silver">
                                <form action="/Oil_Transaction_System/searchclients" >
                                    <table align="left" cellspacing="20" style="margin-top: 10pt">
                                        
                                        <tr>
                                            <td valign="top" colspan="2">
                                                <input type="radio" name="mode" value="name"/>Client name<input type="radio" name="mode" value="email"/>Email<input type="radio" name="mode" value="address"/>Address
                                           </td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Enter Value
                                            </td>
                                            <td valign="top"><input type="text" name="searchvalue" id="searchvalue" style="width: 100pt"></td>
                                        </tr>
                                        <tr>
                                            <td valign="top"><input class="button" type="submit" value="Search"/></td>
                                            <td valign="top"></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            </td>
                            <td style="width: 20%">
                            
                             <div style="margin-left: 10pt;width: 100%;height: 350pt;border:1px solid silver; background-color: whitesmoke">
                                 <table id="clientpool" class="scrollable" style="height:350pt">
                                    
                                </table>
                                
                            </div>
                            </td>
                            <td style="width: 30%">
                            
                             <div style="margin-left: 10pt;width: 100%;height: 350pt;border:1px solid silver">
                                <table id="displayhistory" class="scrollable" style="height:350pt">
                                    
                                </table>
                            </div>
                            </td>
                        </tr>
                    </table>
                    
                    
                </div>
            </div>
    </body>
</html>
