package minitienda3;

public class Pedido {
    String id;
    String idUsuario;
    Float importe;

    public Pedido() {}
    
    public String getId() {
        return id;
    }
    public String getIdUsuario() {
        return idUsuario;
    }

    public Float getImporte() {
        return importe;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public void setImporte(Float importe) {
        this.importe = importe;
    }
}
