package Model;

import java.util.Date;

public class OrderReport {
    private int orderId;
    private Date orderDate;
    private String customerName;
    private String email;
    private int totalProductsInOrder;

    public OrderReport(int orderId, Date orderDate, String customerName, String email, int totalProductsInOrder) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.email = email;
        this.totalProductsInOrder = totalProductsInOrder;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    public int getTotalProductsInOrder() {
        return totalProductsInOrder;
    }

    public void setTotalProductsInOrder(int totalProductsInOrder) {
        this.totalProductsInOrder = totalProductsInOrder;
    }
}