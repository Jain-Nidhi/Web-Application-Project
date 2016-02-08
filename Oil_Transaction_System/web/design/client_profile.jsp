<%-- 
    Document   : client_profile
    Created on : Nov 10, 2015, 7:23:36 PM
    Author     : Nidhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OTS-Client Profile</title>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu_style.css"/>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
		
                <script>
                $(document).ready(function(){
                        $.getJSON("../client_info", function(result){
                         //   $.each(result, function(i, field){
                         //      $("#jsonResults").append(field + " ");
                         //   });
                            $("#clilevel").append("["+result.level+"]");
                             $("#street").append(result.street);
                             $("#city").append(result.city);
                             $("#zip").append(result.zipcode);
                             $("#state").append(result.state);
                        });
                        
                        $.getJSON("../user_info", function(result){
                         //   $.each(result, function(i, field){
                         //      $("#jsonResults").append(field + " ");
                         //   });
                             $("#cliname").append(result.fname+" "+result.lname);
                            $("#cliuname").append("#"+result.uname);
                            $("#fname").append(result.fname);
                            $("#lname").append(result.lname);
                            $("#phone").append(result.phone);
                            $("#cellphone").append(result.cellphone);
                            $("#email").append(result.email);
                        });
                });
                </script>
    </head>

    <body style="margin: 0">
        <div id="header" style="width: 100%; height: 55pt">
             <img src="${pageContext.request.contextPath}/images/oil1.png" align="left" style="margin-left: 80pt"/>
            <table align="right" style="margin-right: 80pt">
                <tr>
                    <td style="width: 100pt">
                        <h3 style="color: steelblue;margin-bottom:  30pt">Client Profile</h3>
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
                                        <a class="sel" id="profile" href="client_profile.jsp">Profile</a>
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
                    <br><br><br>
                    <h3 style="margin-left: 35pt;margin-bottom: 1pt;margin-top: 2pt;color: steelblue">Profile</h3>
                                    
                                <div id="result" style="margin-left: 35pt;width: 90%;height: 350pt;border:1px solid silver">
                                <form action="client_profile_1.jsp" >
                                    <table align="left" cellspacing="20" style="margin-top: 10pt">
                                        <tr>
                                            <td valign="top">
                                                First Name
                                            </td>
                                            <td id="fname" valign="top" style="color: slategray"></td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Last Name
                                            </td>
                                            <td id="lname" valign="top" style="color: slategray"></td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Street
                                            </td>
                                            <td id="street" valign="top" style="color: slategray"></td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                City
                                            </td>
                                            <td id="city" valign="top" style="color: slategray"></td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                State
                                            </td>
                                            <td id="state" valign="top" style="color: slategray"></td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Zip code
                                            </td>
                                            <td id="zip" valign="top" style="color: slategray"></td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                E-mail Id
                                            </td>
                                            <td id="email" valign="top" style="color: slategray"></td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Phone no.
                                            </td>
                                            <td id="phone" valign="top" style="color: slategray"></td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Cellphone no.
                                            </td>
                                            <td id="cellphone" valign="top" style="color: slategray"></td>
                                        </tr>
                                        <tr>
                                            <td valign="top"><input class="button" type="submit" value="Edit"/></td>
                                            <td valign="top"></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                    
                </div>
            </div>
    </body>
</html>

