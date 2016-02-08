package servlets;

import beans.User;
import beans.graph;
import dao.daoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class weekgraph extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       try{
           HttpSession session = request.getSession(true);
           User cn =new User();
           daoImpl pd= new daoImpl();
           cn.setuid(session.getAttribute("User").toString());
           cn.setpword(session.getAttribute("Pwd").toString());
           String ch= session.getAttribute("Type").toString();
           cn.settype(ch.charAt(0));
           cn.setuname(session.getAttribute("Uname").toString());
           User us = pd.Login(cn.getuname(), cn.getpword());
                   if (cn.gettype()=='m')
                    {
                        String son="[";
                        List<graph> st=pd.getWeeklyTransaction();
                        for(graph s:st){
                                son+="{\"label\":\""+s.getlabel()+"\",\"value\":\""+s.getval()+"\"},";
                                
                        }
                        son+="]";
                        son=son.replace(",]", "]");
                        out.println(son);
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
