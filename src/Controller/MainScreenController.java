/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class MainScreenController implements Initializable {

    // FXML Created Properties
    @FXML
    private AnchorPane mainScr;
    @FXML
    private Button exitBtn;
    @FXML
    private AnchorPane productsPanel;
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> productsTableProductIDCol;
    @FXML
    private TableColumn<Product, String> productsTableProductNameCol;
    @FXML
    private TableColumn<Product, Integer> productsTableInvLevelCol;
    @FXML
    private TableColumn<Product, Double> productsTablePPUCol;
    @FXML
    private Button productsAddBtn;
    @FXML
    private Button productsModifyBtn;
    @FXML
    private Button productsDeleteBtn;
    @FXML
    private Button productsSearchBtn;
    @FXML
    private TextField productsSearchField;
    @FXML
    private AnchorPane partPanel;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partsTablePartIDCol;
    @FXML
    private TableColumn<Part, String> partsTablePartNameCol;
    @FXML
    private TableColumn<Part, Integer> partsTableInvLevelCol;
    @FXML
    private TableColumn<Part, Double> partsTablePPUCol;
    @FXML
    private Button partsSearchBtn;
    @FXML
    private TextField partsSearchField;
    @FXML
    private Button partsAddBtn;
    @FXML
    private Button partsModifyBtn;
    @FXML
    private Button partsDeleteBtn;

    // Additional properties required for functionality
    private static Part partModify;
    private static int partModifyIndex;
    private static Product productModify;
    private static int productModifyIndex;

    // Getters for additional private properties
    public static int getPartModifyIndex() {
        return partModifyIndex;
    }

    public static int getProductModifyIndex() {
        return productModifyIndex;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fetching and setting Parts Table rows
        partsTablePartIDCol.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        partsTablePartNameCol.setCellValueFactory(cellData -> cellData.getValue().getPartNameProperty());
        partsTableInvLevelCol.setCellValueFactory(cellData -> cellData.getValue().getPartStockLevelProperty().asObject());
        partsTablePPUCol.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());

        // Fetching and setting Products Table rows
        productsTableProductIDCol.setCellValueFactory(cellData -> cellData.getValue().getProductIDProperty().asObject());
        productsTableProductNameCol.setCellValueFactory(cellData -> cellData.getValue().getProductNameProperty());
        productsTableInvLevelCol.setCellValueFactory(cellData -> cellData.getValue().getProductStockLevelProperty().asObject());
        productsTablePPUCol.setCellValueFactory(cellData -> cellData.getValue().getProductPriceProperty().asObject());

        // Initializing Add Part and Delete Part table views
        updatePartsTable();
        updateProductsTable();
        partTableTestDataHelper();
        productTableTestDataHelper();
    }

    // Method to create part table test data (******COMMENT OUT BEFORE FINAL BUILD*******)
    private void partTableTestDataHelper() {
        for (int i = 3000; i < 3006; i++) {
            InHouse inHouseTest = new InHouse();
            inHouseTest.setPartID(i);
            inHouseTest.setPartName("Test" + i);
            inHouseTest.setPartStockLevel(i - 2095);
            inHouseTest.setPartPrice(i - 999);
            Inventory.addPart(inHouseTest);
        }
    }

    // Method to create product table test data (******COMMENT OUT BEFORE FINAL BUILD*******)
    private void productTableTestDataHelper() {
        for (int i = 2006; i > 2000; i--) {
            Product productTest = new Product();
            productTest.setProductID(i);
            productTest.setProductName("Test" + i);
            productTest.setProductStockLevel(i - 2095);
            productTest.setProductPrice(i - 999);
            Inventory.addProduct(productTest);
        }
    }

    /**
     * * Helper methods for updating table views
     *
     * @param event
     */
    private void updatePartsTable() {
        partsTable.setItems(Inventory.getAllParts());
    }

    private void updateProductsTable() {
        productsTable.setItems(Inventory.getAllProducts());
    }

    @FXML
    private void exitBtnHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION: EXIT APPLICATION");
        alert.setHeaderText("Are you sure you would like to exit this application?");
        alert.setContentText("Click OK to exit the application. \n\nClick CANCEL to continue and return to the current screen.");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            System.out.println("User confirmed. Exiting application. END.");
            System.exit(0);
        } else {
            System.out.println("User cancelled alert. Returning to Main Screen.");
            alert.close();
        }
    }
}

@FXML
        private void productsAddBtnHandler(ActionEvent event) {
    }

    @FXML
        private void productsModifyBtnHandler(ActionEvent event) {
    }

    @FXML
        private void productsDeleteBtnHandler(ActionEvent event) {
    }

    @FXML
        private void productsSearchBtnHandler(ActionEvent event) {
    }

    @FXML
        private void partsSearchBtnHandler(ActionEvent event) {
    }

    @FXML
        private void partsAddBtnHandler(ActionEvent event) {
    }

    @FXML
        private void partsModifyBtnHandler(ActionEvent event) {
    }

    @FXML
        private void partsDeleteBtnHandler(ActionEvent event) {
    }




}
