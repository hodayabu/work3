package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View extends Acontrol {
    public javafx.scene.control.Button btn_create;



    public void create()
    {
        try{
            Stage stage=new Stage();
            stage.setTitle("Create new user");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("create.fxml").openStream());
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();


        } catch (Exception e) {

        }
        }


    public void help(){
        showAlert("\t Vacation 4 U is the App for you! \n \t Here you can sell and buy flight ticket with no effort! \n \t Start now and craete your user!");


    }


    public void read()
    {
        try{
            Stage stage=new Stage();
            stage.setTitle("search a user");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("read.fxml").openStream());
            Scene scene = new Scene(root, 550, 450);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();


        } catch (Exception e) {

        }
    }


    public void delete()
    {
        try{
            Stage stage=new Stage();
            stage.setTitle("delete a user");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("delete.fxml").openStream());
            Scene scene = new Scene(root, 550, 450);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();


        } catch (Exception e) {

        }
    }

    public void update()
    {
        try{
            Stage stage=new Stage();
            stage.setTitle("update a user");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("update.fxml").openStream());
            Scene scene = new Scene(root, 750, 550);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();


        } catch (Exception e) {

        }
    }


}

