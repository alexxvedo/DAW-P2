package minitienda3;

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

    public void setItems(List<CD> items) {
        this.items = items;
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