package minitienda3;

public class CD {

    private String nombre;
    private Integer cantidad;
    private Float precio;
    private Float total;

    public CD() {}

    public int getCantidad() {
        return cantidad;
    }
    public String getNombre() {
        return nombre;
    }
    public Float getPrecio() {
        return precio;
    }
    public Float getTotal() {
        return total;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        if(precio != null){
            this.total = cantidad * precio;
        }
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPrecio(Float precio) {
        this.precio = precio;
        if(cantidad != null){
            this.total = cantidad * precio;
        }
    }
    
}
