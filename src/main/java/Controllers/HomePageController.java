package Controllers;

import DataAccess.ProductDAO;
import Model.Cart;
import Model.Product;
import com.example.magazinonline.HomePage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.scene.control.Label;


public class HomePageController {

    @FXML
    private HBox itemColumns;

    private static final int ROWS_PER_COLUMN = 7;

    private Cart cart;
    private Connection connection;


    public void start(Cart cart) {
        //System.out.println("Home Page Controller started");

        this.cart = cart;
        this.connection = connection;

        /// Liata cu toate produsele
        ProductDAO productDAO = new ProductDAO();
        List<Product> combinedProducts = new ArrayList<>();
        combinedProducts.addAll(productDAO.getNonTaxableProducts());
        combinedProducts.addAll(productDAO.getTaxableProducts());

        /// Sortam alfabetic
        Collections.sort(combinedProducts, Comparator.comparing(Product::getName));

        for(Product product : combinedProducts) {
            System.out.println(product.toString() + " ");
        }

        populateColumns(combinedProducts);
    }

    private void populateColumns(List<Product> products) {

        /// Sterge elementele din coloane
        itemColumns .getChildren().clear();

        VBox currentColumn = new VBox();
        int productCount = 0;

        for(Product product : products) {

            Label productLabel = new Label(product.getName() + "_____" + String.format("%.2f", product.getPrice()) + " lei");
            Button ViewDetails = new Button("View details");
            Label spaceLabel = new Label("\n\n");
            ViewDetails.setOnAction(e -> {
                try {
                    HomePage.showDetailView(product, cart);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            currentColumn.getChildren().add(productLabel);
            currentColumn.getChildren().add(ViewDetails);
            currentColumn.getChildren().add(spaceLabel);
            productCount++;

            if (productCount % ROWS_PER_COLUMN == 0) {
                itemColumns.getChildren().add(currentColumn);

                /// O noua coloana
                currentColumn = new VBox();
            }
        }

        /// Adaugam ultima coloana daca nu este goala sau daca nu a fost deja adaugata
        if (!currentColumn.getChildren().isEmpty()) {
            itemColumns.getChildren().add(currentColumn);
        }
    }

    /// Metoda pentru a naviga la pagina de coș
    @FXML
    private void handleGoToCart() throws IOException {
        System.out.println("Go to cart");
        HomePage.showCartView(cart);
    }

    @FXML
    private void handleGoToReport() throws IOException {
        System.out.println("Go to report");
        HomePage.showReportPage();
    }
}
