/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Part partToModify = getAllParts().get(partModifyIndex);
        partID = getAllParts().get(partModifyIndex).getPartID();
        partIDField.setText("Auto-Generated:   " + partID);
        partNameField.setText(partToModify.getPartName());
        inventoryLevelField.setText(Integer.toString(partToModify.getPartStockLevel()));
        priceCostField.setText(Double.toString(partToModify.getPartPrice()));
        maxLevelField.setText(Integer.toString(partToModify.getPartMaxStockLevel()));
        minLevelField.setText(Integer.toString(partToModify.getPartMinStockLevel()));

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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR: EMPTY FIELDS PRESENT");
            alert.setHeaderText("This part has not been modified");
            alert.setContentText(partFieldException);
            alert.showAndWait();
            partFieldException = "";
        } else if (iHPartDataTypeException.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR: INVALID DATA TYPES PRESENT");
            alert.setHeaderText("This part has not been modified");
            alert.setContentText(iHPartDataTypeException);
            alert.showAndWait();
            iHPartDataTypeException = "";
        } else if (outsourcedPartDataTypeException.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR: INVALID DATA TYPES PRESENT");
            alert.setHeaderText("This part has not been modified");
            alert.setContentText(outsourcedPartDataTypeException);
            alert.showAndWait();
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
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ERROR: INVALID VALUES PRESENT");
                    alert.setHeaderText("This part has not been modified");
                    alert.setContentText(partValueException);
                    alert.showAndWait();
                    partValueException = "";
                } else {
                    if (inHousePart == true) {
                        InHouse inHouse = new InHouse();
                        inHouse.setPartID(partID);
                        inHouse.setPartName(partName);
                        inHouse.setPartStockLevel(Integer.parseInt(inventoryLevel));
                        inHouse.setPartPrice(Double.parseDouble(priceCost));
                        inHouse.setPartMaxStockLevel(Integer.parseInt(maxInvLevel));
                        inHouse.setPartMinStockLevel(Integer.parseInt(minInvLevel));
                        inHouse.setMachineID(Integer.parseInt(machIDCompName));
                        Inventory.updatePart(partModifyIndex, inHouse);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("SUCCESS: IN-HOUSE PART MODIFIED");
                        alert.setHeaderText("In-House Part Successfully Modified in Inventory");
                        alert.setContentText("Click OK to return to the main screen.");
                        alert.showAndWait();

                        if (alert.getResult() == ButtonType.OK) {
                            System.out.println("In-House Part successfully modified in inventory. \nUser confirmed. \nExiting to Main Screen.");
                            Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                            Scene scene = new Scene(root);
                            Stage mainScreenWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            mainScreenWindow.setTitle("ABC Company: Inventory Management System");
                            mainScreenWindow.setScene(scene);
                            mainScreenWindow.show();
                        } else {
                        }
                    } else {
                        Outsourced outsourced = new Outsourced();
                        outsourced.setPartID(partID);
                        outsourced.setPartName(partName);
                        outsourced.setPartStockLevel(Integer.parseInt(inventoryLevel));
                        outsourced.setPartPrice(Double.parseDouble(priceCost));
                        outsourced.setPartMaxStockLevel(Integer.parseInt(maxInvLevel));
                        outsourced.setPartMinStockLevel(Integer.parseInt(minInvLevel));
                        outsourced.setCompanyName(machIDCompName);
                        Inventory.updatePart(partModifyIndex, outsourced);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("SUCCESS: OUTSOURCED PART MODIFIED");
                        alert.setHeaderText("Outsourced Part Successfully Modified in Inventory");
                        alert.setContentText("Click OK to return to the main screen.");
                        alert.showAndWait();

                        if (alert.getResult() == ButtonType.OK) {
                            System.out.println("Outsourced Part successfully modified in inventory. \nUser confirmed. \nExiting to Main Screen.");
                            Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                            Scene scene = new Scene(root);
                            Stage mainScreenWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            mainScreenWindow.setTitle("ABC Company: Inventory Management System");
                            mainScreenWindow.setScene(scene);
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION: EXIT TO MAIN SCREEN");
        alert.setHeaderText("Would you like to cancel this operation?");
        alert.setContentText("Click OK to cancel operation and return to the main screen. \n\nClick CANCEL to continue and return to the current screen.");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            System.out.println("User cancelled operation. Exiting to Main Screen.");
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
