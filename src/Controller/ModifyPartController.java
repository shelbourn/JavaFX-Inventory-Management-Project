/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class ModifyPartController implements Initializable {

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
    private Label dynamicallyAssignedLabel;
    @FXML
    private TextField dynamicallyAssignedField;
    @FXML
    private RadioButton outsourcedRadio;
    @FXML
    private TextField partIDField;
    @FXML
    private TextField priceCostFiled;
    @FXML
    private TextField maxLevelField;
    @FXML
    private TextField partNameField;
    @FXML
    private TextField inventoryLevelField;
    @FXML
    private TextField minLevelField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveBtnHandler(ActionEvent event) {
    }

    @FXML
    private void cancelBtnHandler(ActionEvent event) {
    }

    @FXML
    private void inHouseRadioHandler(ActionEvent event) {
    }

    @FXML
    private void outsourcedRadioHandler(ActionEvent event) {
    }
    
}
