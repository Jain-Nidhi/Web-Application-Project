<%-- 
    Document   : main
    Created on : Nov 10, 2015, 7:23:36 PM
    Author     : Nidhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Oil Transaction System</title>
    </head>

    <body style="margin: 0">
        <div id="header" style="width: 100%; height: 55pt">
            <img src="${pageContext.request.contextPath}/images/oil1.png" align="left" style="margin-left: 80pt"/>
            
            <div align="right" style="margin-right: 80pt">
                <form name="login" action="/Oil_Transaction_System/login_logout" method="post">
                <table align="right">
                    <tr style="height: 5pt"><td></td></tr>
                    <tr>
                        <td align="left">User-Id</td>
                        <td align="left">Password</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="uname" style="width: 100pt"></td>
                        <td><input type="password" name="pwd" style="width: 100pt"></td>
                        <td><input type="submit" value="Login"/><input type="hidden" name="opt1" value="login"></td>
                    </tr>
                </table>
                </form>
            </div>
        </div>
        
            <div id="content" style="width: 100%; height: 440pt; background-color: lavender"> 
                <br><br>
                <table align="center" style="vertical-align: middle">
                    <tr>
                        <td>
                            <div style="width: 280pt">
                                <h2 style="color: slategray">Oil Transaction System</h2>
                                <h3 style="color: lightslategrey">For fast & hassle free Oil Trading</h3>
                                <h3 style="color: lightslategrey"> - Purchase Oil</h3>
                                <h3 style="color: lightslategrey"> - Sell Oil</h3>
                                <h3 style="color: lightslategrey"> - Track your Orders</h3>
                                <h3 style="color: lightslategrey"> - Flexible Payments</h3>
                                <h3 style="color: lightslategrey">Register today to start trading</h3>
                            </div>
                        </td>
                        <td>
                            
                                <!-- -->
                                <form action="/Oil_Transaction_System/login_logout" method="post">
                                <table>
                                    <tr><td>&nbsp;</td></tr>
                               <tr>
                                   <td align="left"><h2 style="color: darkslategray">Register</h2></td>
                                   <td></td>
                               </tr>
                               <tr>
                                   <td><input type="text" name="fname" style=" color: darkgray" value="First name" onfocus="if(this.value==='First name'){this.value=''; this.style.color='black';}"></td>
                                   <td>&nbsp;&nbsp;</td>
                                   <td><input type="text" name="lname" style=" color: darkgray" value="Last name" onfocus="if(this.value==='Last name'){this.value=''; this.style.color='black';}"></td>
                               </tr>
                               <tr><td>&nbsp;</td></tr>
                               <tr>
                                   <td colspan="3"><input type="text" name="street" style=" color: darkgray; width: 100%" value="Street" onfocus="if(this.value==='Street'){this.value=''; this.style.color='black';}"></td>
                               </tr>
                                <tr><td>&nbsp;</td></tr>
                               <tr>
                                   <td><input type="text" name="city" style=" color: darkgray" value="City" onfocus="if(this.value==='City'){this.value=''; this.style.color='black';}"></td>
                                   <td>&nbsp;&nbsp;</td>
                                   <td><input type="text" name="state" style=" color: darkgray" value="State" onfocus="if(this.value==='State'){this.value=''; this.style.color='black';}"></td>
                               </tr>
                                <tr><td>&nbsp;</td></tr>
                               <tr>
                                   <td><input type="text" name="zip" style=" color: darkgray" value="Zip" onfocus="if(this.value==='Zip'){this.value=''; this.style.color='black';}"></td>
                                   <td>&nbsp;&nbsp;</td>
                                   <td><input type="text" name="email" style=" color: darkgray" value="Email-Id" onfocus="if(this.value==='Email-Id'){this.value=''; this.style.color='black';}"></td>
                               </tr>
                                <tr><td>&nbsp;</td></tr>
                               <tr>
                                   <td><input type="text" name="phone" style=" color: darkgray" value="Phone no." onfocus="if(this.value==='Phone no.'){this.value=''; this.style.color='black';}"></td>
                                   <td>&nbsp;&nbsp;</td>
                                   <td><input type="text" name="cellphone" style=" color: darkgray" value="Mobile no." onfocus="if(this.value==='Mobile no.'){this.value=''; this.style.color='black';}"></td>
                               </tr>
                                <tr><td>&nbsp;</td></tr>
                               <tr>
                                   <td><input type="text" name="uname" style=" color: darkgray" value="Enter Username" onfocus="if(this.value==='Enter Username'){this.value=''; this.style.color='black';}"></td>
                                   <td>&nbsp;&nbsp;</td>
                                   <td><input type="text" name="passwd" style=" color: darkgray" value="Enter new password" onfocus="if(this.value==='Enter new password'){this.value='';this.type='password'; this.style.color='black';}"></td>
                                    
                               </tr>
                                <tr><td>&nbsp;</td></tr>
                               <tr>
                                   <td><input type="submit" value="Register"/></td>
                                   <td><input type="hidden" name="opt1" value="register"></td>
                               </tr>
                               </table>
                                </form>
                            <!-- -->
                            
                        </td>
                    </tr>
                </table>
              
            </div>
    </body>
</html>
