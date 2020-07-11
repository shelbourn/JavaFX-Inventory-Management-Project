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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class MainScreenController implements Initializable {

    @FXML
    private AnchorPane mainScr;
    @FXML
    private Button exitBtn;
    @FXML
    private AnchorPane productsPanel;
    @FXML
    private TableView<?> productsTable;
    @FXML
    private TableColumn<?, ?> productsTableProductIDCol;
    @FXML
    private TableColumn<?, ?> productsTableProductNameCol;
    @FXML
    private TableColumn<?, ?> productsTableInvLevelCol;
    @FXML
    private TableColumn<?, ?> productsTablePPUCol;
    @FXML
    private Button productsAddBtn;
    @FXML
    private Button productsModifyBtn;
    @FXML
    private Button productsDeleteBtn;
    @FXML
    private Button productsSearchBtn;
    @FXML
    private TextField productsSearchField;
    @FXML
    private AnchorPane partPanel;
    @FXML
    private TableView<?> partsTable;
    @FXML
    private TableColumn<?, ?> partsTablePartIDCol;
    @FXML
    private TableColumn<?, ?> partsTablePartNameCol;
    @FXML
    private TableColumn<?, ?> partsTableInvLevelCol;
    @FXML
    private TableColumn<?, ?> partsTablePPUCol;
    @FXML
    private Button partsSearchBtn;
    @FXML
    private TextField partsSearchField;
    @FXML
    private Button partsAddBtn;
    @FXML
    private Button partsModifyBtn;
    @FXML
    private Button partsDeleteBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exitBtnHandler(ActionEvent event) {
    }

    @FXML
    private void productsAddBtnHandler(ActionEvent event) {
    }

    @FXML
    private void productsModifyBtnHandler(ActionEvent event) {
    }

    @FXML
    private void productsDeleteBtnHandler(ActionEvent event) {
    }

    @FXML
    private void productsSearchBtnHandler(ActionEvent event) {
    }

    @FXML
    private void partsSearchBtnHandler(ActionEvent event) {
    }

    @FXML
    private void partsAddBtnHandler(ActionEvent event) {
    }

    @FXML
    private void partsModifyBtnHandler(ActionEvent event) {
    }

    @FXML
    private void partsDeleteBtnHandler(ActionEvent event) {
    }



    
}
