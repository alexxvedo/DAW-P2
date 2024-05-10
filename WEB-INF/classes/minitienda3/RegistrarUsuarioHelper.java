package minitienda3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrarUsuarioHelper {
    public void ejecutar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String tarjetaTipo = req.getParameter("tipo_tarjeta");
        String tarjetaNumero = req.getParameter("numero_tarjeta");

        Usuario usuario = null;
        try {
            usuario = registrarUsuario(email, password, tarjetaTipo, tarjetaNumero);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error al registrar el usuario");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/registroUsuario.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if (usuario != null) {
            HttpSession session = req.getSession();
            session.setAttribute("usuario", usuario);

            Carrito carrito = (Carrito) session.getAttribute("carrito");
            Pedido pedido = crearPedido(usuario.getId(), carrito.getTotalPrice());
            session.setAttribute("pedido", pedido);
            carrito.vaciarCarrito();

            RequestDispatcher dispatcher = req.getRequestDispatcher("/gracias.jsp");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("error", "Error al registrar el usuario");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/registroUsuario.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private Usuario registrarUsuario(String email, String password, String tarjetaTipo, String tarjetaNumero) throws SQLException {
        Connection con = ConexionBD.getConnection();
        String query = "INSERT INTO usuarios(correo_electronico, contrasena, tipo_tarjeta, numero_tarjeta) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, email);
        pst.setString(2, password);
        pst.setString(3, tarjetaTipo);
        pst.setString(4, tarjetaNumero);
        int result = pst.executeUpdate();

        if (result > 0) {
            pst = con.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setCorreo(email);
                usuario.setContrasena(password);
                usuario.setTarjetaTipo(tarjetaTipo);
                usuario.setTarjetaNumero(tarjetaNumero);
                return usuario;
            }
        }
        return null;
    }

    private Pedido crearPedido(Integer id_usuario, double importe_final) {
        Connection con = null;
        PreparedStatement pst = null;
        Pedido newPedido = null;
        try {
            con = ConexionBD.getConnection();
            String query = "INSERT INTO pedidos(id_usuario, importe_final) VALUES (?, ?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, id_usuario);
            pst.setDouble(2, importe_final);
            int result = pst.executeUpdate();
            if (result > 0) {
                pst = con.prepareStatement("SELECT id, id_usuario, importe_final FROM pedidos WHERE id = LAST_INSERT_ID()");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    newPedido = new Pedido();
                    newPedido.setId(String.valueOf(rs.getInt(1)));
                    newPedido.setId_usuario(String.valueOf(rs.getInt(2)));
                    newPedido.setTotal(rs.getFloat(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) try { pst.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return newPedido;
    }
}