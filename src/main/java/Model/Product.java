package Model;

public class Product {

    private int id;
    private String name;
    private double price;
    private Category category;

    public Product(int id, String name, double price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " - " + price + " lei (Categoria: " + category.getName() + ")";
    }

    public Integer getId() {
        return id;
    }

    public boolean isTaxable() {
        return this instanceof TaxableProduct;
    }
}
