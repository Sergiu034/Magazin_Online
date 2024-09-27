package Controllers;

import DataAccess.ReportPageDAO;
import Model.OrderReport;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReportPageController {

    @FXML
    private TableView<OrderReport> reportTableView;

    @FXML
    private TableColumn<OrderReport, Integer> orderIdColumn;

    @FXML
    private TableColumn<OrderReport, String> orderDateColumn;

    @FXML
    private TableColumn<OrderReport, String> customerNameColumn;

    @FXML
    private TableColumn<OrderReport, String> emailColumn;

    @FXML
    private TableColumn<OrderReport, Integer> totalProductsColumn;

    private ReportPageDAO reportService;

    public void initialize() {

        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        totalProductsColumn.setCellValueFactory(new PropertyValueFactory<>("totalProductsInOrder"));
    }

    public void setReportService(ReportPageDAO reportService) {
        this.reportService = reportService;
        loadReportData();
    }

    public void loadReportData() {
        if (reportService != null) {
            ObservableList<OrderReport> reportData = reportService.getOrderReport();
            reportTableView.setItems(reportData);
        } else {
            System.out.println("reportService is null, cannot fetch report.");
        }
    }
}