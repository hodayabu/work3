package View;

import Controller.Controller;
import Model.Model;
import javafx.scene.control.Alert;

public  abstract class Acontrol {


   protected static Controller conection_layer;

    public void setController(Controller conection_layer) {
        this.conection_layer = conection_layer;
    }

    public void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.show();

    }
}
