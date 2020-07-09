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
    public int getProductID() {
        return productID.get();
    }

    public String getProductName() {
        return productName.get();
    }

    public double getProductPrice() {
        return productPrice.get();
    }

    public int getProductStockLevel() {
        return productStockLevel.get();
    }

    public int getProductMinStockLevel() {
        return productMinStockLevel.get();
    }

    public int getProductMaxStockLevel() {
        return productMaxStockLevel.get();
    }

    public ObservableList<Part> getAssociatedParts() {
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
    public void setProductID(int value) {
        productID.set(value);
    }

    public void setProductName(String value) {
        productName.set(value);
    }

    public void setProductPrice(double value) {
        productPrice.set(value);
    }

    public void setProductStockLevel(int value) {
        productStockLevel.set(value);
    }

    public void setProductMinStockLevel(int value) {
        productMinStockLevel.set(value);
    }

    public void setProductMaxStockLevel(int value) {
        productMaxStockLevel.set(value);
    }

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        Product.associatedParts = associatedParts;
    }

    /**
     * * Exception Handling **
     */
    // Exception handling for entered field values
    public static String productValueExceptions(double prodPrice, int prodStockLevel, int prodMinStockLevel, int prodMaxStockLevel, String productValueException) {
        if (prodPrice <= 0) {
            productValueException = productValueException + "\nProduct Price cannot be $0 or negative.";
        }
        if (prodStockLevel < 1) {
            productValueException = productValueException + "\nProduct Stock Level cannot be zero.";
        }
        if (prodStockLevel < prodMinStockLevel) {
            productValueException = productValueException + "\nProduct Stock Level must be greater than its Minimum Stock Level requirement.";
        }
        if (prodStockLevel > prodMaxStockLevel) {
            productValueException = productValueException + "\nProduct Stock Level must be less than its Maximum Stock Level requirement.";
        }
        if (prodMinStockLevel > prodMaxStockLevel) {
            productValueException = productValueException + "\nProduct Minimum Stock Level cannot be greater than its Maximum Stock Level.";
        }
        return productValueException;
    }

    // Exception handling for empty fields
    public static String productFieldExceptions(String prodName, String prodPrice, String prodStockLevel, String prodMinStockLevel, String prodMaxStockLevel, String productFieldException) {
        if (prodName.equals("")) {
            productFieldException = productFieldException + "\nProduct Name cannot be blank.";
        }
        if (prodPrice.equals("")) {
            productFieldException = productFieldException + "\nProduct Price cannot be blank.";
        }
        if (prodStockLevel.equals("")) {
            productFieldException = productFieldException + "\nProduct Stock Level cannot be blank.";
        }
        if (prodMinStockLevel.equals("")) {
            productFieldException = productFieldException + "\nProduct Minimum Stock Level cannot be blank.";
        }
        if (prodMaxStockLevel.equals("")) {
            productFieldException = productFieldException + "\nProduct Maximum Stock Level cannot be blank.";
        }
        return productFieldException;
    }

    // Exception handling for entered field data types
    private static boolean productPriceDataTypeException(String prodPrice) {
        try {
            Double.parseDouble(prodPrice);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean stockLevelDataTypeException(String prodStockLevel) {
        try {
            Integer.parseInt(prodStockLevel);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean maxLevelDataTypeException(String prodMaxStockLevel) {
        try {
            Integer.parseInt(prodMaxStockLevel);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean minLevelDataTypeException(String prodMinStockLevel) {
        try {
            Integer.parseInt(prodMinStockLevel);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

// Alert message text for Product field value data type exceptions
    public static String prodDataTypeExceptions(String prodPrice, String prodStockLevel, String prodMaxStockLevel, String prodMinStockLevel, String productDataTypeException) {
        if (productPriceDataTypeException(prodPrice) == false) {
            productDataTypeException = productDataTypeException + "\nPart Price must be a number in the format XX.XX";
        }
        if (stockLevelDataTypeException(prodStockLevel) == false) {
            productDataTypeException = productDataTypeException + "\nPart Inventory Level must be a whole number greater than 0.";
        }
        if (maxLevelDataTypeException(prodMaxStockLevel) == false) {
            productDataTypeException = productDataTypeException + "\nPart Maximum Inventory Level must be a whole number.";
        }
        if (minLevelDataTypeException(prodMinStockLevel) == false) {
            productDataTypeException = productDataTypeException + "\nPart Minimum Inventory Level must be a whole number.";
        }
        return productDataTypeException;
    }
}
