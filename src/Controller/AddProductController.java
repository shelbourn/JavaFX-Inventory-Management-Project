/**
 * Controller for the AddProduct.fxml file and Add Product view
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
package Controller;

import Model.Inventory;
import Model.Part;
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
        // TODO
    }

    // Add ability to clear the search field
    // Search ID and Name or just ID???
    @FXML
    private void searchBtnHandler(ActionEvent event) {
        String partSearchString = searchField.getText();
        int searchedPartIndex;
        if (Inventory.lookupPart(partSearchString) == -1) {
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
        }
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
