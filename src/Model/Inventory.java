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
    
    // Lookup Part by ID (may want to combine the two search methods into one function)
    public Part lookupPartByID(int partID) {
        for (Part prt : allParts) {
            if (prt.getPartID() == partID)
                return prt;
            }
            return null;
        }
    
    // Lookup Part by Name
    public Part lookupPartByName(String partName) {
        for (Part prt : allParts) {
            if (prt.getPartName() == partName)
                return prt;
        }
        return null;
    }
    
    // Update Part
    public final void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
    
    // Delete Part
    public final boolean deletePart(Part selectedPart) {
        for(int i = 0; i < allProducts.size (); i++) {
            return allProducts.get(i).getAssociatedParts().contains(selectedPart);
        }
        return false;
    }
    
    // Add Product
    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    
    // Lookup Product by ID (may want to combine the two search methods into one function)
    public Product lookupProductByID(int productID) {
        for (Product prd : allProducts) {
            if (prd.getProductID() == productID)
                return prd;
            }
            return null;
        }
    
    // Lookup Product by Name
    public Product lookupProductByName(String productName) {
        for (Product prd : allProducts) {
            if (prd.getProductName() == productName)
                return prd;
        }
        return null;
    }
    
    // Update Product
    public void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }
    
    
}




