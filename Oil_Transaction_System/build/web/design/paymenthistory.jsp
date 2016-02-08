<%-- 
    Document   : payments
    Created on : Nov 10, 2015, 7:23:36 PM
    Author     : Nidhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OTS-Client Payments</title>
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
                        
                        $.getJSON("../fetch_paidpayments", function(result){
                            $.each(result, function(i, field){
                               $("#paymenthistory").append("<tr><td><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: green\">#"+field.paymentid.substring(0,8)+"</h5><h5 style=\"margin-left: 10pt;margin-bottom: 3pt;margin-top: 5pt;color: slategrey\">Order = #"+field.tid.substring(0,8)+" ,     Paid = $"+field.amount+"</h5></td><td valign=\"bottom\"><form id=\"clear\" action=\"/Oil_Transaction_System/payment_oil\" ><input type=\"hidden\" name=\"payid\" value=\""+field.paymentid+"\" /><input name=\"opt2\" class=\"button\" type=\"submit\" value=\"Clear\"  style=\"margin-left: 20pt\"/></form></td></tr><tr><td colspan=\"2\"><hr align=\"left\" style=\"margin-left: 10pt;width: 95%\"></td></tr>");
                        });
                        });
                });
                </script>
    </head>

    <body style="margin: 0">
        <div id="header" style="width: 100%; height: 55pt">
             <img src="${pageContext.request.contextPath}/images/oil1.png" align="left" style="margin-left: 80pt"/>
            <table align="right" style="margin-right: 80pt">
                <tr>
                    <td style="width: 115pt">
                        <h3 style="color: steelblue;margin-bottom:  30pt">Payment History</h3>
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
                                        <a id="oil" href="client_oil.jsp">Oil Transaction</a>
                                    </li>
                                    <li>
                                        <a class="sel" id="pay" href="paymenthistory.jsp">Payment History</a>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    </table>
                    <br><br><br>
                                <ul>
                                    <li>
                                        <h3 style="margin-left: 5pt;margin-bottom: 1pt;margin-top: 2pt;color: steelblue">Payment History</h3>
                                    </li>
                                </ul>
                    <br>
                    <br>
                    <table style="margin-left: 20pt;width:28%" align="left">
                        <tr>
                            <td style="margin-left: 0pt;width: 100%">
                            <div style="margin-left: 10pt;width: 100%;height: 350pt;border:1px solid silver;background-color: whitesmoke">
                            
                                <table class="scrollable" id="paymenthistory" >
                                    
                                </table>
                                
                            </div>
                            </td>
                        </tr>
                    </table>
                    
                    
                </div>
            </div>
    </body>
</html>
