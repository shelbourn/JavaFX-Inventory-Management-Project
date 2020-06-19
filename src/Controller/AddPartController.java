/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class AddPartController implements Initializable {

    @FXML
    private AnchorPane addPartScr;
    @FXML
    private AnchorPane addPartScrPane;
    @FXML
    private Button addPartScrSaveBtn;
    @FXML
    private Button addPartScrCancelBtn;
    @FXML
    private RadioButton addPartScrIHRadio;
    @FXML
    private ToggleGroup addPartScrRadioBtns;
    @FXML
    private Label addPartScrMachIDLbl;
    @FXML
    private TextField addPartScrMachIDField;
    @FXML
    private RadioButton addPartScrOutRadio;
    @FXML
    private TextField addPartScrIDField;
    @FXML
    private TextField addPartScrPartPriceField;
    @FXML
    private TextField addPartScrPartMaxField;
    @FXML
    private TextField addPartScrPartNameField;
    @FXML
    private TextField addPartScrPartInvField;
    @FXML
    private TextField addPartScrPartMinField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @FXML
    private void addPartScrSaveBtnHandler(MouseEvent event) {
    }

    @FXML
    private void addPartScrCancelBtnHandler(MouseEvent event) {
    }

    @FXML
    private void addPartScrIHRadioHandler(MouseEvent event) {
    }

    @FXML
    private void addPartScrMachIDFieldHandler(KeyEvent event) {
    }

    @FXML
    private void addPartScrOutRadioHandler(MouseEvent event) {
    }

    @FXML
    private void addPartScrIDFieldHandler(KeyEvent event) {
    }

    @FXML
    private void addPartScrPartPriceFieldHandler(KeyEvent event) {
    }

    @FXML
    private void addPartScrPartMaxFieldHandler(KeyEvent event) {
    }

    @FXML
    private void addPartScrPartNameFieldHandler(KeyEvent event) {
    }

    @FXML
    private void addPartScrPartInvFieldHandler(MouseEvent event) {
    }

    @FXML
    private void addPartScrPartMinFieldHandler(KeyEvent event) {
    }
    
}
