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

        String action = request.getParameter("action");
        if(action.equals("login")){
            String usuario = request.getParameter("email");
            String contrasena = request.getParameter("password");
            Usuario newUser = UsuarioBD.login(usuario, contrasena);
            if(newUser != null){
                Pedido newPedido = PedidoBD.crearPedido(newUser.getId(), carrito.getTotalPrice());
                if(newPedido != null){
                    session.setAttribute("pedido", newPedido);
                    carrito.vaciarCarrito();
                    response.sendRedirect("gracias.jsp");
                }
            }    
        } 
        
        else if(action.equals("registrar")){
            String usuario = request.getParameter("email");
            String contrasena = request.getParameter("password");
            String tipo_tarjeta = request.getParameter("tipo_tarjeta");
            String numero_tarjeta = request.getParameter("numero_tarjeta");
            Usuario newUser = UsuarioBD.registrar(usuario, contrasena, tipo_tarjeta, numero_tarjeta);

            if(newUser != null){
                Pedido newPedido = PedidoBD.crearPedido(newUser.getId(), carrito.getTotalPrice());
                if(newPedido != null){
                    session.setAttribute("pedido", newPedido);
                    carrito.vaciarCarrito();
                    response.sendRedirect("gracias.jsp");
                }
            }    
        }
         
    }

    public void destroy() 
    {
    }

}