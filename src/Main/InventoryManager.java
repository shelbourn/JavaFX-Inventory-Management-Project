/**
 * Main for ABC Company: Inventory Management System
 * Sets test data, adds test data to inventory, populates Main Screen table views
 * Launches Main Screen
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
package Main;

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

    // Helper Method to create sample In-House parts
    private void inHouseTestParts(
            String partName,
            int inventoryLevel,
            double price,
            int maxInventory,
            int minInventory,
            int machineID) {
        int partID = Inventory.getGeneratedPartID();
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

    // Helper Method to create sample Outsourced parts
    private void outsourcedTestParts(
            String partName,
            int inventoryLevel,
            double price,
            int maxInventory,
            int minInventory,
            String companyName) {
        int partID = Inventory.getGeneratedPartID();
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
            String productIName,
            int inventoryLevel,
            double price,
            int maxInventory,
            int minInventory,
            ObservableList<Part> associatedParts) {
        int productID = Inventory.getGeneratedProductID();
        Product newProductTest = new Product();
        newProductTest.setProductID(productID);
        newProductTest.setProductName(productIName);
        newProductTest.setProductStockLevel(inventoryLevel);
        newProductTest.setProductPrice(price);
        newProductTest.setProductMaxStockLevel(maxInventory);
        newProductTest.setProductMinStockLevel(minInventory);
        Inventory.addProduct(newProductTest);
        newProductTest.setAssociatedParts(associatedParts);
    }

    @Override
    public void start(Stage inventoryManager) throws Exception {

        // Initializing application with test data
        inHouseTestParts("32-bit ARM9 core microprocessor", 12, 199.99, 32, 6, 662);
        inHouseTestParts("64-bit ARM9 core microprocessor", 8, 399.99, 24, 4, 662);
        inHouseTestParts("64 MB DDR2 SDRAM chip", 4, 899.99, 10, 2, 520);
        inHouseTestParts("4 Gbit NAND memory", 20, 99.99, 64, 10, 400);
        inHouseTestParts("Micro USB type B connector", 100, 5.99, 300, 25, 210);
        inHouseTestParts("USB Type A Connector", 97, 3.99, 300, 25, 210);
        inHouseTestParts("USB Type C 3.0 Connector", 73, 10.99, 200, 12, 210);

        outsourcedTestParts("3.3V Voltage Regulator", 6, 19.99, 20, 2, "Newegg Computer Parts");
        outsourcedTestParts("1.8V Voltage Regulator", 6, 13.99, 20, 2, "Newegg Computer Parts");
        outsourcedTestParts("1.0V Voltage Regulator", 6, 9.99, 20, 2, "Newegg Computer Parts");
        outsourcedTestParts("Aluminum Clamshell Computer Frame - Slate", 4, 53.99, 10, 1, "Newegg Computer Parts");
        outsourcedTestParts("Aluminum Clamshell Computer Frame - Silver", 3, 53.99, 10, 1, "Newegg Computer Parts");
        outsourcedTestParts("Aluminum Clamshell Computer Frame - White", 2, 53.99, 10, 1, "Newegg Computer Parts");

        testProducts("32-bit Arm Laptop - Slate", 4, 1199.99, 6, 1, associatedParts);
        testProducts("32-bit Arm Laptop - Silver", 6, 1299.99, 10, 2, associatedParts);
        testProducts("32-bit Arm Laptop - White", 10, 1399.99, 14, 4, associatedParts);
        testProducts("64-bit Arm Laptop - Slate", 4, 1699.99, 6, 1, associatedParts);
        testProducts("64-bit Arm Laptop - Silver", 6, 1799.99, 10, 2, associatedParts);
        testProducts("64-bit Arm Laptop - White", 10, 1899.99, 14, 4, associatedParts);

        // Launching app with test data and opening main screen
        System.out.println("Opening ABC Company: Inventory Management System");
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        Scene mainScreen = new Scene(root);
        inventoryManager.setTitle("ABC Company: Inventory Management System");
        inventoryManager.setScene(mainScreen);
        inventoryManager.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
