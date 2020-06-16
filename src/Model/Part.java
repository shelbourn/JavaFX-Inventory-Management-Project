/**
 * Abstract class used to store class variables and methods to then be inherited by
 * the InHouse and Outsourced sub-classes
 * 
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */

package Model;

public abstract class Part {
    
    // Variables
    private int partID;
    private String partName;
    private double partPrice;
    private int partStockLevel;
    private int minStockLevelPart;
    private int maxStockLevelPart;

    // Getter and Setter for partID
    public int getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    // Getter and Setter for partName
    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    // Getter and Setter for partPrice
    public double getPrice() {
        return partPrice;
    }

    public void setPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    // Getter and Setter for partStockLevel
    public int getStockLevel() {
        return partStockLevel;
    }

    public void setStockLevel(int partStockLevel) {
        this.partStockLevel = partStockLevel;
    }

    // Getter and Setter for minStockLevelPart
    public int getMinStockLevel() {
        return minStockLevelPart;
    }

    public void setMinStockLevel(int minStockLevelPart) {
        this.minStockLevelPart = minStockLevelPart;
    }

    // Getter and Setter for maxStockLevelPart
    public int getMaxStockLevel() {
        return maxStockLevelPart;
    }

    public void setMaxStockLevel(int maxStockLevelPart) {
        this.maxStockLevelPart = maxStockLevelPart;
    }
    
    
}
