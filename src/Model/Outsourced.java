/**
 * Defines the properties and methods for the Outsourced class which is a sub-class of the
 * Part super class
 * 
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */

package Model;

// Package Imports
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Outsourced extends Part {
    
    // Properties for Outsourced Part sub-class
    private StringProperty companyName;
    
    // Constructor to Instantiate Outsourced Part sub-class
    public Outsourced() {
        super();
        this.companyName = new SimpleStringProperty();
    }

    // Getter for Outsourced Part properties
    public final StringProperty getCompanyName() {
        return companyName;
    }

    // Setter for Outsourced Part properties
    public final void setCompanyName(StringProperty companyName) {
        this.companyName = companyName;
    }
}
