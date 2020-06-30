/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Inventory;
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

public class AddPartController implements Initializable {

    // FMXL Generated Properties
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outsourcedRadio;
    @FXML
    private TextField partIDField;
    @FXML
    private TextField maxLevelField;
    @FXML
    private TextField partNameField;
    @FXML
    private TextField minLevelField;
    @FXML
    private AnchorPane addPartScr;
    @FXML
    private AnchorPane addPartScrPane;
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
    @FXML
    private int partID;
    @FXML
    private boolean inHousePart;
    @FXML
    private String exceptionEmptyFields;
    @FXML
    private String exceptionFieldValues;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partID = Inventory.getGeneratedPartID();
        partIDField.setText("Auto-Generated:" + partID);
    }    

    @FXML
    private void saveBtnHandler(ActionEvent event) {
    }

    // Try to outsource this code to @AlertMessages.java
    @FXML
    private void cancelBtnHandler(ActionEvent event) throws IOException {
      try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit to Main Screen");
            alert.setHeaderText("Would you like to cancel this operation?");
            alert.setContentText("Click OK to cancel operation and return to the main screen. \nClick CANCEL to continue operation and return to the current screen.");
            alert.showAndWait();
            
            if (alert.getResult() == ButtonType.OK) {
                Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(root);
                Stage mainScreenWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainScreenWindow.setTitle("ABC Company: Inventory Management System");
                mainScreenWindow.setScene(scene);
                mainScreenWindow.show();
            }
            else {
                alert.close();
            }
        }
            catch (IOException e) {}
    }

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
        dynamicField.setText("Company Name");
    }
}
