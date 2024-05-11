package minitienda3.helpers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda3.modelos.Carrito;

import java.io.IOException;

public class EliminarItemHelper {
    public void ejecutar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        if (carrito != null) {
            String[] nombres = req.getParameterValues("productoSeleccionado");
            if (nombres != null) {
                for (String nombre : nombres) {
                    carrito.getItems().removeIf(p -> p.getNombre().equals(nombre));
                }
            }
            session.setAttribute("carrito", carrito);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/verCarrito.jsp");
        dispatcher.forward(req, resp);
    }
}