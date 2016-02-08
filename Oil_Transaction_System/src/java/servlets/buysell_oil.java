package servlets;


import beans.Client;
import beans.Transaction;
import beans.User;
import dao.daoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class buysell_oil extends HttpServlet {
/*
Connection conn=null;
ConnectionPool c=null;
*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
       
        User cn =new User();
        Client cli = new Client();
        Transaction tn = new Transaction();
        daoImpl pd= new daoImpl();
        
        try {
            
            HttpSession session = request.getSession(true);
            try{
                cn.setuid(session.getAttribute("User").toString());
                cn.setpword(session.getAttribute("Pwd").toString());
                String ch= session.getAttribute("Type").toString();
                cn.settype(ch.charAt(0));
                cn.setuname(session.getAttribute("Uname").toString());
                }catch (Exception e) {
                    response.sendRedirect("design/main.jsp");
                    }
            
            if(!cn.getuid().equals(""))
            {
                Client client1 = pd.GetClientForCredentials(cn.getuname(),cn.getpword());
                if(request.getParameter("opt").equals("Purchase") || request.getParameter("opt").equals("Sell"))
                {
                    tn.setquantity(Integer.parseInt(request.getParameter("oilamount")));

                    if(request.getParameter("opt").equals("Purchase"))
                    {
                        
                        float purchase;
                         try{
                            purchase = Float.parseFloat(session.getAttribute("currpur").toString());
                         }catch (Exception e) {
                                 purchase = 0.0f;
                             }
                        
                        tn.setrate(purchase);
                        tn.setbuyorsell(true);
                    }
                    else
                    {
                        float sale;
                        try{
                        sale = Float.parseFloat(session.getAttribute("currsale").toString());
                         }catch (Exception e) {
                                 sale = 0.0f;
                             }
                        tn.setrate(sale);
                        tn.setbuyorsell(false);
                    }

                    if(request.getParameter("mode").equals("oil"))
                    tn.setcmode(true);
                    else
                    tn.setcmode(false);
                    tn.settdate(new Date());
                  //  String clientid, double quantity, Date tradeDate, double rate, CommissionMode cmode, double CommissionRate) {
                    Transaction newt;
                   if(request.getParameter("opt").equals("Purchase"))
                   {
                    newt = pd.Buy(client1.getclientid(),tn.getquantity(),tn.gettdate(),tn.getrate(),tn.iscmode());
                    response.sendRedirect("design/client_oil.jsp");
                   }
                   else
                   {
                    newt = pd.Sell(client1.getclientid(),tn.getquantity(),tn.gettdate(),tn.getrate(),tn.iscmode());
                    response.sendRedirect("design/selloil.jsp");
                   }

                }
        
              //  ArrayList<Transaction> arrtn = pd.(cli.getclientid());
              //  request.setAttribute("TransactionList",arrtn);
                response.sendRedirect("design/client_oil.jsp");
            }
            else
            {
                  response.sendRedirect("design/main.jsp");
            }
       

            } catch (Exception e) {
                    response.sendRedirect("design/main.jsp");
                }finally {
                    out.close();
                }

}


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
