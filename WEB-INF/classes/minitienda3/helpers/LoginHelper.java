package minitienda3.helpers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minitienda3.modelos.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginHelper {
    public void ejecutar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Usuario usuario = null;
        try {
            usuario = ModeloBD.getUsuarioByEmailAndPassword(email, password);
            if(usuario == null) {
                req.setAttribute("error", "Usuario o contraseña incorrectos");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
                dispatcher.forward(req, resp);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error al iniciar sesión");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if (usuario != null) {
            HttpSession session = req.getSession();
            session.setAttribute("usuario", usuario);

            Carrito carrito = (Carrito) session.getAttribute("carrito");
            Pedido pedido = ModeloBD.crearPedido(usuario.getId(), carrito.getTotalPrice());
            System.out.println("Pedido creado: " + pedido.getTotal());
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