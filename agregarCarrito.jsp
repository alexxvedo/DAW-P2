<%@ page import="minitienda2.Producto, minitienda2.Carrito" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
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

    Producto producto = new Producto();
    producto.setNombre(nombre);
    producto.setCantidad(cantidad);
    producto.setPrecio(precio);

    Carrito carrito = (Carrito) session.getAttribute("carrito");
    if (carrito == null) {
        carrito = new Carrito();
        session.setAttribute("carrito", carrito);
    }

    boolean found = false;
    for (Producto p : carrito.getProductos()) {
        if (p.getNombre().equals(producto.getNombre())) {
            p.setCantidad(p.getCantidad() + producto.getCantidad());
            found = true;
            break;
        }
    }
    if (!found) {
        carrito.getProductos().add(producto);
    }

    response.sendRedirect("verCarrito.jsp");
%>
