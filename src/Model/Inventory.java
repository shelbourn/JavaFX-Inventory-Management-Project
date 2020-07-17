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
    private static ObservableList<Part> partSearch = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Product> productSearch = FXCollections.observableArrayList();
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

    // Integer validation for inputs
    public static boolean validInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * * Methods for Inventory Class -- Parts
     */
    // Add Part
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    // Lookup parts by Part ID or Keyword
    public static ObservableList<Part> lookupPart(String partSearchString) {
        boolean isFound = false;
        if (validInt(partSearchString)) {
            for (int i = 0; i < allParts.size(); i++) {
                if (Integer.parseInt(partSearchString) == allParts.get(i).getId()) {
                    partSearch.add(allParts.get(i));
                    isFound = true;
                }
            }
        } else {
            for (int i = 0; i < allParts.size(); i++) {
                partSearchString = partSearchString.toLowerCase();
                if ((allParts.get(i).getName().toLowerCase()).contains(partSearchString)) {
                    partSearch.add(allParts.get(i));
                    isFound = true;
                }
            }
        }
        if (isFound == true) {
            return partSearch;
        } else {
            return null;
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
            if (allProducts.get(i).getAllAssociatedParts().contains(selectedPart)) {
                okayToDeletePart = false;
            }
        }
        return okayToDeletePart;
    }

    // Delete Part
    public static void deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
    }

    /**
     * * Methods for Inventory Class -- Products
     */
    // Add Product
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    // Lookup Products by Product ID or Keyword
    public static ObservableList<Product> lookupProduct(String productSearchString) {
        boolean isFound = false;
        if (validInt(productSearchString)) {
            for (int i = 0; i < allParts.size(); i++) {
                if (Integer.parseInt(productSearchString) == allParts.get(i).getId()) {
                    productSearch.add(allProducts.get(i));
                    isFound = true;
                }
            }
        } else {
            for (int i = 0; i < allParts.size(); i++) {
                productSearchString = productSearchString.toLowerCase();
                if ((allProducts.get(i).getName().toLowerCase()).contains(productSearchString)) {
                    productSearch.add(allProducts.get(i));
                    isFound = true;
                }
            }
        }
        if (isFound == true) {
            return productSearch;
        } else {
            return null;
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
        int selectedProductID = selectedProduct.getId();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == selectedProductID) {
                if (allProducts.get(i).getAllAssociatedParts().isEmpty()) {
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
