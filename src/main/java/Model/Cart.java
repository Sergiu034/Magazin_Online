package Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<ProductInCart> items;

    public Cart() {
        items = new ArrayList<>();
    }

    /// Adauga un produs in cos
    public void addItem(Product product, int quantity) {
        for (ProductInCart item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new ProductInCart(product, quantity, this));
    }

    /// Sterge produs din cos
    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().getId() == product.getId());
    }

    public void clearCart(){
        items.clear();
    }

    public List<ProductInCart> getItems() {
        return items;
    }


    /// Calculeaza totalul general pentru toate produsele din cos
    public double getTotal() {
        double total = 0;
        for (ProductInCart item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}
