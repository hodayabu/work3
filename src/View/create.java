package View;

import static java.lang.Thread.sleep;

public class create extends Acontrol {
    public javafx.scene.control.Button btn_ImDone;
    public javafx.scene.control.TextField f_name;
    public javafx.scene.control.TextField l_name;
    public javafx.scene.control.TextField u_name;
    public javafx.scene.control.TextField password;
    public javafx.scene.control.TextField city;
    public javafx.scene.control.DatePicker BDay;


    public void Insert() throws InterruptedException {
        if(u_name.getText().equals("") || f_name.getText().equals("") || l_name.getText().equals("") || password.getText().equals("") ||city.getText().equals("") || BDay.toString().equals("") )
             showAlert("you must fill all the fields");

        else if ((conection_layer.exist(u_name.getText())))
            showAlert("this user name is already exist, please choose another one");

         else {
            conection_layer.Insert(u_name.getText(), password.getText(), BDay.toString(), f_name.getText(), l_name.getText(), city.getText());
            showAlert("you have sign in sucssesfully");
        }



    }





}