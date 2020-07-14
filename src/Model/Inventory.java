/**
 * Enables adding, deleting, modifying, and searching for parts and products in inventory.
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
package Model;

// Package Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    // Properties for Inventory class
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int generatedPartID = 1000;
    private static int generatedProductID = 1000;

    // Getters for Inventory property values
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static int getGeneratedPartID() {
        generatedPartID++;
        return generatedPartID;
    }

    public static int getGeneratedProductID() {
        generatedProductID++;
        return generatedProductID;
    }

    /**
     * * Methods for Inventory Class
     */
    // Integer validation for input
    public static boolean validInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Add Part
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    // Lookup parts by Part ID or Part Name
    public static int lookupPart(String partSearch) {
        boolean partFound = false;
        int partIndex = 0;
        if (validInt(partSearch)) {
            for (int i = 0; i < allParts.size(); i++) {
                if (Integer.parseInt(partSearch) == allParts.get(i).getPartID()) {
                    partIndex = i;
                    partFound = true;
                }
            }
        } else {
            for (int i = 0; i < allParts.size(); i++) {
                partSearch = partSearch.toLowerCase();
                if (partSearch.contains(allParts.get(i).getPartName().toLowerCase())) {
                    partIndex = i;
                    partFound = true;
                }
            }
        }
        if (partFound == true) {
            return partIndex;
        } else {
            System.out.println("ERROR: Unable to locate any parts based on the given search parameters.");
            return -1;
        }
    }

    // Update Part
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    // Delete Part Validation
    // Checks to see whether part can be deleted or not (checks whether part is associated with any products)
    public static boolean deletePartCheck(Part selectedPart) {
        boolean okayToDeletePart = true;
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getAssociatedParts().contains(selectedPart)) {
                okayToDeletePart = false;
            }
        }
        return okayToDeletePart;
    }

// Delete Part
    public static void deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
    }

    // Add Product
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    // Lookup parts by Part ID or Part Name
    public static int lookupProduct(String productSearch) {
        boolean productFound = false;
        int productIndex = 0;
        if (validInt(productSearch)) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (Integer.parseInt(productSearch) == allProducts.get(i).getProductID()) {
                    productIndex = i;
                    productFound = true;
                }
            }
        } else {
            for (int i = 0; i < allParts.size(); i++) {
                productSearch = productSearch.toLowerCase();
                if (productSearch.equals(allParts.get(i).getPartName().toLowerCase())) {
                    productIndex = i;
                    productFound = true;
                }
            }
        }
        if (productFound == true) {
            return productIndex;
        } else {
            System.out.println("ERROR: Unable to locate any products based on the given search parameters.");
            return -1;
        }
    }

    // Update Product
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    // Delete Product Validation
    // Checks to see whether a product can be deleted or not (checks whether the product contains any parts)
    public static boolean deleteProductCheck(Product selectedProduct) {
        boolean okayToDeleteProduct = false;
        int selectedProductID = selectedProduct.getProductID();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getProductID() == selectedProductID) {
                if (allProducts.get(i).getAssociatedParts().isEmpty()) {
                    okayToDeleteProduct = true;
                }
            }
        }
        return okayToDeleteProduct;
    }

    // Delete Product
    public static void deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
    }
}
