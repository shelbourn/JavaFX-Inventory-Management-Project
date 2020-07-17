/**
 * Controller for the Add Product view
 * Captures user input data for new products, performs exception handling, adds parts to product's
 * associate parts list, adds products to inventory.
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
package Controller;

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
    }

    // Helper Methods for Updating Table Views
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
                System.err.println("Empty search field present. Search cannot be conducted.");
                Alert emptySearch = new Alert(Alert.AlertType.INFORMATION);
                emptySearch.setTitle("ERROR: EMPTY FIELD");
                emptySearch.setHeaderText("Unable to process search");
                emptySearch.setContentText("Search field cannot be blank.");
                emptySearch.showAndWait();
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
            System.err.println("No part selected. Part cannot be added to product's associated parts list.");
            Alert noPartSelected = new Alert(Alert.AlertType.WARNING);
            noPartSelected.setTitle("ERROR: NO PART SELECTED");
            noPartSelected.setHeaderText("No part has been added to product's part list.");
            noPartSelected.setContentText("A part must be selected before it can be added to the part list.");
            noPartSelected.showAndWait();
        }

        System.out.println(selectedPart + " successfully added to product's associated parts list.");
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

        Alert deleteConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        deleteConfirm.setTitle("CONFIRMATION: REMOVE PART");
        deleteConfirm.setHeaderText("Would you like to remove " + selectedPart + " from the product's part list?");
        deleteConfirm.setContentText("Click OK to remove the part.\nClick CANCEL to close this window and keep the part.");
        deleteConfirm.showAndWait();

        if (deleteConfirm.getResult() == ButtonType.OK) {
            System.out.println("User confirmed.\n" + selectedPart + " has been removed from Product's part list.");
            associatedParts.remove(selectedPart);
            updateDeleteTable();

            Alert removeAlert = new Alert(Alert.AlertType.INFORMATION);
            removeAlert.setTitle("SUCCESS: PART REMOVED");
            removeAlert.setHeaderText(selectedPart + " has been removed from the part list.");
            removeAlert.setContentText("Click OK to close this window.");
            removeAlert.showAndWait();
        } else {
            System.out.println("User cancelled part removal.\nClosing alert window.");
            deleteConfirm.close();
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
            System.err.println("Empty fields present. Product not added to inventory.");
            Alert emptyFields = new Alert(Alert.AlertType.WARNING);
            emptyFields.setTitle("ERROR: EMPTY FIELDS PRESENT");
            emptyFields.setHeaderText("This product has not been added to inventory");
            emptyFields.setContentText(productFieldException);
            emptyFields.showAndWait();
            productFieldException = "";
        } else if (productDataTypeException.length() > 0) {
            System.err.println("Invalid data types present. Product not added to inventory.");
            Alert invalidDataTypes = new Alert(Alert.AlertType.WARNING);
            invalidDataTypes.setTitle("ERROR: INVALID DATA TYPES PRESENT");
            invalidDataTypes.setHeaderText("This product has not been added to inventory");
            invalidDataTypes.setContentText(productDataTypeException);
            invalidDataTypes.showAndWait();
            productDataTypeException = "";
        } else if (productValueException.length() > 0) {
            System.err.println("Invalid field values present. Product has not been added to inventory.");
            Alert invalidValues = new Alert(Alert.AlertType.WARNING);
            invalidValues.setTitle("ERROR: INVALID VALUES PRESENT");
            invalidValues.setHeaderText("This product has not been added to inventory");
            invalidValues.setContentText(productValueException);
            invalidValues.showAndWait();
            productValueException = "";
        } else {
            try {
                if (associatedParts.isEmpty()) {
                    System.err.println("No parts added to product. Product has not been added to inventory.");
                    Alert noPartsAdded = new Alert(Alert.AlertType.WARNING);
                    noPartsAdded.setTitle("ERROR: NO PARTS ADDED TO PRODUCT");
                    noPartsAdded.setHeaderText("This product has not been added to inventory");
                    noPartsAdded.setContentText("A new product must contain at least one part.");
                    noPartsAdded.showAndWait();
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

                    Alert productAddSuccess = new Alert(Alert.AlertType.INFORMATION);
                    productAddSuccess.setTitle("SUCCESS: PRODUCT ADDED");
                    productAddSuccess.setHeaderText(productName + " Successfully Added to Inventory");
                    productAddSuccess.setContentText("Click OK to return to the main screen.");
                    productAddSuccess.showAndWait();

                    if (productAddSuccess.getResult() == ButtonType.OK) {
                        System.out.println("User confirmed product addition.\nExiting to Main Screen.");
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

    /**
     * *
     * Cancel button displays a CONFIRMATION alert requesting user to confirm
     * cancel and if they confirm then they are returned to the application's
     * main screen.
     */
    @FXML
    private void cancelBtnHandler(ActionEvent event) throws IOException {
        Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
        cancelAlert.setTitle("CONFIRMATION: EXIT TO MAIN SCREEN");
        cancelAlert.setHeaderText("Would you like to cancel this operation?");
        cancelAlert.setContentText("Click OK to cancel operation and return to the main screen.\nClick CANCEL to continue and return to the current screen.");
        cancelAlert.showAndWait();

        if (cancelAlert.getResult() == ButtonType.OK) {
            System.out.println("User cancelled operation.\nExiting to Main Screen.");
            Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            Scene mainScreen = new Scene(root);
            Stage mainScreenWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScreenWindow.setTitle("ABC Company: Inventory Management System");
            mainScreenWindow.setScene(mainScreen);
            mainScreenWindow.show();
        } else {
            cancelAlert.close();
        }
    }

}
