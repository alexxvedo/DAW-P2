package minitienda3.modelos;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<CD> items;
    private double totalPrice;

    public Carrito() {
        this.items = new ArrayList<>();
        this.totalPrice = 0;
    }

    public List<CD> getItems() {
        return items;
    }

    public void setItems(CD item) {
        boolean found = false;
        for (CD p : this.items) {
            if (p.getNombre().equals(item.getNombre())) {
                p.setCantidad(p.getCantidad() + item.getCantidad());
                this.totalPrice = this.totalPrice + item.getCantidad() * p.getPrecio();
                found = true;
                break;
            }
        }
        if (!found) {
            this.items.add(item);
            this.totalPrice = this.totalPrice + item.getTotal();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void vaciarCarrito() {
        this.items.clear();
        this.totalPrice = 0;
    }
}