package minitienda3.helpers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda3.CD;
import minitienda3.Carrito;

import java.io.IOException;

public class AgregarCarritoHelper {
    public void ejecutar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        
        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }

        String nombreMusica = req.getParameter("nombreMusica");
        int cantidad = Integer.parseInt(req.getParameter("cantidadMusica"));

         if (nombreMusica == null || nombreMusica.trim().isEmpty() || cantidad <= 0) {
            resp.sendRedirect("index.html");
            return;
        }

        String[] partes = nombreMusica.split("\\|");
        if (partes.length < 4) {
            resp.sendRedirect("index.html");
            return;
        }

        String nombre = partes[0].trim();
        String precioStr = partes[3].trim().substring(1);
        float precio;
        try {
            precio = Float.parseFloat(precioStr);
        } catch (NumberFormatException e) {
            resp.sendRedirect("index.html");
            return;
        }

        CD producto = new CD();
        producto.setNombre(nombre);
        producto.setCantidad(cantidad);
        producto.setPrecio(precio);

        boolean found = false;
        for (CD p : carrito.getItems()) {
            if (p.getNombre().equals(producto.getNombre())) {
                p.setCantidad(p.getCantidad() + cantidad);
                carrito.setTotalPrice(carrito.getTotalPrice() + cantidad * p.getPrecio());
                found = true;
                break;
            }
        }
        if (!found) {
            carrito.getItems().add(producto);
            carrito.setTotalPrice(carrito.getTotalPrice() + producto.getTotal());
        }


        req.setAttribute("carrito", carrito);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/verCarrito.jsp");
        dispatcher.forward(req, resp);
    }
}

