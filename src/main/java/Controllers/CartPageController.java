package Controllers;

import Model.Cart;
import Model.Product;
import Model.ProductInCart;

import Connection.DataBase_Connection;

import com.example.magazinonline.HomePage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CartPageController {

    @FXML
    private TableView<ProductInCart> cartTable;

    @FXML
    private TableColumn<ProductInCart, Integer> columnIndex;

    @FXML
    private TableColumn<ProductInCart, String> columnName;

    @FXML
    private TableColumn<ProductInCart, Integer> columnQuantity;

    @FXML
    private TableColumn<ProductInCart, Double> columnPrice;

    @FXML
    private TableColumn<ProductInCart, Double> columnTotal;

    @FXML
    private TableColumn<ProductInCart, Button> columnDelete;

    private Cart cart;
    private Connection connection;

    public void start(Cart cart) {
        this.cart = cart;

        try {
            connection = DataBase_Connection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /// Initializare coloane
        columnIndex.setCellValueFactory(new PropertyValueFactory<>("productId"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        columnDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        /// Populam tabel
        populateTable();
    }

    private void populateTable() {
        /// Convertim in Observable List
        ObservableList<ProductInCart> cartItemsObservable = FXCollections.observableArrayList(cart.getItems());

        cartTable.setItems(cartItemsObservable);
    }


    ///Metoda pentru a naviga la pagina de checkout
    @FXML
    private void handleCheckout() throws SQLException, IOException {
        System.out.println("Checkout");
        HomePage.showCheckoutView(cart, connection);
    }

}
