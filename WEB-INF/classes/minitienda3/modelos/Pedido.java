package minitienda3.modelos;

public class Pedido {
    
    String id;
    String id_usuario;
    Float total;

    public Pedido() {}

    public String getId() {
        return id;
    }
    public String getId_usuario() {
        return id_usuario;
    }
    public Float getTotal() {
        return total;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    public void setTotal(Float total) {
        this.total = total;
    }
    
}
