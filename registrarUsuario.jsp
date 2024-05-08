<%@ page import="java.sql.*, minitienda2.Usuario, java.io.PrintWriter, minitienda2.ConexionBD"%>
<%
    String correo = request.getParameter("correo");
    String contrasena = request.getParameter("contrasena");
    String tarjeta_tipo = request.getParameter("tarjeta_tipo");
    String tarjeta_numero = request.getParameter("tarjeta_numero");

    response.setContentType("text/html");
    PrintWriter printWriter = response.getWriter();

    Connection con = null;
    PreparedStatement pst = null;

    try {
        con = ConexionBD.getConnection();

        String query = "INSERT INTO usuarios(correo_electronico, contrasena, tipo_tarjeta, numero_tarjeta) VALUES (?, ?, ?, ?)";
        pst = con.prepareStatement(query);
        pst.setString(1, correo);
        pst.setString(2, contrasena);
        pst.setString(3, tarjeta_tipo);
        pst.setString(4, tarjeta_numero);
        int result = pst.executeUpdate();

        if (result > 0) {
            pst = con.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt(1);
                Usuario newUser = new Usuario();
                newUser.setId(userId);
                newUser.setCorreo(correo);
                newUser.setContrasena(contrasena);
                session.setAttribute("usuario", newUser);
                response.sendRedirect("finalizarCompra.jsp");
            } else {
                printWriter.println("<p>Error al recuperar el ID del usuario.</p>");
            }
        } else {
            printWriter.println("<p>Error al insertar el usuario en la base de datos.</p>");
        }
    } catch (SQLException e) {
        e.printStackTrace(printWriter);
        printWriter.println("<p>Error de SQL: " + e.getMessage() + "</p>");
    } finally {
        if (pst != null) try { pst.close(); } catch (SQLException e) { e.printStackTrace(printWriter); }
        if (con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace(printWriter); }
    }
%>
