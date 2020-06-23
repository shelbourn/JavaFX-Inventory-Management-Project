/**
 * Abstract class used to store class properties and methods to then be inherited by
 * the InHouse and Outsourced sub-classes
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

public abstract class Part {
    
    // Properties for Part class
    private IntegerProperty partID;
    private StringProperty partName;
    private DoubleProperty partPrice;
    private IntegerProperty partStockLevel;
    private IntegerProperty partMinStockLevel;
    private IntegerProperty partMaxStockLevel;
    
    // Constructor to Instantiate Part Class
    public Part() {
        this.partID = new SimpleIntegerProperty();
        this.partName = new SimpleStringProperty();
        this.partPrice = new SimpleDoubleProperty();
        this.partStockLevel = new SimpleIntegerProperty();
        this.partMinStockLevel = new SimpleIntegerProperty();
        this.partMaxStockLevel = new SimpleIntegerProperty();
    }
    
    // Getters for Part Property Values
    public int getPartID() {
        return partID.get();
    }

    public String getPartName() {
        return partName.get();
    }
    
    public double getPartPrice() {
        return partPrice.get();
    }
    
    public int getPartStockLevel() {
        return partStockLevel.get();
    }
    
    public int getPartMinStockLevel() {
        return partMinStockLevel.get();
    }
    
    public int getPartMaxStockLevel() {
        return partMaxStockLevel.get();
    }
    
    // Getters for Part Properties
    public IntegerProperty getPartIDProperty() {
        return partID;
    }
    
    public StringProperty getPartNameProperty() {
        return partName;
    }
    
    public DoubleProperty getPartPriceProperty() {
        return partPrice;
    }
    
    public IntegerProperty getPartStockLevelProperty() {
        return partStockLevel;
    }
    
    public IntegerProperty getPartMinStockLevelProperty() {
        return partMinStockLevel;
    }
    
    public IntegerProperty getPartMaxStockLevelProperty() {
        return partMaxStockLevel;
    }
    
    // Setters for Part Properties
    public void setPartID(int value) {
        partID.set(value);
    }

    public void setPartName(String value) {
        partName.set(value);
    }


    public void setPartPrice(double value) {
        partPrice.set(value);
    }


    public void setPartStockLevel(int value) {
        partStockLevel.set(value);
    }


    public void setPartMinStockLevel(int value) {
        partMinStockLevel.set(value);
    }


    public void setPartMaxStockLevel(int value) {
        partMaxStockLevel.set(value);
    }
    
    /*** Exception Handling ***/
    
    // Exception handling for entered field values
    private static String partValueExceptions(double partPrice, int partStockLevel, int partMinStockLevel, int partMaxStockLevel, String partValueException) {
        if(partPrice <= 0) {
            partValueException = partValueException + "\nPart Price cannot be $0 or negative.";
        }
        if(partStockLevel < 1) {
            partValueException = partValueException + "\nPart Stock Level cannot be zero.";
        }
        if(partStockLevel < partMinStockLevel) {
            partValueException = partValueException + "\nPart Stock Level must be greater than its Minimum Stock Level requirement.";
        }
        if(partStockLevel > partMaxStockLevel) {
            partValueException = partValueException + "\nPart Stock Level must be less than its Maximum Stock Level requirement.";
        }
        if(partMinStockLevel  > partMaxStockLevel) {
            partValueException = partValueException + "\nPart Minimum Stock Level cannot be greater than its Maximum Stock Level.";
        }
        return partValueException;
    }
    
    // Exception handling for empty fields
    private static String partFieldExceptions(String partName, String partPrice, String partStockLevel, String partMinStockLevel, String partMaxStockLevel, String partFieldException) {
        if(partName.equals("")) {
            partFieldException = partFieldException + "\nPart Name cannot be blank.";
        }
        if(partPrice.equals("")) {
            partFieldException = partFieldException + "\nPart Price cannot be blank.";
        }
        if(partStockLevel.equals("")) {
            partFieldException = partFieldException + "\nPart Stock Level cannot be blank.";
        }
        if(partMinStockLevel.equals("")) {
            partFieldException = partFieldException + "\nPart Minimum Stock Level cannot be blank.";
        }
        if(partMaxStockLevel.equals("")) {
            partFieldException = partFieldException + "\nPart Maximum Stock Level cannot be blank.";
        }
    return partFieldException;
    }
}
