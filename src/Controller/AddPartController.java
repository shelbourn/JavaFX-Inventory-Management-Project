/**
 * Controller for the Add Part view
 * Captures user input data for creating new parts, handles exceptions,
 * adds new parts to inventory
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
package Controller;

import Model.InHouse;
import Model.Inventory;
import static Model.Inventory.addPart;
import Model.Outsourced;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddPartController implements Initializable {

    @FXML
    private TextField partIDField;
    @FXML
    private TextField maxLevelField;
    @FXML
    private TextField partNameField;
    @FXML
    private TextField minLevelField;
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outsourcedRadio;
    @FXML
    private ToggleGroup addPartToggleGroup;
    @FXML
    private Label dynamicLabel;
    @FXML
    private TextField dynamicField;
    @FXML
    private TextField priceCostField;
    @FXML
    private TextField invLevelField;

    // Additional properties needed for functionality
    private int partID;
    private boolean inHousePart;
    private String partFieldException = new String();
    private String iHPartDataTypeException = new String();
    private String outsourcedPartDataTypeException = new String();
    private String partValueException = new String();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partID = Inventory.getGeneratedPartID();
        partIDField.setText("Auto-Generated:   " + partID);
    }

    // Dynamically setting the Machine ID / Company Name field based on selected radio button
    @FXML
    private void inHouseRadioHandler(ActionEvent event) {
        inHousePart = true;
        dynamicLabel.setText("Machine ID");
        dynamicField.setPromptText("Machine ID");
    }

    @FXML
    private void outsourcedRadioHandler(ActionEvent event) {
        inHousePart = false;
        dynamicLabel.setText("Company Name");
        dynamicField.setPromptText("Company Name");
    }

    /**
     * *
     * Save Button tests for field/value/data type exceptions, creates
     * in-house/outsourced parts, displays alerts, navigates user back to main
     * screen upon successful part addition.
     */
    @FXML
    private void saveBtnHandler(ActionEvent event) throws IOException {
        String partName = partNameField.getText();
        String inventoryLevel = invLevelField.getText();
        String priceCost = priceCostField.getText();
        String maxInvLevel = maxLevelField.getText();
        String minInvLevel = minLevelField.getText();
        String machIDCompName = dynamicField.getText();

        if (inHousePart == true) {
            partFieldException = Part.inHousePartFieldExceptions(
                    partName,
                    inventoryLevel,
                    priceCost,
                    minInvLevel,
                    maxInvLevel,
                    machIDCompName,
                    partFieldException);

            iHPartDataTypeException = Part.iHPartDataTypeExceptions(
                    priceCost,
                    inventoryLevel,
                    maxInvLevel,
                    minInvLevel,
                    machIDCompName,
                    iHPartDataTypeException);
        } else {
            partFieldException = Part.outsourcedPartFieldExceptions(
                    partName,
                    inventoryLevel,
                    priceCost,
                    minInvLevel,
                    maxInvLevel,
                    machIDCompName,
                    partFieldException);

            outsourcedPartDataTypeException = Part.outsourcedPartDataTypeExceptions(
                    priceCost,
                    inventoryLevel,
                    maxInvLevel,
                    minInvLevel,
                    outsourcedPartDataTypeException);
        }
        if (partFieldException.length() > 0) {
            System.err.println("Empty fields present in form.\nPart not added to inventory.");
            Alert emptyFields = new Alert(Alert.AlertType.WARNING);
            emptyFields.setTitle("ERROR: EMPTY FIELDS PRESENT");
            emptyFields.setHeaderText("This part has not been added to inventory");
            emptyFields.setContentText(partFieldException);
            emptyFields.showAndWait();
            partFieldException = "";
        } else if (iHPartDataTypeException.length() > 0) {
            System.err.println("Invalid data types present.\nPart not added to inventory.");
            Alert invalidIHDataTypes = new Alert(Alert.AlertType.WARNING);
            invalidIHDataTypes.setTitle("ERROR: INVALID DATA TYPES PRESENT");
            invalidIHDataTypes.setHeaderText("This part has not been added to inventory");
            invalidIHDataTypes.setContentText(iHPartDataTypeException);
            invalidIHDataTypes.showAndWait();
            iHPartDataTypeException = "";
        } else if (outsourcedPartDataTypeException.length() > 0) {
            System.err.println("Invalid data types present.\nPart not added to inventory.");
            Alert invalidOutDataTypes = new Alert(Alert.AlertType.WARNING);
            invalidOutDataTypes.setTitle("ERROR: INVALID DATA TYPES PRESENT");
            invalidOutDataTypes.setHeaderText("This part has not been added to inventory");
            invalidOutDataTypes.setContentText(outsourcedPartDataTypeException);
            invalidOutDataTypes.showAndWait();
            outsourcedPartDataTypeException = "";
        } else {
            try {
                partValueException = Part.partValueExceptions(
                        Double.parseDouble(priceCost),
                        Integer.parseInt(inventoryLevel),
                        Integer.parseInt(minInvLevel),
                        Integer.parseInt(maxInvLevel),
                        partValueException);

                if (partValueException.length() > 0) {
                    System.err.println("Invalid field values present.\nPart not added to inventory.");
                    Alert invalidValues = new Alert(Alert.AlertType.WARNING);
                    invalidValues.setTitle("ERROR: INVALID VALUES PRESENT");
                    invalidValues.setHeaderText("This part has not been added to inventory");
                    invalidValues.setContentText(partValueException);
                    invalidValues.showAndWait();
                    partValueException = "";
                } else {
                    if (inHousePart == true) {
                        InHouse inHouse = new InHouse();
                        inHouse.setId(partID);
                        inHouse.setName(partName);
                        inHouse.setStock(Integer.parseInt(inventoryLevel));
                        inHouse.setPrice(Double.parseDouble(priceCost));
                        inHouse.setMax(Integer.parseInt(maxInvLevel));
                        inHouse.setMin(Integer.parseInt(minInvLevel));
                        inHouse.setMachineID(Integer.parseInt(machIDCompName));
                        addPart(inHouse);

                        System.out.println("In-House part " + inHouse + " successfully added to inventory.");
                        Alert partAddSuccess = new Alert(Alert.AlertType.INFORMATION);
                        partAddSuccess.setTitle("SUCCESS: IN-HOUSE PART ADDED");
                        partAddSuccess.setHeaderText("In-House Part " + partName + " Successfully Added to Inventory");
                        partAddSuccess.setContentText("Click OK to return to the main screen.");
                        partAddSuccess.showAndWait();

                        if (partAddSuccess.getResult() == ButtonType.OK) {
                            System.out.println("Exiting to Main Screen.");
                            Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
                            Scene mainScreen = new Scene(root);
                            Stage mainScreenWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            mainScreenWindow.setTitle("ABC Company: Inventory Management System");
                            mainScreenWindow.setScene(mainScreen);
                            mainScreenWindow.show();
                        } else {
                        }
                    } else {
                        Outsourced outsourced = new Outsourced();
                        outsourced.setId(partID);
                        outsourced.setName(partName);
                        outsourced.setStock(Integer.parseInt(inventoryLevel));
                        outsourced.setPrice(Double.parseDouble(priceCost));
                        outsourced.setMax(Integer.parseInt(maxInvLevel));
                        outsourced.setMin(Integer.parseInt(minInvLevel));
                        outsourced.setCompanyName(machIDCompName);
                        addPart(outsourced);

                        System.out.println("Outsourced part " + outsourced + " successfully added to inventory.");
                        Alert partAddSuccess = new Alert(Alert.AlertType.INFORMATION);
                        partAddSuccess.setTitle("SUCCESS: OUTSOURCED PART ADDED");
                        partAddSuccess.setHeaderText("Outsourced Part " + partName + " Successfully Added to Inventory");
                        partAddSuccess.setContentText("Click OK to return to the main screen.");
                        partAddSuccess.showAndWait();

                        if (partAddSuccess.getResult() == ButtonType.OK) {
                            System.out.println("Exiting to Main Screen.");
                            Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
                            Scene mainScreen = new Scene(root);
                            Stage mainScreenWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            mainScreenWindow.setTitle("ABC Company: Inventory Management System");
                            mainScreenWindow.setScene(mainScreen);
                            mainScreenWindow.show();
                        } else {
                        }
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
