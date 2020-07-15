/**
 * Controller for the AddProduct.fxml file and Add Product view
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
package Controller;

import Model.InHouse;
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
import javafx.scene.control.Button;
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
public class AddProductController implements Initializable {

    // FXML Generated Properties
    @FXML
    private AnchorPane addProdScr;
    @FXML
    private AnchorPane addProdScrPane;
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

    // Additional properties needed for functionality
    private int productID;
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
        // Fetching and displaying Product ID
        productID = Inventory.getGeneratedProductID();
        productIDField.setText("Auto-Generated:   " + productID);

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

    /**
     * * Search Button Handlers searches for Part by ID and Part name. If
     * search field is empty and the button is clicked then an error is thrown.
     * If a part ID or name is not found in inventory then an INFORMATION alert
     * is thrown. If a part ID or name is found then then the part is returned
     * and added to a temporary list that populates the AddTable list view.
     *
     * @param event
     */
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

    /**
     * * Executes part search when the ENTER key is typed while the search
     * field is in focus.
     *
     * @param event
     */
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

    /**
     * * Clears the search field
     *
     * @param event
     */
    @FXML
    private void clearSearchFieldHandler(MouseEvent event) {
        searchField.setText("");
    }

    /**
     * * Add Button Handler adds the selected part in the Add Parts table view
     * to the current list of Associated Parts that belong to the product. If no
     * part is selected then an error will be thrown.
     *
     * @param event
     */
    @FXML
    private void addBtnHandler(ActionEvent event) {
        boolean noActiveSelection = addTable.getSelectionModel().isEmpty();
        Part selectedPart = addTable.getSelectionModel().getSelectedItem();

        if (noActiveSelection) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR: NO PART SELECTED");
            alert.setHeaderText("No part has been added to product's part list.");
            alert.setContentText("A part must be selected before it can be added to the part list.");
            alert.showAndWait();
        }

        associatedParts.add(selectedPart);
        updateDeleteTable();
    }

    /**
     * * Delete Button Handler first throws a CONFIRMATION alert to confirm
     * that the user wishes to delete a part from the list of Associated Parts
     * that belong to the product. If the user confirms then the part will be
     * removed. If the user cancels then the part will stay in the Associated
     * Parts list.
     *
     * @param event
     */
    @FXML
    private void deleteBtnHandler(ActionEvent event) {
        Part selectedPart = deleteTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION: REMOVE PART");
        alert.setHeaderText("Would you like to remove this part from the product's part list?");
        alert.setContentText("Click OK to remove the part. \n\nClick CANCEL to close this window and keep the part.");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            System.out.println("User confirmed. \nPart has been removed from Product's part list.");
            associatedParts.remove(selectedPart);
            // ?Not sure if I need to call the updateDeleteTable method here or not. Will test.
            updateDeleteTable();
            Alert removeAlert = new Alert(Alert.AlertType.INFORMATION);
            removeAlert.setTitle("SUCCESS: PART REMOVED");
            removeAlert.setHeaderText("The selected part has been removed from the part list.");
            removeAlert.setContentText("Click OK to close this window.");
            removeAlert.showAndWait();
        } else {
            alert.close();
        }
    }

    /**
     * * Save Button Handler verifies that there are no empty fields, incorrect
     * values, or incompatible data types present. If any of the above are
     * present then an exception is thrown and logged. If there are no
     * exceptions to handle then the product will be saved and added to
     * inventory.
     *
     * @param event
     */
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR: EMPTY FIELDS PRESENT");
            alert.setHeaderText("This product has not been added to inventory");
            alert.setContentText(productFieldException);
            alert.showAndWait();
            productFieldException = "";
        } else if (productDataTypeException.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR: INVALID DATA TYPES PRESENT");
            alert.setHeaderText("This product has not been added to inventory");
            alert.setContentText(productDataTypeException);
            alert.showAndWait();
            productDataTypeException = "";
        } else if (productValueException.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR: INVALID VALUES PRESENT");
            alert.setHeaderText("This product has not been added to inventory");
            alert.setContentText(productValueException);
            alert.showAndWait();
            productValueException = "";
        } else {
            try {
                if (associatedParts.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ERROR: NO PARTS ADDED TO PRODUCT");
                    alert.setHeaderText("This product has not been added to inventory");
                    alert.setContentText("A new product must contain at least one part.");
                    alert.showAndWait();
                } else {
                    Product newProduct = new Product();
                    newProduct.setProductID(productID);
                    newProduct.setProductName(productName);
                    newProduct.setProductPrice(Double.parseDouble(productPrice));
                    newProduct.setProductStockLevel(Integer.parseInt(productStockLevel));
                    newProduct.setProductMaxStockLevel(Integer.parseInt(productMaxStockLevel));
                    newProduct.setProductMinStockLevel(Integer.parseInt(productMinStockLevel));
                    newProduct.setAssociatedParts(associatedParts);
                    Inventory.addProduct(newProduct);
                    System.out.println("Product " + productName + " (ID#: " + productID + ") successfully added to inventory.");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCCESS: PRODUCT ADDED");
                    alert.setHeaderText("Product Successfully Added to Inventory");
                    alert.setContentText("Click OK to return to the main screen.");
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        System.out.println("User confirmed product addition. \nExiting to Main Screen.");
                        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                        Scene scene = new Scene(root);
                        Stage mainScreenWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        mainScreenWindow.setTitle("ABC Company: Inventory Management System");
                        mainScreenWindow.setScene(scene);
                        mainScreenWindow.show();
                    } else {
                    }
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * *
     * Cancel button displays a CONFIRMATION alert requesting user to confirm
     * cancel and if they confirm then they are returned to the application's
     * main screen.
     */
    @FXML
    private void cancelBtnHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION: EXIT TO MAIN SCREEN");
        alert.setHeaderText("Would you like to cancel this operation?");
        alert.setContentText("Click OK to cancel operation and return to the main screen. \n\nClick CANCEL to continue and return to the current screen.");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            System.out.println("User cancelled operation. \n\nExiting to Main Screen.");
            Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(root);
            Stage mainScreenWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScreenWindow.setTitle("ABC Company: Inventory Management System");
            mainScreenWindow.setScene(scene);
            mainScreenWindow.show();
        } else {
            alert.close();
        }
    }

}
