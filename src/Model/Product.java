/**
 * Class to store all properties and methods associated with Products.
 * 
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */

package Model;

// Package Imports
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Product {
    
    // Properties for Product Class
    private IntegerProperty productID;
    private StringProperty productName;
    private DoubleProperty productPrice;
    private IntegerProperty productStockLevel;
    private IntegerProperty productMinStockLevel;
    private IntegerProperty productMaxStockLevel;
    public static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
    // Constructor to Instantiate Product Class
    public Product() {
        this.productID = new SimpleIntegerProperty();
        this.productName = new SimpleStringProperty();
        this.productPrice = new SimpleDoubleProperty();
        this.productStockLevel = new SimpleIntegerProperty();
        this.productMinStockLevel = new SimpleIntegerProperty();
        this.productMaxStockLevel = new SimpleIntegerProperty();
    }
    
    // Getters for Product Property Values

    public final IntegerProperty getProductID() {
        return productID;
    }

    public final StringProperty getProductName() {
        return productName;
    }
    
    public final DoubleProperty getProductPrice() {
        return productPrice;
    }
    
    public final IntegerProperty getProductStockLevel() {
        return productStockLevel;
    }
    
    public final IntegerProperty getProductMinStockLevel() {
        return productMinStockLevel;
    }
    
    public final IntegerProperty getProductMaxStockLevel() {
        return productMaxStockLevel;
    }
    
    public final static ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    // Getters for Product Properties
    public IntegerProperty getProductIDProperty() {
        return productID;
    }
    
    public StringProperty getProductNameProperty() {
        return productName;
    }
    
    public DoubleProperty getProductPriceProperty() {
        return productPrice;
    }
    
    public IntegerProperty getProductStockLevelProperty() {
        return productStockLevel;
    }
    
    public IntegerProperty getProductMinStockLevelProperty() {
        return productMinStockLevel;
    }
    
    public IntegerProperty getProductMaxStockLevelProperty() {
        return productMaxStockLevel;
    }
    
    // Setters for Product Properties
    public final void setProductID(IntegerProperty productID) {
        this.productID = productID;
    }
    
    public final void setProductName(StringProperty productName) {
        this.productName = productName;
    }

    public final void setProductPrice(DoubleProperty productPrice) {
        this.productPrice = productPrice;
    }

    public final void setProductStockLevel(IntegerProperty productStockLevel) {
        this.productStockLevel = productStockLevel;
    }

    public final void setProductMinStockLevel(IntegerProperty productMinStockLevel) {
        this.productMinStockLevel = productMinStockLevel;
    }

    public final void setProductMaxStockLevel(IntegerProperty productMaxStockLevel) {
        this.productMaxStockLevel = productMaxStockLevel;
    }
    
    public final static void setAssociatedParts(ObservableList<Part> associatedParts) {
        Product.associatedParts = associatedParts;
    }
}
