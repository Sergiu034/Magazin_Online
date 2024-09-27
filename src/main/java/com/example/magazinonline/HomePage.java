package com.example.magazinonline;

import Controllers.*;
import DataAccess.ReportPageDAO;
import Model.Cart;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class HomePage extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("homePageView.fxml"));
        VBox root = fxmlLoader.load();

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Home Page");

        stage.setScene(scene);
        stage.show();

        HomePageController controller = fxmlLoader.getController();
        controller.start(new Cart());
    }

    public static void showCartView(Cart cart) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("cartPage.fxml"));
        VBox root = fxmlLoader.load();

        Stage cartStage = new Stage();
        cartStage.setTitle("Cos");

        Scene cartScene = new Scene(root, 600, 600);
        cartStage.setScene(cartScene);
        cartStage.show();

        CartPageController controller = fxmlLoader.getController();
        controller.start(cart);
    }

    public static void showCheckoutView(Cart cart, Connection connection) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("checkoutPage.fxml"));
        VBox root = fxmlLoader.load();

        Stage checkoutStage = new Stage();
        checkoutStage.setTitle("Checkout");

        Scene checkoutScene = new Scene(root, 600, 600);
        checkoutStage.setScene(checkoutScene);
        checkoutStage.show();

        CheckoutPageController controller = fxmlLoader.getController();
        controller.initialize(cart, connection);
    }

    public static void showDetailView(Product product, Cart cart) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("productDetailsView.fxml"));
        VBox root = fxmlLoader.load();

        Stage detailStage = new Stage();
        detailStage.setTitle("Details");

        Scene detailScene = new Scene(root, 300, 270);
        detailStage.setScene(detailScene);
        detailStage.show();

        ProductDetailsController controller = fxmlLoader.getController();
        controller.setProduct(product, cart);
    }

    public static void showReportPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("reportPage.fxml"));
        VBox root = fxmlLoader.load();

        Stage raportStage = new Stage();
        raportStage.setTitle("Report");

        Scene raportScene = new Scene(root, 600, 600);
        raportStage.setScene(raportScene);
        raportStage.show();

        ReportPageController controller = fxmlLoader.getController();
        controller.setReportService(new ReportPageDAO());
    }

    public static void main(String[] args) {
        launch();
    }
}
