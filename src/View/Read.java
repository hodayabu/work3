package View;

public class Read extends Acontrol {

    public javafx.scene.control.Button btn_search;
    public javafx.scene.control.TextField userName;

    public void read() throws InterruptedException {
        String user=conection_layer.read(userName.getText());
        System.out.println(user);
        if(user!=null) {
            showAlert(user);
        }

         else
            showAlert("this user name doesn't exist");

    }

}
