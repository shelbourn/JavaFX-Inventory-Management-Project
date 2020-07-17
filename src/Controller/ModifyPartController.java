/**
 * Controller for the Modify Part view
 * Captures user input data for modifying parts, handles exceptions,
 * modifies existing part in inventory
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
package Controller;

import static Controller.MainScreenController.getPartModifyIndex;
import Model.InHouse;
import Model.Inventory;
import static Model.Inventory.getAllParts;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ModifyPartController implements Initializable {

    // FXML Created Properties
    @FXML
    private AnchorPane modifyPartScr;
    @FXML
    private AnchorPane modifyPartScrPane;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private ToggleGroup modifyPartToggleGroup;
    @FXML
    private RadioButton outsourcedRadio;
    @FXML
    private TextField partIDField;
    @FXML
    private TextField maxLevelField;
    @FXML
    private TextField partNameField;
    @FXML
    private TextField inventoryLevelField;
    @FXML
    private TextField priceCostField;
    @FXML
    private TextField minLevelField;
    @FXML
    private Label dynamicLabel;
    @FXML
    private TextField dynamicField;

    // Additional Properties needed for functionality
    private int partID;
    private boolean inHousePart;
    private int partModifyIndex = getPartModifyIndex();
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
        Part partToModify = getAllParts().get(partModifyIndex);
        partID = getAllParts().get(partModifyIndex).getId();
        partIDField.setText("Auto-Generated:   " + partID);
        partNameField.setText(partToModify.getName());
        inventoryLevelField.setText(Integer.toString(partToModify.getStock()));
        priceCostField.setText(Double.toString(partToModify.getPrice()));
        maxLevelField.setText(Integer.toString(partToModify.getMax()));
        minLevelField.setText(Integer.toString(partToModify.getMin()));

        if (partToModify instanceof InHouse) {
            inHouseRadio.setSelected(true);
            dynamicLabel.setText("Machine ID");
            dynamicField.setText(Integer.toString(((InHouse) getAllParts().get(partModifyIndex)).getMachineID()));
        } else {
            outsourcedRadio.setSelected(true);
            dynamicLabel.setText("Company Name");
            dynamicField.setText(((Outsourced) getAllParts().get(partModifyIndex)).getCompanyName());
        }

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

    @FXML
    private void saveBtnHandler(ActionEvent event) {
        String partName = partNameField.getText();
        String inventoryLevel = inventoryLevelField.getText();
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
            System.err.println("Empty fields present. Part has not been modified.");
            Alert emptyFields = new Alert(Alert.AlertType.WARNING);
            emptyFields.setTitle("ERROR: EMPTY FIELDS PRESENT");
            emptyFields.setHeaderText("This part has not been modified");
            emptyFields.setContentText(partFieldException);
            emptyFields.showAndWait();
            partFieldException = "";
        } else if (iHPartDataTypeException.length() > 0) {
            System.err.println("Invalid data types present. Part has not been modified.");
            Alert invalidDataTypes = new Alert(Alert.AlertType.WARNING);
            invalidDataTypes.setTitle("ERROR: INVALID DATA TYPES PRESENT");
            invalidDataTypes.setHeaderText("This part has not been modified");
            invalidDataTypes.setContentText(iHPartDataTypeException);
            invalidDataTypes.showAndWait();
            iHPartDataTypeException = "";
        } else if (outsourcedPartDataTypeException.length() > 0) {
            System.err.println("Invalid data types presents. Part has not been modified.");
            Alert invalidDataTypes = new Alert(Alert.AlertType.WARNING);
            invalidDataTypes.setTitle("ERROR: INVALID DATA TYPES PRESENT");
            invalidDataTypes.setHeaderText("This part has not been modified");
            invalidDataTypes.setContentText(outsourcedPartDataTypeException);
            invalidDataTypes.showAndWait();
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
                    System.err.println("Invalid values present. Part has not been modiied.");
                    Alert invalidValues = new Alert(Alert.AlertType.WARNING);
                    invalidValues.setTitle("ERROR: INVALID VALUES PRESENT");
                    invalidValues.setHeaderText("This part has not been modified");
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
                        Inventory.updatePart(partModifyIndex, inHouse);

                        Alert modifyPartSuccess = new Alert(Alert.AlertType.INFORMATION);
                        modifyPartSuccess.setTitle("SUCCESS: IN-HOUSE PART MODIFIED");
                        modifyPartSuccess.setHeaderText("In-House Part " + inHouse + " Successfully Modified in Inventory");
                        modifyPartSuccess.setContentText("Click OK to return to the main screen.");
                        modifyPartSuccess.showAndWait();

                        if (modifyPartSuccess.getResult() == ButtonType.OK) {
                            System.out.println("In-House Part successfully modified in inventory. \nUser confirmed. \nExiting to Main Screen.");
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
                        Inventory.updatePart(partModifyIndex, outsourced);

                        Alert modifyPartSuccess = new Alert(Alert.AlertType.INFORMATION);
                        modifyPartSuccess.setTitle("SUCCESS: OUTSOURCED PART MODIFIED");
                        modifyPartSuccess.setHeaderText("Outsourced Part " + outsourced + " Successfully Modified in Inventory");
                        modifyPartSuccess.setContentText("Click OK to return to the main screen.");
                        modifyPartSuccess.showAndWait();

                        if (modifyPartSuccess.getResult() == ButtonType.OK) {
                            System.out.println("Outsourced Part successfully modified in inventory.\nUser confirmed. \nExiting to Main Screen.");
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

    @FXML
    private void cancelBtnHandler(ActionEvent event) throws IOException {
        Alert cancelConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        cancelConfirm.setTitle("CONFIRMATION: EXIT TO MAIN SCREEN");
        cancelConfirm.setHeaderText("Would you like to cancel this operation?");
        cancelConfirm.setContentText("Click OK to cancel operation and return to the main screen.\nClick CANCEL to continue and return to the current screen.");
        cancelConfirm.showAndWait();

        if (cancelConfirm.getResult() == ButtonType.OK) {
            System.out.println("User cancelled operation. Exiting to Main Screen.");
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
