package minitienda3.helpers;

import java.sql.*;

import minitienda3.Pedido;

public class CrearPedidoHelper {
    public Pedido crearPedido(Integer id_usuario, double importe_final) {
        Connection con = null;
        PreparedStatement pst = null;
        Pedido newPedido = null;
        try {
            con = ConexionBDHelper.getConnection();
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
