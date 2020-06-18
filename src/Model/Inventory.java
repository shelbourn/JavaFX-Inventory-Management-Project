/**
 * Enables adding, deleting, modifying, and searching for parts and products in inventory.
 * 
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */

package Model;

// Package Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    
    // Properties for Inventory class
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    // Getters for Inventory properties 
    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    
    // Setters for Inventory properties
    public void setAllParts(ObservableList<Part> allParts) {
        this.allParts = allParts;
    }

    public void setAllProducts(ObservableList<Product> allProducts) {
        this.allProducts = allProducts;
    }
    
    /*** Methods for Inventory Class ***/
    
    // Add Part
    public void addPart(Part newPart) {
        allParts.add(newPart);
    }
    
    // Add Product
    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    
    // Lookup Part
    public Part lookupPart(int partID) {
        for (Part prt : allParts) {
            if (prt.getPartID() == partID)
                return prt;
            }
            return null;
        }
    
    // Lookup Product
    public Product lookupProduct(int productID) {
        for (Product prd : allProducts) {
            if (prd.getProductID() == productID)
                return prd;
            }
            return null;
        }
}




