<%@ page import="minitienda2.Carrito" %>
<%
    Carrito carrito = (Carrito) session.getAttribute("carrito");
    String action = request.getParameter("action");
    if ("Eliminar Seleccionados".equals(action) && carrito != null) {
        String[] nombres = request.getParameterValues("productoSeleccionado");
        if (nombres != null) {
            for (String nombre : nombres) {
                carrito.getProductos().removeIf(p -> p.getNombre().equals(nombre));
            }
        }
        session.setAttribute("carrito", carrito);
        response.sendRedirect("verCarrito.jsp");
    } else if ("Vaciar Carrito".equals(action)) {
        response.sendRedirect("vaciarCarrito.jsp");
    }
%>
