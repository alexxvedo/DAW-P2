<%@ page import="java.sql.*, minitienda2.ConexionBD" %>
<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    Connection con = ConexionBD.getConnection();

    PreparedStatement pst = con.prepareStatement("SELECT * FROM Usuarios WHERE correo = ? AND contrasena = ?");
    pst.setString(1, email);
    pst.setString(2, password);
    ResultSet rs = pst.executeQuery();

    if (rs.next()) {
        Usuario newUser = new Usuario();
        newUser.setId(rs.getInt("id"));
        newUser.setCorreo(email);
        newUser.setContrasena(password);
        session.setAttribute("usuario", newUser);
        response.sendRedirect("finalizarCompra.jsp");
    } else {
        out.println("<p>Credenciales incorrectas. Inténtalo de nuevo.</p>");
        out.println("<a href='login.jsp'>Volver al inicio de sesión</a>");
    }

    rs.close();
    pst.close();
    con.close();
%>
