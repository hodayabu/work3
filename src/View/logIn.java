package View;

import javafx.stage.Stage;

public class logIn extends Acontrol {
    public javafx.scene.control.Button btn_LogIn;
    public javafx.scene.control.TextField f_UserName;
    public javafx.scene.control.TextField f_Password;
    private Stage stage;



    private MainScreen mainScreen;
//

    public void setMainScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    public void LogIn(){
        String userName=f_UserName.getText();
        String Password=f_Password.getText();
        if(userName.equals("")||Password.equals(""))
            showAlert("Please fill all the fields");
        else if(conection_layer.logIn(userName,Password)){
            showAlert("You have sign in successfully");
            mainScreen.btn_LogIn.setText("Log Out");
            mainScreen.btn_sellaerInbox.setDisable(false);
            mainScreen.btn_BuyerInbox.setDisable(false);
            stage.close();
        }
        else showAlert("User name or password are incorrect");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
