package Model;

import java.sql.Timestamp;

public class Order {

    private int id;
    private String customerName;
    private String email;
    private String phone;
    private String address;
    private double total;
    private Timestamp createdAt;

    public Order(String customerName, String email, String phone, String address, double total) {
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.total = total;
    }

    public Order(int id, String customerName, String email, String phone, String address, double total, Timestamp createdAt) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.total = total;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", total=" + total +
                ", createdAt=" + createdAt +
                '}';
    }
}
