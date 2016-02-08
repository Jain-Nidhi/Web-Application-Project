package servlets;


import beans.Client;
import beans.User;
import com.google.gson.Gson;
import dao.daoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class profile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       try{
            HttpSession session = request.getSession(true);
           User cn =new User();
           daoImpl pd= new daoImpl();
           cn.setuid(session.getAttribute("User").toString());
                  
           int cellphone = Integer.parseInt(request.getParameter("cellphone"));
           String fname = request.getParameter("fname");
           String lname = request.getParameter("lname");
           int phone = Integer.parseInt(request.getParameter("phone"));
           
           String city = request.getParameter("city");
           String state = request.getParameter("state");
           String street = request.getParameter("street");
           int zip = Integer.parseInt(request.getParameter("zip"));
           
           pd.UpdateClient(cn.getuid(),fname,lname,phone,cellphone,street,city,zip,state);
           
           response.sendRedirect("design/client_profile.jsp");
           
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
