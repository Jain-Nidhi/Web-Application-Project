package servlets;


import beans.Payment;
import beans.Transaction;
import dao.daoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class payment_oil extends HttpServlet {
/*
Connection conn=null;
ConnectionPool c=null;
*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       HttpSession session = request.getSession(true);
        daoImpl pd = new daoImpl();
        try {
                String i="";
                try{
                 if(request.getParameter("opt").equals("Pay"))
                    {
                        i="Pay";
                    }
                }catch(Exception e){
                    
                }
                try{
                 if(request.getParameter("opt1").equals("Pay"))
                    {
                        i="Pay";
                    }
                }catch(Exception e){
                    
                }
                try{
                 if(request.getParameter("opt").equals("Cancel"))
                    {
                        i="optCancel";
                    }
                }catch(Exception e){
                    
                }
                
                try{
                 if(request.getParameter("opt1").equals("Cancel"))
                    {
                        i="opt1Cancel";
                    }
                }catch(Exception e){
                    
                }
                
                try{
                 if(request.getParameter("opt2").equals("Clear"))
                    {
                        i="opt2Clear";
                    }
                }catch(Exception e){
                    
                }
                try{
                 if(request.getParameter("opt3").equals("Clear"))
                    {
                        i="opt3Clear";
                    }
                }catch(Exception e){
                    
                }
                try{
                 if(request.getParameter("opt4").equals("Clear"))
                    {
                        i="opt4Clear";
                    }
                }catch(Exception e){
                    
                }
                try{
                 if(request.getParameter("opt5").equals("Cancel"))
                    {
                        i="opt5Cancel";
                    }
                }catch(Exception e){
                    
                }
                try{
                 if(request.getParameter("opt5").equals("Clear"))
                    {
                        i="opt5Clear";
                    }
                }catch(Exception e){
                    
                }
            try{
                 if(request.getParameter("opt6").equals("Clear"))
                    {
                        i="opt6Clear";
                    }
                }catch(Exception e){
                    
                }
            
                if(i.equals("Pay"))
                {
                    String tid = request.getParameter("tid");
                    session.setAttribute("Tid", tid);
                    response.sendRedirect("design/client_payment.jsp");
                }
                else if(i.equals("optCancel"))
                {
                    String tid = request.getParameter("tid");
                    session.removeAttribute("Tid");
                    Transaction tn = new Transaction();
                    tn = pd.GetTransactionForId(tid);
                    List<Payment> lp = pd.getTransactionPayments(tid);
                    for (Payment Paym : lp) {
                      pd.CancelPayment(Paym.getpaymentid(),tn.gettraderid());
                     }
                    pd.CancelTransaction(tid, tn.gettraderid());
                    
                    response.sendRedirect("design/client_oil.jsp");
                }
                else if(i.equals("opt1Cancel"))
                {
                    String tid = request.getParameter("tid");
                    session.removeAttribute("Tid");
                    Transaction tn = new Transaction();
                    tn=pd.GetTransactionForId(tid);
                    List<Payment> lp = pd.getTransactionPayments(tid);
                    for (Payment Paym : lp) {
                      pd.CancelPayment(Paym.getpaymentid(),tn.gettraderid());
                     }
                    pd.CancelTransaction(tid, tn.gettraderid());
                    response.sendRedirect("design/selloil.jsp");
                }
                 else if(i.equals("opt3Clear"))
                {
                    String tid = request.getParameter("tid");
                    session.removeAttribute("Tid");
                    Transaction tn = new Transaction();
                    tn=pd.GetTransactionForId(tid);
                    List<Payment> lp = pd.getTransactionPayments(tid);
                    for (Payment Paym : lp) {
                      pd.DeletePayment(Paym.getpaymentid());
                     }
                    pd.DeleteTransaction(tid);
                    response.sendRedirect("design/client_oil.jsp");
                }
                  else if(i.equals("opt4Clear"))
                {
                    String tid = request.getParameter("tid");
                    session.removeAttribute("Tid");
                    Transaction tn = new Transaction();
                    tn=pd.GetTransactionForId(tid);
                    List<Payment> lp = pd.getTransactionPayments(tid);
                    for (Payment Paym : lp) {
                      pd.DeletePayment(Paym.getpaymentid());
                     }
                    pd.DeleteTransaction(tid);
                    response.sendRedirect("design/selloil.jsp");
                }
                else if(i.equals("opt2Clear"))
                {
                    String payid = request.getParameter("payid");
                    pd.DeletePayment(payid);
                    response.sendRedirect("design/paymenthistory.jsp");
                }
                else if(i.equals("opt5Cancel"))
                {
                    String tid = request.getParameter("tid");
                    session.removeAttribute("Tid");
                    Transaction tn = new Transaction();
                    tn=pd.GetTransactionForId(tid);
                    List<Payment> lp = pd.getTransactionPayments(tid);
                    for (Payment Paym : lp) {
                      pd.CancelPayment(Paym.getpaymentid(),tn.gettraderid());
                     }
                    pd.CancelTransaction(tid, tn.gettraderid());
                    response.sendRedirect("design/trader_dashboard.jsp");
                }
                else if(i.equals("opt5Clear"))
                {
                    String tid = request.getParameter("tid");
                    session.removeAttribute("Tid");
                    Transaction tn = new Transaction();
                    tn=pd.GetTransactionForId(tid);
                    List<Payment> lp = pd.getTransactionPayments(tid);
                    for (Payment Paym : lp) {
                      pd.DeletePayment(Paym.getpaymentid());
                     }
                    pd.DeleteTransaction(tid);
                    response.sendRedirect("design/trader_dashboard.jsp");
                }
                else if(i.equals("opt6Clear"))
                {
                    String payid = request.getParameter("payid");
                    pd.DeletePayment(payid);
                    response.sendRedirect("design/trader_payment.jsp");
                }
               
            }
            catch (Exception e) {
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
