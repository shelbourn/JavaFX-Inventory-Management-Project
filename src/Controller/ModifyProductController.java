/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.MainScreenController.getProductModifyIndex;
import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    // Additional properties required for functionality
    private int productID;
    private int productModifyIndex = getProductModifyIndex();
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private String productFieldException = new String();
    private String productValueException = new String();
    private String productDataTypeException = new String();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Fetching and populating data for product to modify
        Product productToModify = Inventory.getAllProducts().get(productModifyIndex);
        productID = Inventory.getAllProducts().get(productModifyIndex).getId();
        productIDField.setText("Auto-Generated:   " + productID);
        productNameField.setText(productToModify.getName());
        inventoryLevelField.setText(Integer.toString(productToModify.getStock()));
        priceField.setText(Double.toString(productToModify.getPrice()));
        maxLevelField.setText(Integer.toString(productToModify.getMax()));
        minLevelField.setText(Integer.toString(productToModify.getMin()));

        // Fetching and populating product's associated parts
        associatedParts = productToModify.getAllAssociatedParts();

        // Fetching and setting Add Table rows
        addTablePartIDCol.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        addTablePartNameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        addTableInvLevelCol.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());
        addTablePPUCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());

        // Fetching and setting Delete Table rows
        deleteTablePartIDCol.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        deleteTablePartNameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        deleteTableInvLevelCol.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());
        deleteTablePPUCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());

        // Initializing Add Part and Delete Part table views
        updateAddTable();
        updateDeleteTable();
    }

    // Helper Methods for Updating Table Views
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
            System.err.println("Empty search field present. Part search could not be conducted.");
            Alert emptySearchField = new Alert(Alert.AlertType.INFORMATION);
            emptySearchField.setTitle("ERROR: EMPTY FIELD");
            emptySearchField.setHeaderText("Unable to process search");
            emptySearchField.setContentText("Search field cannot be blank.");
            emptySearchField.showAndWait();
        } else if (Inventory.lookupPart(partSearchString) == -1) {
            System.err.println("Part not found. Search returned no results.");
            Alert partNotFound = new Alert(Alert.AlertType.INFORMATION);
            partNotFound.setTitle("ERROR: PART NOT FOUND");
            partNotFound.setHeaderText("Unable to locate part");
            partNotFound.setContentText("The Part ID or name entered was not found in inventory.");
            partNotFound.showAndWait();
        } else {
            System.out.println("Part search succeeded. Add parts table view updated.");
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
                System.err.println("Empty search field present. Part search could not be conducted.");
                Alert emptySearchField = new Alert(Alert.AlertType.INFORMATION);
                emptySearchField.setTitle("ERROR: EMPTY FIELD");
                emptySearchField.setHeaderText("Unable to process search");
                emptySearchField.setContentText("Search field cannot be blank.");
                emptySearchField.showAndWait();
            } else if (Inventory.lookupPart(partSearchString) == -1) {
                System.err.println("Part not found. Search returned no results.");
                Alert partNotFound = new Alert(Alert.AlertType.INFORMATION);
                partNotFound.setTitle("ERROR: PART NOT FOUND");
                partNotFound.setHeaderText("Unable to locate part");
                partNotFound.setContentText("The Part ID or name entered was not found in inventory.");
                partNotFound.showAndWait();
            } else {
                System.out.println("Part search succeeded. Add parts table view updated.");
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
        boolean noActiveSelection = addTable.getSelectionModel().isEmpty();
        Part selectedPart = addTable.getSelectionModel().getSelectedItem();

        if (noActiveSelection) {
            System.err.println("No part selected. Part cannot be added to product's associated parts list.");
            Alert noPartSelected = new Alert(Alert.AlertType.WARNING);
            noPartSelected.setTitle("ERROR: NO PART SELECTED");
            noPartSelected.setHeaderText("No part has been added to product's part list.");
            noPartSelected.setContentText("A part must be selected before it can be added to the part list.");
            noPartSelected.showAndWait();
        }

        Product.addAssociatedPart(selectedPart);
        updateDeleteTable();
    }

    @FXML
    private void deleteBtnHandler(ActionEvent event) {
        Part selectedPart = deleteTable.getSelectionModel().getSelectedItem();
        String selectedPartName = selectedPart.getName();
        boolean noActiveSelection = addTable.getSelectionModel().isEmpty();

        if (noActiveSelection) {
            System.err.println("No part selected. Part must selected to be removed from product's associated parts list.");
            Alert noPartSelected = new Alert(Alert.AlertType.WARNING);
            noPartSelected.setTitle("ERROR: NO PART SELECTED");
            noPartSelected.setHeaderText("No part has been removed from product's part list.");
            noPartSelected.setContentText("A part must be selected before it can be removed from the part list.");
            noPartSelected.showAndWait();
        }

        Alert deleteConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        deleteConfirm.setTitle("CONFIRMATION: REMOVE PART");
        deleteConfirm.setHeaderText("Would you like to remove " + selectedPartName + " from the product's part list?");
        deleteConfirm.setContentText("Click OK to remove the part.\nClick CANCEL to close this window and keep the part.");
        deleteConfirm.showAndWait();

        if (deleteConfirm.getResult() == ButtonType.OK) {
            System.out.println("User confirmed.\n" + selectedPartName + " has been removed from Product's part list.");
            Product.deleteAssociatedPart(selectedPart);
            associatedParts.remove(selectedPart);
            updateDeleteTable();

            Alert removeAlert = new Alert(Alert.AlertType.INFORMATION);
            removeAlert.setTitle("SUCCESS: PART REMOVED");
            removeAlert.setHeaderText(selectedPartName + " has been removed from the part list.");
            removeAlert.setContentText("Click OK to close this window.");
            removeAlert.showAndWait();
        } else {
            System.out.println("User cancelled part removal.\nClosing alert window.");
            deleteConfirm.close();
        }
    }

    @FXML
    private void saveBtnHandler(ActionEvent event) throws IOException {
        String productName = productNameField.getText();
        String productPrice = priceField.getText();
        String productStockLevel = inventoryLevelField.getText();
        String productMinStockLevel = minLevelField.getText();
        String productMaxStockLevel = maxLevelField.getText();

        productFieldException = Product.productFieldExceptions(
                productName,
                productPrice,
                productStockLevel,
                productMinStockLevel,
                productMaxStockLevel,
                productFieldException);

        productDataTypeException = Product.prodDataTypeExceptions(
                productPrice,
                productStockLevel,
                productMinStockLevel,
                productMaxStockLevel,
                productDataTypeException);

        productValueException = Product.productValueExceptions(
                Double.parseDouble(productPrice),
                Integer.parseInt(productStockLevel),
                Integer.parseInt(productMinStockLevel),
                Integer.parseInt(productMaxStockLevel),
                productValueException);

        if (productFieldException.length() > 0) {
            System.err.println("Empty fields present. Product has not been modified.");
            Alert emptyFields = new Alert(Alert.AlertType.WARNING);
            emptyFields.setTitle("ERROR: EMPTY FIELDS PRESENT");
            emptyFields.setHeaderText("This product has not been modified");
            emptyFields.setContentText(productFieldException);
            emptyFields.showAndWait();
            productFieldException = "";
        } else if (productDataTypeException.length() > 0) {
            System.err.println("Invalid data types present. Product has not been modified.");
            Alert invalidDataTypes = new Alert(Alert.AlertType.WARNING);
            invalidDataTypes.setTitle("ERROR: INVALID DATA TYPES PRESENT");
            invalidDataTypes.setHeaderText("This product has not been modified");
            invalidDataTypes.setContentText(productDataTypeException);
            invalidDataTypes.showAndWait();
            productDataTypeException = "";
        } else if (productValueException.length() > 0) {
            System.err.println("Invalid values present. Product has not been modified.");
            Alert invalidValues = new Alert(Alert.AlertType.WARNING);
            invalidValues.setTitle("ERROR: INVALID VALUES PRESENT");
            invalidValues.setHeaderText("This product has not been modified");
            invalidValues.setContentText(productValueException);
            invalidValues.showAndWait();
            productValueException = "";
        } else {
            try {
                if (associatedParts.isEmpty()) {
                    System.err.println("No parts added to product. Product has not been modified.");
                    Alert noPartsAdded = new Alert(Alert.AlertType.WARNING);
                    noPartsAdded.setTitle("ERROR: NO PARTS ADDED TO PRODUCT");
                    noPartsAdded.setHeaderText("This product has not been modified");
                    noPartsAdded.setContentText("A product must contain at least one part.");
                    noPartsAdded.showAndWait();
                } else {
                    Product updatedProduct = new Product();
                    updatedProduct.setId(productID);
                    updatedProduct.setName(productName);
                    updatedProduct.setPrice(Double.parseDouble(productPrice));
                    updatedProduct.setStock(Integer.parseInt(productStockLevel));
                    updatedProduct.setMax(Integer.parseInt(productMaxStockLevel));
                    updatedProduct.setMin(Integer.parseInt(productMinStockLevel));
                    updatedProduct.setAssociatedParts(associatedParts);
                    Inventory.updateProduct(productModifyIndex, updatedProduct);
                    System.out.println("Product " + productName + " (ID#: " + productID + ") successfully modified.");

                    Alert productModifySuccess = new Alert(Alert.AlertType.INFORMATION);
                    productModifySuccess.setTitle("SUCCESS: PRODUCT MODIFIED");
                    productModifySuccess.setHeaderText(productName + " Successfully Modified");
                    productModifySuccess.setContentText("Click OK to return to the main screen.");
                    productModifySuccess.showAndWait();

                    if (productModifySuccess.getResult() == ButtonType.OK) {
                        System.out.println("User confirmed product modification.\nExiting to Main Screen.");
                        Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
                        Scene mainScreen = new Scene(root);
                        Stage mainScreenWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        mainScreenWindow.setTitle("ABC Company: Inventory Management System");
                        mainScreenWindow.setScene(mainScreen);
                        mainScreenWindow.show();
                    } else {
                    }
                }
            } catch (IOException e) {
            }
        }
    }

    @FXML
    private void cancelBtnHandler(ActionEvent event) throws IOException {
        Alert cancelConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        cancelConfirm.setTitle("CONFIRMATION: EXIT TO MAIN SCREEN");
        cancelConfirm.setHeaderText("Would you like to cancel this operation?");
        cancelConfirm.setContentText("Click OK to cancel operation and return to the main screen.\nClick CANCEL to continue and return to the current screen.");
        cancelConfirm.showAndWait();

        if (cancelConfirm.getResult() == ButtonType.OK) {
            System.out.println("User cancelled operation.\nExiting to Main Screen.");
            Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            Scene mainScreen = new Scene(root);
            Stage mainScreenWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScreenWindow.setTitle("ABC Company: Inventory Management System");
            mainScreenWindow.setScene(mainScreen);
            mainScreenWindow.show();
        } else {
            cancelConfirm.close();
        }
    }

}
