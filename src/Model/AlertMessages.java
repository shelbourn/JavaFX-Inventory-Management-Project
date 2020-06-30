/**
 * Contains all types of Alert message windows used in application
 * 
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */

package Model;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class AlertMessages {
    public void cancelAlert (ActionEvent event) throws IOException {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit to Main Screen");
            alert.setHeaderText("Would you like to cancel this operation?");
            alert.setContentText("Click OK to cancel operation and return to the main screen. \nClick CANCEL to continue operation and return to the current screen.");
            alert.showAndWait();
            
            if (alert.getResult() == ButtonType.OK) {
                Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(root);
                Stage mainScreenWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainScreenWindow.setTitle("ABC Company: Inventory Management System");
                mainScreenWindow.setScene(scene);
                mainScreenWindow.show();
            }
            
            else {
                alert.close();
            }
        }
            catch (IOException e) {}
    }
    
}
