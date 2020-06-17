/**
 * Abstract class used to store class variables and methods to then be inherited by
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
    private IntegerProperty partID = new SimpleIntegerProperty();
    private StringProperty partName = new SimpleStringProperty();
    private DoubleProperty partPrice = new SimpleDoubleProperty();
    private IntegerProperty partStockLevel = new SimpleIntegerProperty();
    private IntegerProperty partMinStockLevel = new SimpleIntegerProperty();
    private IntegerProperty partMaxStockLevel = new SimpleIntegerProperty();
    
    // Getters for Part Property Values
    public final IntegerProperty getPartID() {
        return partID;
    }

    public final StringProperty getPartName() {
        return partName;
    }
    
    public final DoubleProperty getPartPrice() {
        return partPrice;
    }
    
    public final IntegerProperty getPartStockLevel() {
        return partStockLevel;
    }
    
    public final IntegerProperty getPartMinStockLevel() {
        return partMinStockLevel;
    }
    
    public final IntegerProperty getPartMaxStockLevel() {
        return partMaxStockLevel;
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
    public void setPartID(IntegerProperty partID) {
        this.partID = partID;
    }

    public void setPartName(StringProperty partName) {
        this.partName = partName;
    }


    public void setPartPrice(DoubleProperty partPrice) {
        this.partPrice = partPrice;
    }


    public void setPartStockLevel(IntegerProperty partStockLevel) {
        this.partStockLevel = partStockLevel;
    }


    public void setPartMinStockLevel(IntegerProperty partMinStockLevel) {
        this.partMinStockLevel = partMinStockLevel;
    }


    public void setPartMaxStockLevel(IntegerProperty partMaxStockLevel) {
        this.partMaxStockLevel = partMaxStockLevel;
    }
    
    
}
