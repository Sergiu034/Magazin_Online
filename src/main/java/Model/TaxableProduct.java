package Model;

public class TaxableProduct extends Product {
    private double taxRate;

    public TaxableProduct(int id, String name, double price, Category category, double taxRate) {
        super(id, name, price, category);
        this.taxRate = taxRate;
    }

    @Override
    public double getPrice() {
        return super.getPrice() * (1 + taxRate);
    }

    @Override
    public String toString() {
        return super.toString() + " (TVA inclus)";
    }
}
