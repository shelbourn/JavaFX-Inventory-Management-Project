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
    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty stock;
    private IntegerProperty min;
    private IntegerProperty max;
    public static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    // Constructor to Instantiate Product Class
    public Product() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.stock = new SimpleIntegerProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
    }

    // Getters for Product Property Values
    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }

    public int getStock() {
        return stock.get();
    }

    public int getMin() {
        return min.get();
    }

    public int getMax() {
        return max.get();
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    // Getters for Product Properties
    public IntegerProperty getIdProperty() {
        return id;
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public DoubleProperty getPriceProperty() {
        return price;
    }

    public IntegerProperty getStockProperty() {
        return stock;
    }

    public IntegerProperty getMinProperty() {
        return min;
    }

    public IntegerProperty getMaxProperty() {
        return max;
    }

    // Setters for Product Properties
    public void setId(int value) {
        id.set(value);
    }

    public void setName(String value) {
        name.set(value);
    }

    public void setPrice(double value) {
        price.set(value);
    }

    public void setStock(int value) {
        stock.set(value);
    }

    public void setMin(int value) {
        min.set(value);
    }

    public void setMax(int value) {
        max.set(value);
    }

    public static void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public static boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return true;
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
