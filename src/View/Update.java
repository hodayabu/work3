package View;

public class Update extends Acontrol {
    public javafx.scene.control.Button btn_update;
    public javafx.scene.control.TextField curren_name;
    public javafx.scene.control.TextField new_name;
    public javafx.scene.control.TextField f_name;
    public javafx.scene.control.TextField l_name;
    public javafx.scene.control.TextField password;
    public javafx.scene.control.TextField city;
    public javafx.scene.control.TextField BDay;

    public void update() throws InterruptedException {
        if(conection_layer.exist(new_name.getText()))
            showAlert("your new user name is already exist,please choose another one ");

        else if(conection_layer.exist(curren_name.getText())) {
            conection_layer.update(curren_name.getText(),new_name.getText(), password.getText(), BDay.getText(), f_name.getText(), l_name.getText(), city.getText());
            showAlert("update sucssesfully");

        }
        else
            showAlert("this user name isn't exist");



    }
}
