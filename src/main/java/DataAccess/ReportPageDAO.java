package DataAccess;

import java.sql.Connection;
import Model.OrderReport;
import Connection.DataBase_Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ReportPageDAO {
    private Connection connection;

    public ReportPageDAO() {
        try {
            connection = DataBase_Connection.getConnection();
            if (connection != null) {
                System.out.println("Database connection established.");
            } else {
                System.out.println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /// Interogare
    public ObservableList<OrderReport> getOrderReport() {
        String query = "SELECT " +
                "orders.id AS order_id, " +
                "orders.created_at AS order_date, " +
                "orders.customer_name, " +
                "orders.email, " +
                "COUNT(order_items.product_id) AS total_products_in_order " +
                "FROM orders " +
                "JOIN order_items ON orders.id = order_items.order_id " +
                "JOIN products ON order_items.product_id = products.id " +
                "JOIN categories ON products.category_id = categories.id " +
                "WHERE products.price < 100 " +
                "GROUP BY orders.id, categories.id " +
                "HAVING COUNT(order_items.product_id) >= 3 " +
                "ORDER BY total_products_in_order DESC;";

        ObservableList<OrderReport> reportData = FXCollections.observableArrayList();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {

            System.out.println("Query executed successfully");

            while (rs.next()) {
                System.out.println("Found order: " + rs.getInt("order_id"));
                OrderReport report = new OrderReport(
                        rs.getInt("order_id"),
                        rs.getDate("order_date"),
                        rs.getString("customer_name"),
                        rs.getString("email"),
                        rs.getInt("total_products_in_order")
                );
                reportData.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportData;
    }
}
