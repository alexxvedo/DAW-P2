<%@ page import="minitienda2.*" %>
<%
    Carrito carrito = (Carrito) session.getAttribute("carrito");
    if (carrito != null) {
        carrito.getProductos().clear();
    }
    response.sendRedirect("verCarrito.jsp");
%>
