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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class MainScreenController implements Initializable {

    @FXML
    private AnchorPane mainScrProdPane;
    @FXML
    private TableView<?> mainScrPartTab1;
    @FXML
    private TableColumn<?, ?> mainScrProdIDCol;
    @FXML
    private TableColumn<?, ?> mainScrProdNameCol;
    @FXML
    private TableColumn<?, ?> mainScrInvLvlProdCol;
    @FXML
    private TableColumn<?, ?> mainScrPriceCol;
    @FXML
    private Label mainScrProdLbl;
    @FXML
    private Button mainScrProdAddBtn;
    @FXML
    private Button mainScrProdModifyBtn;
    @FXML
    private Button mainScrProdDeleteBtn;
    @FXML
    private Button mainScrProdSearchBtn;
    @FXML
    private TextField mainScrPartSearchField;
    @FXML
    private AnchorPane mainScrPartPane;
    @FXML
    private TableView<?> mainScrPartTab;
    @FXML
    private TableColumn<?, ?> mainScrPartIDCol;
    @FXML
    private TableColumn<?, ?> mainScrPartNmCol;
    @FXML
    private TableColumn<?, ?> mainScrInvLvlPartCol;
    @FXML
    private TableColumn<?, ?> mainScrCostCol;
    @FXML
    private Label mainScrPartsLbl;
    @FXML
    private Button mainScrPartSearchBtn;
    @FXML
    private Button mainScrPartAddBtn;
    @FXML
    private Button mainScrPartModifyBtn;
    @FXML
    private Button mainScrPartDeleteBtn;
    @FXML
    private Button mainScrExitBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mainScrProdAddBtnHandler(MouseEvent event) {
    }

    @FXML
    private void mainScrProdModifyBtn(MouseEvent event) {
    }

    @FXML
    private void mainScrProdDeleteBtnHandler(MouseEvent event) {
    }

    @FXML
    private void mainScrProdSearchBtnHandler(MouseEvent event) {
    }

    @FXML
    private void mainScrProdSearchFieldHandler(InputMethodEvent event) {
    }

    @FXML
    private void mainScrPartSearchBtnHandler(MouseEvent event) {
    }

    @FXML
    private void mainScrPartSearchFieldHandler(InputMethodEvent event) {
    }

    @FXML
    private void mainScrPartAddBtnHandler(MouseEvent event) {
    }

    @FXML
    private void mainScrPartModifyBtn(MouseEvent event) {
    }

    @FXML
    private void mainScrPartDeleteBtnHandler(MouseEvent event) {
    }

    @FXML
    private void mainScrExitBtnHandler(MouseEvent event) {
    }
    
}
