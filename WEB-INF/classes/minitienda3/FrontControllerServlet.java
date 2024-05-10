package minitienda3;

import javax.servlet.*;
import javax.servlet.http.*;

import minitienda3.helpers.AgregarCarritoHelper;
import minitienda3.helpers.EliminarItemHelper;
import minitienda3.helpers.LoginHelper;
import minitienda3.helpers.RegistrarUsuarioHelper;
import minitienda3.helpers.VaciarCarritoHelper;

import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "Agregar":
                new AgregarCarritoHelper().ejecutar(req, resp);
                break;
            
            case "eliminar":
                new EliminarItemHelper().ejecutar(req, resp);
                break;

            case "vaciar":
                new VaciarCarritoHelper().ejecutar(req, resp);
                break;

            case "comprar":
                Carrito carrito = (Carrito) req.getSession().getAttribute("carrito");
                if(carrito == null || carrito.getItems().isEmpty())
                    resp.sendRedirect("verCarrito.jsp");
                else
                    resp.sendRedirect("verCaja.jsp");
                
                break;

            case "login":
                new LoginHelper().ejecutar(req, resp);
                break;

            case "registrar":
                new RegistrarUsuarioHelper().ejecutar(req, resp);
                break;

            default:
                resp.sendRedirect("error.jsp"); //TODO: implementar error.jsp
                break;
        }
    }
}
