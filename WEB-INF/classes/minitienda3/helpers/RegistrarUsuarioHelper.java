package minitienda3.helpers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda3.Carrito;
import minitienda3.Pedido;
import minitienda3.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class RegistrarUsuarioHelper {
    public void ejecutar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String tarjetaTipo = req.getParameter("tipo_tarjeta");
        String tarjetaNumero = req.getParameter("numero_tarjeta");

        Usuario usuario = null;
        try {
            usuario = registrarUsuario(email, password, tarjetaTipo, tarjetaNumero);
        }catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            req.setAttribute("error", "Ya existe un usuario con ese correo electrónico");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/registroUsuario.jsp");
            dispatcher.forward(req, resp);
            return;
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
            Pedido pedido = new CrearPedidoHelper().crearPedido(usuario.getId(), carrito.getTotalPrice());

            session.setAttribute("pedido", pedido);
            carrito.vaciarCarrito();

            RequestDispatcher dispatcher = req.getRequestDispatcher("/gracias.jsp");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("error", "Error al crear el pedido, vuelve a intentarlo.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private Usuario registrarUsuario(String email, String password, String tarjetaTipo, String tarjetaNumero) throws SQLException {
        Connection con = ConexionBDHelper.getConnection();
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

}