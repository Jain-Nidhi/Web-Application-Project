/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.User;
import dao.daoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nidhi
 */
public class searchclients extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
       
        User cn =new User();
        daoImpl pd= new daoImpl();
        
        try {
            
            HttpSession session = request.getSession(true);
            try{
                cn.setuid(session.getAttribute("User").toString());
                }catch (Exception e) {
                    response.sendRedirect("design/main.jsp");
                    }
            
            if(!cn.getuid().equals(""))
            {
                String opt = request.getParameter("mode");
                String value = request.getParameter("searchvalue");
                List<String> cli = new ArrayList<>();
                
                if(opt.equals("name"))
                {
                    cli = pd.GetAllClients_ByName(value);
                }
                else if(opt.equals("email"))
                {
                    cli = pd.GetAllClients_ByEmail(value);
                }
                else if(opt.equals("address"))
                {
                   cli = pd.GetAllClients_ByAddress(value);
                }
                
                String str="";
                for(String cl:cli){
                    str+=cl+" ";
                }
                session.setAttribute("searchclient", str);
                
                response.sendRedirect("design/trader_client.jsp");
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
