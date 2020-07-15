/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.MainScreenController.getPartModifyIndex;
import Model.InHouse;
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

    @FXML
    private void saveBtnHandler(ActionEvent event) {
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
