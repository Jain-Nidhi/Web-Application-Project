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
        <title>OTS-Client Dashboard</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu_style.css"/>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
		
                <script>
                $(document).ready(function(){
                        $.getJSON("../client_info", function(result){
                         //   $.each(result, function(i, field){
                         //      $("#jsonResults").append(field + " ");
                         //   });
                            $("#clilevel").append("["+result.level+"]");
                             $("#accountoil").append(result.stock+" barrel");
                             if(result.level==="Silver")
                            {
                                $("#upgradeT").append("Upgrade to *Gold*");
                                $("#upgradeM").append("Trade 30 Barrels in a month & upgrade to Gold customer");
                            }
                            else
                            {
                                $("#upgradeT").append("Gold Customer *Bonus*");
                                $("#upgradeM").append("As you are our Gold customer, you can trade more Oil for less commission rates of 5%");
                            }
                        });
                        
                        $.getJSON("../user_info", function(result){
                         //   $.each(result, function(i, field){
                         //      $("#jsonResults").append(field + " ");
                         //   });
                             $("#cliname").append(result.fname+" "+result.lname);
                            $("#cliuname").append("#"+result.uname);
                        });
                        
                        $.getJSON("../month_info", function(result){
                          $("#monthsummary").append("Current month: Purchase = "+result.purchase+" barrel , Sale = "+result.sale+" barrel");
                          $("#currrates").append("Purchase @ $"+result.currpur+"/barrel<br>Sell @ $"+result.currsale+"/barrel");
                          
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
                        <h3 style="color: steelblue;margin-bottom:  30pt">Client Dashboard</h3>
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
                    
                    <table style="margin:0pt">
                        <tr>
                            <td>
                                <ul>
                                    <li>
                                        <a class="sel" id="home" href="client_dashboard.jsp">Home</a>
                                    </li>
                                    <li>
                                        <a id="profile" href="client_profile.jsp">Profile</a>
                                    </li>
                                    <li>
                                        <a id="oil" href="client_oil.jsp">Oil Transaction</a>
                                    </li>
                                    <li>
                                        <a id="pay" href="paymenthistory.jsp">Payment History</a>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table cellspacing="30" style="margin-left: 10pt;width: 97%">
                    <tr>
                        
                        <td style="width: 35%;height: 100pt;background-color: whitesmoke">
                            <h4 style="margin-left: 7pt;margin-bottom: 3pt;margin-top: 2pt;color: steelblue">Oil in Account</h4>
                            <div id="jsonResults" style="margin-top: 0pt; margin-left: 7pt; width: 95%;height: 70pt;background-color: white;border:1px solid silver">
                             <h3 id="accountoil" style="margin-left: 10pt;margin-bottom: 3pt;margin-top: 15pt;color: green"></h3>
                             <h5 id="monthsummary" style="margin-left: 10pt;margin-bottom: 3pt;margin-top: 10pt;color: slategray"></h5>
                            
                            
                            </div>
                        </td>
                        <td style="width: 35%;height: 100pt;background-color: whitesmoke">
                            <h4 style="margin-left: 7pt;margin-bottom: 3pt;margin-top: 2pt;color: steelblue">Oil rates: 11/14/2015</h4>
                            <div style="margin-top: 0pt; margin-left: 7pt; width: 95%;height: 70pt;background-color: white;border:1px solid silver">
                             
                            <h5 id="currrates" style="margin-left: 10pt;margin-bottom: 3pt;margin-top: 10pt;color: slategray"></h5>
                            </div>
                        </td>
                        
                    </tr>
                    
                    <tr>
                        
                        <td style="width: 35%;height: 70pt;background-color: whitesmoke">
                            
                                <table style="margin-top: 10pt;margin-bottom: 10pt; margin-left: 8pt;width: 90%;height: 90%">
                                    <tr>
                                        
                                        <td style="width: 45%">
                                            <form style="height: 100%" action="client_oil.jsp" >
                                            <input class="button" type="submit" style="width: 100%; height: 100%" value="Purchase Oil"/>
                                            </form>
                                            </td>
                                        <td style="width: 45%">
                                            <form style="height: 100%" action="selloil.jsp" >
                                            <input class="button" type="submit" style="width: 100%; height: 100%;margin-left: 10pt" value="Sell Oil"/>
                                            </form>
                                            </td>
                                       
                                    </tr>
                                </table>
                        </td>

                    </tr>
                    <tr>
                       
                        <td colspan="2" style="width: 35%;height: 100pt;background-color: whitesmoke">
                           <div style="margin-top: 0pt; margin-left: 7pt; width: 95%;height: 70pt;background-color: white;border:1px solid silver">
                                <h3 id="upgradeT" style="margin-left: 10pt;margin-bottom: 3pt;margin-top: 15pt;color: green"></h3>
                            <h5 id="upgradeM" style="margin-left: 10pt;margin-bottom: 3pt;margin-top: 10pt;color: slategray"></h5>
                            
                            </div>
                        </td>
                        
                    </tr>
                </table>
                
                </div>
                
            </div>
    </body>
</html>
