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
    private Button mainScrExitBtn;
    @FXML
    private AnchorPane modifyPartScr;
    @FXML
    private Button modifyPartScrSaveBtn;
    @FXML
    private Button modifyPartScrCancelBtn;
    @FXML
    private RadioButton modifyPartScrIHRadio;
    @FXML
    private ToggleGroup modifyPartScrRadioBtns;
    @FXML
    private Label modifyPartScrMachIDLbl;
    @FXML
    private TextField modifyPartScrMachIDField;
    @FXML
    private RadioButton modifyPartScrOutRadio;
    @FXML
    private TextField modifyPartScrIDField;
    @FXML
    private TextField modifyPartScrPartPriceField;
    @FXML
    private TextField modifyPartScrPartMaxField;
    @FXML
    private TextField modifyPartScrPartNameField;
    @FXML
    private TextField modifyPartScrPartInvField;
    @FXML
    private TextField modifyPartScrPartMinField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mainScrExitBtnHandler(MouseEvent event) {
    }


    @FXML
    private void modifyPartScrSaveBtnHandler(MouseEvent event) {
    }

    @FXML
    private void modifyPartScrCancelBtnHandler(MouseEvent event) {
    }

    @FXML
    private void modifyPartScrIHRadioHandler(MouseEvent event) {
    }

    @FXML
    private void modifyPartScrMachIDFieldHandler(KeyEvent event) {
    }

    @FXML
    private void modifyPartScrOutRadioHandler(MouseEvent event) {
    }

    @FXML
    private void modifyPartScrIDFieldHandler(KeyEvent event) {
    }

    @FXML
    private void modifyPartScrPartPriceFieldHandler(KeyEvent event) {
    }

    @FXML
    private void modifyPartScrPartMaxFieldHandler(KeyEvent event) {
    }

    @FXML
    private void modifyPartScrPartNameFieldHandler(KeyEvent event) {
    }

    @FXML
    private void modifyPartScrPartInvFieldHandler(MouseEvent event) {
    }

    @FXML
    private void modifyPartScrPartMinFieldHandler(KeyEvent event) {
    }
    
}
