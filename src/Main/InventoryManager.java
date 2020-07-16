package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.InHouse;
import Model.Inventory;
import static Model.Inventory.addPart;
import Model.Part;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class InventoryManager extends Application {

    // Method to create sample parts
    private void inHouseTestParts(int partID, String partName, int inventoryLevel, double price, int maxInventory, int minInventory, int machineID) {
        partID = Inventory.getGeneratedPartID();
        Part newInHouse = new InHouse();
        newInHouse.setPartID(partID);
        newInHouse.setPartName(partName);
        newInHouse.setPartStockLevel(inventoryLevel);
        newInHouse.setPartPrice(price);
        newInHouse.setPartMaxStockLevel(maxInventory);
        newInHouse.setPartMinStockLevel(minInventory);
        newInHouse.setMachineID(machineID);
        addPart(newInHouse);
    }

    private void outsourcedTestParts(int partID, String partName, int inventoryLevel, double price, int maxInventory, int minInventory, String companyName) {
        partID = Inventory.getGeneratedPartID();
        Part newInHouse = new InHouse();
        newInHouse.setPartID(partID);
        newInHouse.setPartName(partName);
        newInHouse.setPartStockLevel(inventoryLevel);
        newInHouse.setPartPrice(price);
        newInHouse.setPartMaxStockLevel(maxInventory);
        newInHouse.setPartMinStockLevel(minInventory);
        newInHouse.setMachineID(machineID);
        addPart(newInHouse);
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
