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

    public final int getProductID() {
        return productID.get();
    }

    public final String getProductName() {
        return productName.get();
    }
    
    public final double getProductPrice() {
        return productPrice.get();
    }
    
    public final int getProductStockLevel() {
        return productStockLevel.get();
    }
    
    public final int getProductMinStockLevel() {
        return productMinStockLevel.get();
    }
    
    public final int getProductMaxStockLevel() {
        return productMaxStockLevel.get();
    }
    
    public final ObservableList<Part> getAssociatedParts() {
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
    public final void setProductID(int value) {
        productID.set(value);
    }
    
    public final void setProductName(String value) {
        productName.set(value);
    }

    public final void setProductPrice(double value) {
        productPrice.set(value);
    }

    public final void setProductStockLevel(int value) {
        productStockLevel.set(value);
    }

    public final void setProductMinStockLevel(int value) {
        productMinStockLevel.set(value);
    }

    public final void setProductMaxStockLevel(int value) {
        productMaxStockLevel.set(value);
    }
    
    public final void setAssociatedParts(ObservableList<Part> associatedParts) {
        Product.associatedParts = associatedParts;
    }
}
