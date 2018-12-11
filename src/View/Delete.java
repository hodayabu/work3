package View;

public class Delete extends Acontrol {

    public javafx.scene.control.Button btn_delete;
    public javafx.scene.control.TextField userName;

    public void delete() throws InterruptedException {
        if(conection_layer.exist(userName.getText())) {
            conection_layer.delete(userName.getText());
            showAlert("the user deleted succesfully");
        }

        else
            showAlert("this user name doesn't exist");
    }

}
