package Controllers;

import Model.Cart;
import Model.ProductInCart;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

import java.sql.*;

public class CheckoutPageController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextArea addressField;

    @FXML
    private Label totalLabel;

    private Cart cart;
    private Connection connection;

    public void initialize(Cart cart, Connection connection) {
        this.cart = cart;
        this.connection = connection;

        totalLabel.setText(String.format("%.2f", cart.getTotal()) + " lei");
    }

    /// Metoda pentru gestionarea trimiterea comenzii
    @FXML
    private void handleSubmitOrder() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            showAlert("Eroare", "Toate campurile trebuie completate!");
            return;
        }

        /// Salvare comanda in baza de date
        try {
            saveOrderToDatabase(name, email, phone, address);
            showAlert("Succes", "Comanda a fost trimisa cu succes!");

            nameField.clear();
            emailField.clear();
            phoneField.clear();
            addressField.clear();
            totalLabel.setText("0 lei");

            cart.clearCart();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Eroare", "A aparut o eroare la salvarea comenzii.");
        }
    }

    /// Metoda pentru salvarea comenzii în baza de date
    private void saveOrderToDatabase(String name, String email, String phone, String address) throws SQLException {
        int orderID;

        String insertOrderQuery = "INSERT INTO orders (customer_name, email, phone, address, total) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setDouble(5, cart.getTotal());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                orderID = rs.getInt(1);
            } else {
                throw new SQLException("Creating order failed");
            }
        }

        String insertOrderItemsQuery = "INSERT INTO order_items (order_id, product_id, quantity, product_type) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderItemsQuery)) {
            for (ProductInCart product : cart.getItems()) {
                preparedStatement.setInt(1, orderID);
                preparedStatement.setInt(2, product.getProduct().getId());
                preparedStatement.setInt(3, product.getQuantity());

                if (product.getProduct().isTaxable()) {
                    preparedStatement.setString(4, "taxable_products");
                } else {
                    preparedStatement.setString(4, "products");
                }

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    /// Daca nu sunt completate campurile
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
