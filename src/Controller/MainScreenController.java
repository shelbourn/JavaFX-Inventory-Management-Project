/**
 * Controller for the Main Screen view
 * Displays table views for all parts and all products, includes main navigation for adding, modifying, and deleting parts/products.
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
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fetching and setting Parts Table rows
        partsTablePartIDCol.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        partsTablePartNameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        partsTableInvLevelCol.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());
        partsTablePPUCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());

        // Fetching and setting Products Table rows
        productsTableProductIDCol.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        productsTableProductNameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        productsTableInvLevelCol.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());
        productsTablePPUCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());

        // Initializing Add Part and Delete Part table views
        updatePartsTable();
        updateProductsTable();
    }

    // Helper methods for updating table views
    private void updatePartsTable() {
        partsTable.setItems(Inventory.getAllParts());
    }

    private void updateProductsTable() {
        productsTable.setItems(Inventory.getAllProducts());
    }

    // Parts Table Methods
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
    private void partsSearchBtnHandler(ActionEvent event) {
        String partSearchString = partsSearchField.getText();
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
            System.out.println("Part search succeeded. Parts table view updated.");
            searchedPartIndex = Inventory.lookupPart(partSearchString);
            Part searchedPart = Inventory.getAllParts().get(searchedPartIndex);
            ObservableList<Part> searchedPartList = FXCollections.observableArrayList();
            searchedPartList.add(searchedPart);
            partsTable.setItems(searchedPartList);
            partsSearchField.setText("");
        }
    }

    /**
     * * Executes part search when the ENTER key is typed while the search
     * field is in focus.
     *
     * @param event
     */
    @FXML
    private void partsSearchFieldEnterHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String partSearchString = partsSearchField.getText();
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
                System.out.println("Part search succeeded. Parts table view updated.");
                searchedPartIndex = Inventory.lookupPart(partSearchString);
                Part searchedPart = Inventory.getAllParts().get(searchedPartIndex);
                ObservableList<Part> searchedPartList = FXCollections.observableArrayList();
                searchedPartList.add(searchedPart);
                partsTable.setItems(searchedPartList);
                partsSearchField.setText("");
            }
        }
    }

    /**
     * * Clears the Parts Search Field
     *
     * @param event
     */
    @FXML
    private void clearPartSearchFieldHandler(MouseEvent event
    ) {
        partsSearchField.setText("");
    }

    @FXML
    private void partsAddBtnHandler(ActionEvent event) throws IOException {
        System.out.println("User selected 'ADD PART'.\nOpening ADD PART screen.");
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddPart.fxml"));
        Scene addPartScreen = new Scene(root);
        Stage addPartWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartWindow.setTitle("Add Part");
        addPartWindow.setScene(addPartScreen);
        addPartWindow.show();
    }

    @FXML
    private void partsModifyBtnHandler(ActionEvent event) throws IOException {
        boolean noActiveSelection = partsTable.getSelectionModel().isEmpty();
        partModify = partsTable.getSelectionModel().getSelectedItem();
        partModifyIndex = Inventory.getAllParts().indexOf(partModify);
        String partModifyName = partModify.getName();
        int partModifyID = partModify.getId();

        if (noActiveSelection) {
            System.err.println("No part selected. Part modification cannot be completed.");
            Alert noPartSelected = new Alert(Alert.AlertType.WARNING);
            noPartSelected.setTitle("ERROR: NO PART SELECTED");
            noPartSelected.setHeaderText("Part Modification Failed");
            noPartSelected.setContentText("A part must be selected before it can be modified.");
            noPartSelected.showAndWait();
        } else {
            System.out.println("User has selected " + partModifyName + " with Part ID: " + partModifyID + " to modify.\nOpening Modify Part Screen now.");
            Parent root = FXMLLoader.load(getClass().getResource("/View/ModifyPart.fxml"));
            Scene modifyPartScreen = new Scene(root);
            Stage modifyPartWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            modifyPartWindow.setTitle("Modify Part | " + partModifyName + " | " + "Part ID: " + partModifyID);
            modifyPartWindow.setScene(modifyPartScreen);
            modifyPartWindow.show();
        }
    }

    @FXML
    private void partsDeleteBtnHandler(ActionEvent event
    ) {
        Part partToDelete = partsTable.getSelectionModel().getSelectedItem();
        boolean noActiveSelection = partsTable.getSelectionModel().isEmpty();

        if (noActiveSelection) {
            System.err.println("No part selected. Part deletion cannot be completed.");
            Alert noPartSelected = new Alert(Alert.AlertType.WARNING);
            noPartSelected.setTitle("ERROR: NO PART SELECTED");
            noPartSelected.setHeaderText("Part Deletion Failed");
            noPartSelected.setContentText("A part must be selected before it can be deleted.");
            noPartSelected.showAndWait();
        } else if (!Inventory.deletePartCheck(partToDelete)) {
            System.err.println(partToDelete + " is associated with product(s)\nUnable to delete part.");
            Alert partDelete = new Alert(Alert.AlertType.WARNING);
            partDelete.setTitle("ERROR: UNABLE TO DELETE PART");
            partDelete.setHeaderText(partDelete + " is associated with product(s)");
            partDelete.setContentText("Part cannot be associated with any products in order to be deleted.");
            partDelete.showAndWait();
        } else {
            Alert deleteConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirm.setTitle("CONFIRMATION: DELETE PART FROM INVENTORY");
            deleteConfirm.setHeaderText("Are you sure you would like to delete " + partToDelete + " from inventory?\n\n This cannot be undone!");
            deleteConfirm.setContentText("Click OK to delete this part.\nClick CANCEL to close this window and keep the part.");
            deleteConfirm.showAndWait();

            if (deleteConfirm.getResult() == ButtonType.OK) {
                System.out.println("User confirmed.\n" + partToDelete + " was deleted from inventory");
                Inventory.deletePart(partToDelete);
                updatePartsTable();

                Alert partDeletionAlert = new Alert(Alert.AlertType.INFORMATION);
                partDeletionAlert.setTitle("SUCCESS: PART DELETED");
                partDeletionAlert.setHeaderText(partToDelete + " has been deleted from inventory");
                partDeletionAlert.setContentText("The Parts table has been updated.\n Click OK to close this window.");
                partDeletionAlert.showAndWait();
            } else {
                deleteConfirm.close();
            }
        }
    }

    /**
     * * Products Search Button Handlers searches for Product by ID and Part
     * name. If search field is empty and the button is clicked then an error is
     * thrown. If a product ID or name is not found in inventory then an
     * INFORMATION alert is thrown. If a product ID or name is found then then
     * the part is returned and added to a temporary list that populates the
     * AddTable list view.
     *
     * @param event
     */
    @FXML
    private void productsSearchBtnHandler(ActionEvent event
    ) {
        String productSearchString = productsSearchField.getText();
        int searchedProductIndex;
        if (productSearchString.equals("")) {
            System.err.println("Empty search field present. Product search could not be conducted.");
            Alert emptySearchField = new Alert(Alert.AlertType.INFORMATION);
            emptySearchField.setTitle("ERROR: EMPTY FIELD");
            emptySearchField.setHeaderText("Unable to process search");
            emptySearchField.setContentText("Search field cannot be blank.");
            emptySearchField.showAndWait();
        } else if (Inventory.lookupProduct(productSearchString) == -1) {
            System.err.println("Product not found. Search returned no results.");
            Alert productNotFound = new Alert(Alert.AlertType.INFORMATION);
            productNotFound.setTitle("ERROR: PRODUCT NOT FOUND");
            productNotFound.setHeaderText("Unable to locate product");
            productNotFound.setContentText("The Product ID or name entered was not found in inventory.");
            productNotFound.showAndWait();
        } else {
            System.out.println("Product search succeeded. Products table view updated.");
            searchedProductIndex = Inventory.lookupProduct(productSearchString);
            Product searchedProduct = Inventory.getAllProducts().get(searchedProductIndex);
            ObservableList<Product> searchedProductList = FXCollections.observableArrayList();
            searchedProductList.add(searchedProduct);
            productsTable.setItems(searchedProductList);
            productsSearchField.setText("");
        }
    }

    /**
     * * Executes product search when the ENTER key is typed while the search
     * field is in focus.
     *
     * @param event
     */
    @FXML
    private void productsSearchFieldEnterHandler(KeyEvent event
    ) {
        if (event.getCode() == KeyCode.ENTER) {
            String productSearchString = productsSearchField.getText();
            int searchedProductIndex;
            if (productSearchString.equals("")) {
                System.err.println("Empty search field present. Product search could not be conducted.");
                Alert emptySearchField = new Alert(Alert.AlertType.INFORMATION);
                emptySearchField.setTitle("ERROR: EMPTY FIELD");
                emptySearchField.setHeaderText("Unable to process search");
                emptySearchField.setContentText("Search field cannot be blank.");
                emptySearchField.showAndWait();
            } else if (Inventory.lookupProduct(productSearchString) == -1) {
                System.err.println("Product not found. Search returned no results.");
                Alert productNotFound = new Alert(Alert.AlertType.INFORMATION);
                productNotFound.setTitle("ERROR: PRODUCT NOT FOUND");
                productNotFound.setHeaderText("Unable to locate product");
                productNotFound.setContentText("The Product ID or name entered was not found in inventory.");
                productNotFound.showAndWait();
            } else {
                System.out.println("Product search succeeded. Products table view updated.");
                searchedProductIndex = Inventory.lookupProduct(productSearchString);
                Product searchedProduct = Inventory.getAllProducts().get(searchedProductIndex);
                ObservableList<Product> searchedProductList = FXCollections.observableArrayList();
                searchedProductList.add(searchedProduct);
                productsTable.setItems(searchedProductList);
                productsSearchField.setText("");
            }
        }
    }

    /**
     * * Clears the Product Search Field
     *
     * @param event
     */
    @FXML
    private void clearProductSearchFieldHandler(MouseEvent event
    ) {
        productsSearchField.setText("");
    }

    @FXML
    private void productsAddBtnHandler(ActionEvent event) throws IOException {
        System.out.println("User selected 'ADD PRODUCT'.\nOpening ADD PRODUCT screen.");
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddProduct.fxml"));
        Scene addProductScreen = new Scene(root);
        Stage addProductWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addProductWindow.setTitle("Add Product");
        addProductWindow.setScene(addProductScreen);
        addProductWindow.show();
    }

    @FXML
    private void productsModifyBtnHandler(ActionEvent event) throws IOException {
        boolean noActiveSelection = productsTable.getSelectionModel().isEmpty();
        productModify = productsTable.getSelectionModel().getSelectedItem();
        productModifyIndex = Inventory.getAllProducts().indexOf(productModify);
        String productModifyName = productModify.getName();
        int productModifyID = productModify.getId();

        if (noActiveSelection) {
            System.err.println("No product selected. Product modification cannot be completed.");
            Alert noProductSelected = new Alert(Alert.AlertType.WARNING);
            noProductSelected.setTitle("ERROR: NO PRODUCT SELECTED");
            noProductSelected.setHeaderText("No product has been selected to modify");
            noProductSelected.setContentText("A product must be selected before it can be modified.");
            noProductSelected.showAndWait();
        } else {
            System.out.println("User has selected " + productModifyName + " with Product ID: " + productModifyID + " to modify.\nOpening Modify Product Screen now.");
            Parent root = FXMLLoader.load(getClass().getResource("/View/ModifyProduct.fxml"));
            Scene modifyProductScreen = new Scene(root);
            Stage modifyProductWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            modifyProductWindow.setTitle("Modify Product | " + productModifyName + " | " + "Product ID: " + productModifyID);
            modifyProductWindow.setScene(modifyProductScreen);
            modifyProductWindow.show();
        }
    }

    @FXML
    private void productsDeleteBtnHandler(ActionEvent event
    ) {
        Product productToDelete = productsTable.getSelectionModel().getSelectedItem();
        boolean noActiveSelection = partsTable.getSelectionModel().isEmpty();

        if (noActiveSelection) {
            System.err.println("No product selected. Product deletion cannot be completed.");
            Alert noProductSelected = new Alert(Alert.AlertType.WARNING);
            noProductSelected.setTitle("ERROR: NO PRODUCT SELECTED");
            noProductSelected.setHeaderText("Product Deletion Failed");
            noProductSelected.setContentText("A product must be selected before it can be deleted.");
            noProductSelected.showAndWait();
        } else if (!Inventory.deleteProductCheck(productToDelete)) {
            System.err.println(productToDelete + " has associated part(s).\nUnable to delete product.");
            Alert associatedParts = new Alert(Alert.AlertType.WARNING);
            associatedParts.setTitle("ERROR: UNABLE TO DELETE PRODUCT");
            associatedParts.setHeaderText(productToDelete + " has associated part(s)");
            associatedParts.setContentText("Product cannot be deleted if it has associated parts.");
            associatedParts.showAndWait();
        } else {
            Alert deleteConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirm.setTitle("CONFIRMATION: DELETE PRODUCT FROM INVENTORY");
            deleteConfirm.setHeaderText("Are you sure you would like to delete " + productToDelete + " from inventory?\nThis cannot be undone!");
            deleteConfirm.setContentText("Click OK to delete this product.\nClick CANCEL to close this window and keep the product.");
            deleteConfirm.showAndWait();

            if (deleteConfirm.getResult() == ButtonType.OK) {
                System.out.println("User confirmed.\n" + productToDelete + " has been deleted from inventory");
                Inventory.deleteProduct(productToDelete);
                updateProductsTable();

                Alert productDeletionAlert = new Alert(Alert.AlertType.INFORMATION);
                productDeletionAlert.setTitle("SUCCESS: PRODUCT DELETED");
                productDeletionAlert.setHeaderText(productToDelete + " has been deleted from inventory");
                productDeletionAlert.setContentText("The Products table has been updated.\nClick OK to close this window.");
                productDeletionAlert.showAndWait();
            } else {
                deleteConfirm.close();
            }
        }
    }

    @FXML
    private void exitBtnHandler(ActionEvent event
    ) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("CONFIRMATION: EXIT APPLICATION");
        exitAlert.setHeaderText("Are you sure you would like to exit the application?");
        exitAlert.setContentText("Click OK to exit the application.\nClick CANCEL to continue and return to the current screen.");
        exitAlert.showAndWait();

        if (exitAlert.getResult() == ButtonType.OK) {
            System.out.println("User confirmed.\nExiting application.\nEND.");
            System.exit(0);
        } else {
            System.out.println("User cancelled alert.\nReturning to Main Screen.");
            exitAlert.close();
        }
    }

}
