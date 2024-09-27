package Controllers;

import Model.Cart;
import Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class ProductDetailsController {

    @FXML
    private Label productNameLabel;

    @FXML
    private Label productPriceLabel;

    @FXML
    private Label productCategoryLabel;

    @FXML
    private TextField quantityText;

    private Product product;
    private Cart cart;

    public void setProduct(Product product, Cart cart) {
        this.product = product;
        this.cart = cart;

        productNameLabel.setText(product.getName());
        productPriceLabel.setText("Pret: " + String.format("%.2f", product.getPrice()) + " lei");
        productCategoryLabel.setText("Categorie: " + product.getCategory().getName());
    }

    @FXML
    private void handleAddToCart() {
        System.out.println("Added to cart: " + product.getName());

        int quantity = Integer.parseInt(quantityText.getText());

        System.out.println("Quantity: " + quantity);

        cart.addItem(product, quantity);
    }
}