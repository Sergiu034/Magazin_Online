package Model;

import javafx.scene.control.Button;

public class ProductInCart {
    private Product product;
    private int quantity;
    private Button deleteButton;

    public ProductInCart(Product product, int quantity, Cart cart) {
        this.product = product;
        this.quantity = quantity;
        this.deleteButton = new Button("Delete");

        /// Daca se apasa pe butonul de stergere
        deleteButton.setOnAction(event -> {
            cart.removeItem(product);
        });
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return Math.round((product.getPrice() * quantity) * 100.0) / 100.0;
    }

    public String getProductName() {
        return product.getName();
    }

    public int getProductId() {
        return product.getId();
    }

    public double getPrice() {
        return Math.round((product.getPrice()) * 100.0) / 100.0;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

