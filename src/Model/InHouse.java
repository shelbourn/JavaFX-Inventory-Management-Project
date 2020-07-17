/**
 * Defines the properties and methods for the InHouse class which is a sub-class of the
 * Part super class
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
package Model;

// Package Imports
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InHouse extends Part {

    // Properties for the InHouse Part sub-class
    private IntegerProperty machineID;

    // Constructor to Instantiate InHouse Part sub-class
    public InHouse() {
        super();
        this.machineID = new SimpleIntegerProperty();
    }

    // Getter for InHouse Part property values
    public int getMachineID() {
        return machineID.get();
    }

    // Getter for InHouse Part properties
    public IntegerProperty getMachineIDProperty() {
        return machineID;
    }

    // Setter for InHouse Part properties
    public void setMachineID(int value) {
        machineID.set(value);
    }
}
