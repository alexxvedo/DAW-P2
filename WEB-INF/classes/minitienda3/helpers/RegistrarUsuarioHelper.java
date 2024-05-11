package minitienda3.helpers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda3.modelos.Carrito;
import minitienda3.modelos.ModeloBD;
import minitienda3.modelos.Pedido;
import minitienda3.modelos.Usuario;

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
            usuario = ModeloBD.registrarUsuario(email, password, tarjetaTipo, tarjetaNumero);
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
            Pedido pedido = ModeloBD.crearPedido(usuario.getId(), carrito.getTotalPrice());

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

}