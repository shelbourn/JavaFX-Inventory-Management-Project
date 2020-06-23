/**
 * Defines the properties and methods for the InHouse class which is a sub-class of the
 * Part super class
 * 
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */

package Model;

// Package Imports
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InHouse extends Part {
    
    // Properties for the InHouse Part sub-class
    private StringProperty machineID;
    
    // Constructor to Instantiate InHouse Part sub-class
    public InHouse() {
        super();
        this.machineID = new SimpleStringProperty();
    }

    // Getter for InHouse Part properties
    public String getMachineID() {
        return machineID.get();
    }

    // Setter for InHouse Part properties
    public void setMachineID(String value) {
        machineID.set(value);
    }
}
