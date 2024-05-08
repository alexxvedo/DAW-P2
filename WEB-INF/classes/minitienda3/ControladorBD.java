package minitienda3;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


public class ControladorBD extends HttpServlet {


    public void init(ServletConfig config)throws ServletException {
	    super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// If it is a get request forward to doPost()
	    doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

        Carrito carrito = (Carrito) session.getAttribute("carrito");

        String action = request.getParameter("login");
        if(action.equals("login")){
            String usuario = request.getParameter("email");
            String contrasena = request.getParameter("password");
            if(UsuarioBD.login(usuario, contrasena)){
                response.sendRedirect("gracias.jsp");
            }    
        }
    }

    public void destroy() 
    {
    }

}