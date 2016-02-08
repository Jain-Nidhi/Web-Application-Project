package servlets;

import beans.Transaction;
import beans.User;
import com.google.gson.Gson;
import dao.daoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class comm_info extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       try{
           HttpSession session = request.getSession(true);
           User cn =new User();
           daoImpl pd= new daoImpl();
           cn.setuid(session.getAttribute("User").toString());
           float currpur,currsale;
           try{
           currpur = Float.parseFloat(session.getAttribute("currpur").toString());
            }catch (Exception e) {
                    currpur = 0.0f;
                }
           try{
           currsale = Float.parseFloat(session.getAttribute("currsale").toString());
            }catch (Exception e) {
                    currsale = 0.0f;
                }
           
           
           out.println("{\"currpur\":\""+currpur+"\",\"currsale\":\""+currsale+"\"}");
                   
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
