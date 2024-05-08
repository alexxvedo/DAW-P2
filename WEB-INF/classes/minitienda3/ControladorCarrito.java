package minitienda3;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


public class ControladorCarrito extends HttpServlet {


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
        if(action.equals("Agregar")) {
            String nombreMusica = request.getParameter("nombreMusica");
            int cantidad = Integer.parseInt(request.getParameter("cantidadMusica"));

            if (nombreMusica == null || nombreMusica.trim().isEmpty()) {
                response.sendRedirect("index.html");
                return;
            }

            String[] partes = nombreMusica.split("\\|");
            if (partes.length < 4) {
                response.sendRedirect("index.html");
                return;
            }

            String nombre = partes[0].trim();
            String precioStr = partes[3].trim().substring(1);
            float precio = 0;
            try {
                precio = Float.parseFloat(precioStr);
            } catch (NumberFormatException e) {
                response.sendRedirect("index.html");
                return;
            }

            CD producto = new CD();
            producto.setNombre(nombre);
            producto.setCantidad(cantidad);
            producto.setPrecio(precio);

            if (carrito == null) {
                carrito = new Carrito();
                session.setAttribute("carrito", carrito);
            }

            boolean found = false;
            for (CD p : carrito.getItems()) {
                if (p.getNombre().equals(producto.getNombre())) {
                    p.setCantidad(p.getCantidad() + producto.getCantidad());
                    found = true;
                    break;
                }
            }
            if (!found) {
                carrito.getItems().add(producto);
            }

            response.sendRedirect("verCarrito.jsp");
        }
        else if ("Eliminar Seleccionados".equals(action) && carrito != null) {
            String[] nombres = request.getParameterValues("productoSeleccionado");
            if (nombres != null) {
                for (String nombre : nombres) {
                    carrito.getItems().removeIf(p -> p.getNombre().equals(nombre));
                }
            }
            session.setAttribute("carrito", carrito);
            response.sendRedirect("verCarrito.jsp");
        } else if ("Vaciar Carrito".equals(action)) {
            if (carrito != null) {
                carrito.getItems().clear();
            }
            response.sendRedirect("verCarrito.jsp");
        } else if ("Comprar".equals(action)){
            response.sendRedirect("verCaja.jsp");
        }
    }

    public void destroy() 
    {
    }

}