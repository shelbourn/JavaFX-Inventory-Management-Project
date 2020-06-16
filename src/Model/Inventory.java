/**
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 * 
 * Enables adding, deleting, modifying, and searching for parts and products in inventory.
 */

package Model;

/***
 * JavaFX package imports to enable observable array lists
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {
    
    // Variables
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();
  
    // Getter for allParts
    public ObservableList<Part> getParts(){
        return allParts;
    }
    
    //Getter for allProducts
    public ObservableList<Product> getProducts(){
        return allProducts;
    }
    
    // Add Part method
    public void addPart (Part newPart) {
        allParts.add(newPart);
    }
    
    // Add Product method
    public void addProduct (Product newProduct){
        allProducts.add(newProduct);
    }
    
    public Part lookupPart(int partId){
        for (Part p : allParts){
            p.
        }
    }
}


