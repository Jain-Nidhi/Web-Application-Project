package servlets;


import dao.daoImpl;
import beans.Client;
import beans.User;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login_logout extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
            response.setContentType( "application/json" );
            response.setCharacterEncoding( "UTF-8" );
            PrintWriter out = response.getWriter();
        
        User cn = new User();
        daoImpl pd= new daoImpl();
        
        
        try {
            
            HttpSession session = request.getSession(true);

            if(request.getParameter("opt1").equals("logout"))
            {
                session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect("design/main.jsp");
            }
        else if(request.getParameter("opt1").equals("login"))
            {
                String uname="",pwd="";
               try{
                uname = request.getParameter("uname");
                pwd = request.getParameter("pwd");
                cn=pd.Login(uname,pwd);
               }catch(Exception e){
                   response.sendRedirect("design/main.jsp");
               }
               
                 
                 if(!cn.getuid().equals(""))
                 {
                     
                    Random r = new Random();
                    float currpur = r.nextInt(80 - 65) + 65;
                    float currsale = r.nextInt(80 - 65) + 65;

                    session.setAttribute("currpur", currpur);
                    session.setAttribute("currsale", currsale);
                    session.setAttribute("User", cn.getuid());
                    session.setAttribute("Pwd", cn.getpword());
                    session.setAttribute("Type", cn.gettype());
                    session.setAttribute("Uname", cn.getuname());
                    if (cn.gettype()=='c')
                    {
                            response.sendRedirect("design/client_dashboard.jsp");
                    }
                    else if (cn.gettype()=='t')
                    {
                        session.setAttribute("clientid", "null");
                        session.setAttribute("searchclient", "null");
                        response.sendRedirect("design/trader_dashboard.jsp");
                    }
                    else if (cn.gettype()=='m')
                       {
                           response.sendRedirect("design/manager_dashboard.jsp");
                    }

                }
                  else
                  response.sendRedirect("design/main.jsp");
            }
           else if(request.getParameter("opt1").equals("register"))
            {
                
                    Client cli = new Client();
                    User cn1=new User();
                    cn1.setuname(request.getParameter("uname"));
                    cn1.setfname(request.getParameter("fname")); 
                    cn1.setlname(request.getParameter("lname"));
                    cn1.setphone(Long.parseLong(request.getParameter("phone")));
                    cn1.setcellphone(Long.parseLong(request.getParameter("cellphone")));
                    cn1.setpword(request.getParameter("passwd"));
                    cn1.setemail(request.getParameter("email")); 
                    
                    cli.setlevel("Silver");
                    cli.setdue(0);
                    cli.setstreet(request.getParameter("street"));
                    cli.setcity(request.getParameter("city")); 
                    cli.setstate(request.getParameter("state"));
                    cli.setzipcode(Integer.parseInt(request.getParameter("zip")));
                    
                    
                    Client client1 = new Client();
                    client1 = pd.RegisterClient(cn1.getuname(),cn1.getpword(),cn1.getfname(),cn1.getlname(),cn1.getphone(),cn1.getcellphone(),cn1.getemail(),cli.getlevel(),cli.getdue(),cli.getstreet(),cli.getcity(),cli.getzipcode(),cli.getstate(),cli.getstock());
                    
                    session.setAttribute("User", client1.getclientid());
                    session.setAttribute("Pwd", client1.getpword());
                    session.setAttribute("Type", client1.gettype());
                    session.setAttribute("Uname", client1.getuname());
                    /*
                    FileWriter jsonFileWriter = new FileWriter("C:\\Users\\Nidhi\\Documents\\NetBeansProjects\\Oil_Transaction_System\\web\\design\\newjson.json");
                    jsonFileWriter.write(son);
                    jsonFileWriter.flush();
                    jsonFileWriter.close();
*/
                response.sendRedirect("design/client_dashboard.jsp");
                //    RequestDispatcher rd = request.getRequestDispatcher("client_dash");
                 //   rd.forward(request,response);
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
