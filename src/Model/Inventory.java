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
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int generatedPartID = 1000;
    private static int generatedProductID = 1000;

    // Getters for Inventory property values 
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    
    public static int getGeneratedPartID() {
        generatedPartID++;
        return generatedPartID;
    }
    
    public static int getGeneratedProductID() {
        generatedProductID++;
        return generatedProductID;
    }
       
    // Setters for Inventory properties
    public static void setAllParts(ObservableList<Part> allParts) {
        Inventory.allParts = allParts;
    }

    public static void setAllProducts(ObservableList<Product> allProducts) {
        Inventory.allProducts = allProducts;
    }
    
    /*** Methods for Inventory Class
     * @param newPart ***/
    
    // Add Part
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    
    // Lookup Part by ID (may want to combine the two search methods into one function)
    public static Part lookupPartByID(int partID) {
        for (Part prt : allParts) {
            if (prt.getPartID() == partID)
                return prt;
            }
            return null;
        }
    
    // Lookup Part by Name
    public static Part lookupPartByName(String partName) {
        for (Part prt : allParts) {
            if (prt.getPartName().equals(partName))
                return prt;
        }
        return null;
    }
    
    // Update Part
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
    
    // Delete Part (Only deletes part from product's associated parts)
    // May need to add another method to delete part from inventory completely
    public static boolean deleteAssociatedPart(Part selectedPart) {
        for (Product allProduct : allProducts) {
            if (Product.getAssociatedParts().contains(selectedPart)){
                Product.getAssociatedParts().remove(selectedPart);
                return true; 
            }
        }
        return false;
    }
    
    // Delete Part (Deletes part from Inventory) *** Not sure if this and above method need
    // to be combined ***
    public static boolean deletePart (Part selectedPart) {
        for(int i = 0; i < allParts.size(); i++) {
            if(allParts.get(i).getPartID() == selectedPart.getPartID()) {
                allParts.remove(i);
                return true;
            }
        }
        System.out.println("This part was not found in inventory.");
        return false;
    }
    
    // Add Product
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    
    // Lookup Product by ID (may want to combine the two search methods into one function)
    public static Product lookupProductByID(int productID) {
        for (Product prd : allProducts) {
            if (prd.getProductID() == productID)
                return prd;
            }
            return null;
        }
    
    // Lookup Product by Name
    public static Product lookupProductByName(String productName) {
        for (Product prd : allProducts) {
            if (prd.getProductName().equals(productName))
                return prd;
        }
        return null;
    }
    
    // Update Product
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }
    
    // Delete Product
    public static boolean deleteProduct(Product selectedProduct) {
        for(int i = 0; i < allProducts.size (); i++) {
           if(allProducts.get(i).getProductID() == selectedProduct.getProductID()) {
               allProducts.remove(i);
               return true;
           }
        }
        System.out.println("This product was not found in inventory.");
        return false;
    }
}




