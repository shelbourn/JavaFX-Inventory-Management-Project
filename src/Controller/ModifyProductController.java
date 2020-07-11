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
public class ModifyProductController implements Initializable {

    @FXML
    private AnchorPane modifyProdScr;
    @FXML
    private AnchorPane modifyProdScrPane;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField productIDField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField maxLevelField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField inventoryLevelField;
    @FXML
    private TextField minLevelField;
    @FXML
    private TableView<?> addTable;
    @FXML
    private TableColumn<?, ?> addTablePartIDCol;
    @FXML
    private TableColumn<?, ?> addTablePartNameCol;
    @FXML
    private TableColumn<?, ?> addTableInvLevelCol;
    @FXML
    private TableColumn<?, ?> addTablePPUCol;
    @FXML
    private Button addBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<?> deleteTable;
    @FXML
    private TableColumn<?, ?> deleteTablePartIDCol;
    @FXML
    private TableColumn<?, ?> deleteTablePartNameCol;
    @FXML
    private TableColumn<?, ?> deleteTableInvLevelCol;
    @FXML
    private TableColumn<?, ?> deleteTablePPUCol;
    @FXML
    private Button deleteBtn;

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
    private void addBtnHandler(ActionEvent event) {
    }

    @FXML
    private void searchBtnHandler(ActionEvent event) {
    }

    @FXML
    private void deleteBtnHandler(ActionEvent event) {
    }

    
}
