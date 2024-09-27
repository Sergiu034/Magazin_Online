package DataAccess;
import Model.Category;
import Model.Product;
import Model.TaxableProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Connection.DataBase_Connection;
import Model.TaxableProduct;


public class ProductDAO {

    private Connection connection;

    public ProductDAO() {

        /// Conexiune baza de date ///

        try {
            connection = DataBase_Connection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getNonTaxableProducts() {

        /// Metodă pentru a prelua produsele netaxabile ///

        List<Product> products = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.price, c.id AS category_id, c.name AS category_name "
                     + "FROM products p "
                     + "JOIN categories c ON p.category_id = c.id";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");

                Category category = new Category(categoryId, categoryName);
                Product product = new Product(id, name, price, category);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public List<TaxableProduct> getTaxableProducts() {

        /// Metodă pentru a prelua produsele taxabile ///

        List<TaxableProduct> taxableProducts = new ArrayList<>();
        String query = "SELECT tp.id, tp.name, tp.price, tp.tax_rate, c.id AS category_id, c.name AS category_name "
                + "FROM taxable_products tp "
                + "JOIN categories c ON tp.category_id = c.id";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                double taxRate = resultSet.getDouble("tax_rate");
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");

                // Calculăm prețul final cu TVA
                double finalPrice = price * (1 + taxRate);

                // Creăm un obiect Category și Product
                Category category = new Category(categoryId, categoryName);
                TaxableProduct product = new TaxableProduct(id, name, finalPrice, category, taxRate);
                taxableProducts.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taxableProducts;
    }
}
