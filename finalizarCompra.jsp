<%@ page import="java.sql.*, minitienda2.Carrito, minitienda2.Usuario, minitienda2.Producto, minitienda2.ConexionBD" %>
<%
    Carrito carrito = (Carrito) session.getAttribute("carrito");
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (carrito != null && usuario != null) {
        float total = 0;
        for (Producto producto : carrito.getProductos()) {
            total += producto.getPrecio() * producto.getCantidad();
        }

        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ConexionBD.getConnection();
            String query = "INSERT INTO pedidos(id_usuario, importe_final) VALUES (?, ?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, usuario.getId());
            pst.setFloat(2, total);
            pst.executeUpdate();

            carrito.getProductos().clear();
            session.setAttribute("mensaje", "Gracias por su compra!");
            response.sendRedirect("gracias.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) pst.close();
            if (con != null) con.close();
        }
    } else {
        response.sendRedirect("login.jsp"); // Redireccionar al login si no hay sesión
    }
%>
