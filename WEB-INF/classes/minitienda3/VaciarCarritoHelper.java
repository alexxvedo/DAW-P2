package minitienda3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VaciarCarritoHelper {
    public void ejecutar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        if (carrito != null) {
            carrito.getItems().clear();
            carrito.setTotalPrice(0);
            session.setAttribute("carrito", carrito);
        }

        req.setAttribute("carrito", carrito);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/verCarrito.jsp");
        dispatcher.forward(req, resp);
    }
}