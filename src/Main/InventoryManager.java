package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.InHouse;
import Model.Inventory;
import static Model.Inventory.addPart;
import Model.Outsourced;
import Model.Part;
import Model.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class InventoryManager extends Application {

    // Properties required for helper methods
    ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    // Helper Method to create sample parts
    private void inHouseTestParts(
            int partID,
            String partName,
            int inventoryLevel,
            double price,
            int maxInventory,
            int minInventory,
            int machineID) {
        partID = Inventory.getGeneratedPartID();
        Part newInHouseTest = new InHouse();
        newInHouseTest.setPartID(partID);
        newInHouseTest.setPartName(partName);
        newInHouseTest.setPartStockLevel(inventoryLevel);
        newInHouseTest.setPartPrice(price);
        newInHouseTest.setPartMaxStockLevel(maxInventory);
        newInHouseTest.setPartMinStockLevel(minInventory);
        newInHouseTest.setMachineID(machineID);
        addPart(newInHouseTest);
        associatedParts.add(newInHouseTest);
    }

    private void outsourcedTestParts(
            int partID,
            String partName,
            int inventoryLevel,
            double price,
            int maxInventory,
            int minInventory,
            String companyName) {
        partID = Inventory.getGeneratedPartID();
        Part newOutsourcedTest = new Outsourced();
        newOutsourcedTest.setPartID(partID);
        newOutsourcedTest.setPartName(partName);
        newOutsourcedTest.setPartStockLevel(inventoryLevel);
        newOutsourcedTest.setPartPrice(price);
        newOutsourcedTest.setPartMaxStockLevel(maxInventory);
        newOutsourcedTest.setPartMinStockLevel(minInventory);
        newOutsourcedTest.setCompanyName(companyName);
        addPart(newOutsourcedTest);
        associatedParts.add(newOutsourcedTest);
    }

    // Helper Method to create sample products
    private void testProducts(
            int productID,
            String productIName,
            int inventoryLevel,
            double price,
            int maxInventory,
            int minInventory,
            ObservableList<Part> associatedParts) {
        productID = Inventory.getGeneratedProductID();
        Product newProductTest = new Product();
        newProductTest.setProductID(productID);
        newProductTest.setProductName(productIName);
        newProductTest.setProductStockLevel(inventoryLevel);
        newProductTest.setProductPrice(price);
        newProductTest.setProductMaxStockLevel(maxInventory);
        newProductTest.setProductMinStockLevel(minInventory);
        newProductTest.setAssociatedParts(associatedParts);
        Inventory.addProduct(newProductTest);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
