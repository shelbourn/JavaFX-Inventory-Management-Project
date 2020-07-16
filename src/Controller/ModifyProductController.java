/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.MainScreenController.getProductModifyIndex;
import Model.InHouse;
import Model.Inventory;
import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class ModifyProductController implements Initializable {

    // FXML Created Properties
    @FXML
    private AnchorPane modifyProdScr;
    @FXML
    private AnchorPane modifyProdScrPane;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField productIDField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField maxLevelField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField inventoryLevelField;
    @FXML
    private TextField minLevelField;
    @FXML
    private TableView<Part> addTable;
    @FXML
    private TableColumn<Part, Integer> addTablePartIDCol;
    @FXML
    private TableColumn<Part, String> addTablePartNameCol;
    @FXML
    private TableColumn<Part, Integer> addTableInvLevelCol;
    @FXML
    private TableColumn<Part, Double> addTablePPUCol;
    @FXML
    private Button addBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Part> deleteTable;
    @FXML
    private TableColumn<Part, Integer> deleteTablePartIDCol;
    @FXML
    private TableColumn<Part, String> deleteTablePartNameCol;
    @FXML
    private TableColumn<Part, Integer> deleteTableInvLevelCol;
    @FXML
    private TableColumn<Part, Double> deleteTablePPUCol;
    @FXML
    private Button deleteBtn;

    // Additional properties required for functionality
    private int productID;
    private int productModifyIndex = getProductModifyIndex();
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private String productFieldException = new String();
    private String productValueException = new String();
    private String productDataTypeException = new String();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Fetching and populating data for product to modify
        Product productToModify = Inventory.getAllProducts().get(productModifyIndex);
        productID = Inventory.getAllProducts().get(productModifyIndex).getProductID();
        productIDField.setText("Auto-Generated:   " + productID);
        productNameField.setText(productToModify.getProductName());
        inventoryLevelField.setText(Integer.toString(productToModify.getProductStockLevel()));
        priceField.setText(Double.toString(productToModify.getProductPrice()));
        maxLevelField.setText(Integer.toString(productToModify.getProductMaxStockLevel()));
        minLevelField.setText(Integer.toString(productToModify.getProductMinStockLevel()));

        // Fetching and populating product's associated parts
        associatedParts = productToModify.getAssociatedParts();

        // Fetching and setting Add Table rows
        addTablePartIDCol.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        addTablePartNameCol.setCellValueFactory(cellData -> cellData.getValue().getPartNameProperty());
        addTableInvLevelCol.setCellValueFactory(cellData -> cellData.getValue().getPartStockLevelProperty().asObject());
        addTablePPUCol.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());

        // Fetching and setting Delete Table rows
        deleteTablePartIDCol.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        deleteTablePartNameCol.setCellValueFactory(cellData -> cellData.getValue().getPartNameProperty());
        deleteTableInvLevelCol.setCellValueFactory(cellData -> cellData.getValue().getPartStockLevelProperty().asObject());
        deleteTablePPUCol.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());

        // Initializing Add Part and Delete Part table views
        updateAddTable();
        updateDeleteTable();
        testDataHelper();
    }

    // Helper Methods for Updating Table Views
    // Method to create test data (******COMMENT OUT BEFORE FINAL BUILD*******)
    private void testDataHelper() {
        for (int i = 3000; i < 3006; i++) {
            InHouse inHouseTest = new InHouse();
            inHouseTest.setPartID(i);
            inHouseTest.setPartName("Test" + i);
            inHouseTest.setPartStockLevel(i - 2095);
            inHouseTest.setPartPrice(i - 999);
            Inventory.addPart(inHouseTest);
        }
    }

    private void updateAddTable() {
        addTable.setItems(Inventory.getAllParts());
    }

    private void updateDeleteTable() {
        deleteTable.setItems(associatedParts);
    }

    @FXML
    private void searchBtnHandler(ActionEvent event) {
        String partSearchString = searchField.getText();
        int searchedPartIndex;
        if (partSearchString.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR: EMPTY FIELD");
            alert.setHeaderText("Unable to process search");
            alert.setContentText("Search field cannot be blank.");
            alert.showAndWait();
        } else if (Inventory.lookupPart(partSearchString) == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR: PART NOT FOUND");
            alert.setHeaderText("Unable to locate part");
            alert.setContentText("The Part ID or name entered was not found in inventory.");
            alert.showAndWait();
        } else {
            searchedPartIndex = Inventory.lookupPart(partSearchString);
            Part searchedPart = Inventory.getAllParts().get(searchedPartIndex);
            ObservableList<Part> searchedPartList = FXCollections.observableArrayList();
            searchedPartList.add(searchedPart);
            addTable.setItems(searchedPartList);
            searchField.setText("");
        }
    }

    @FXML
    private void searchFieldEnterHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String partSearchString = searchField.getText();
            int searchedPartIndex;
            if (partSearchString.equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR: EMPTY FIELD");
                alert.setHeaderText("Unable to process search");
                alert.setContentText("Search field cannot be blank.");
                alert.showAndWait();
            } else if (Inventory.lookupPart(partSearchString) == -1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR: PART NOT FOUND");
                alert.setHeaderText("Unable to locate part");
                alert.setContentText("The Part ID or name entered was not found in inventory.");
                alert.showAndWait();
            } else {
                searchedPartIndex = Inventory.lookupPart(partSearchString);
                Part searchedPart = Inventory.getAllParts().get(searchedPartIndex);
                ObservableList<Part> searchedPartList = FXCollections.observableArrayList();
                searchedPartList.add(searchedPart);
                addTable.setItems(searchedPartList);
                searchField.setText("");
            }
        }
    }

    @FXML
    private void clearSearchFieldHandler(MouseEvent event) {
        searchField.setText("");
    }

    @FXML
    private void addBtnHandler(ActionEvent event) {

    }

    @FXML
    private void deleteBtnHandler(ActionEvent event) {
    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {
    }

    @FXML
    private void cancelBtnHandler(ActionEvent event) {
    }

}
