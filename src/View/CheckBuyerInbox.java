package View;

import Model.Vacation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CheckBuyerInbox extends Acontrol {
    public ListView inboxBuyer;
    public javafx.scene.control.Button btn_showAllMsg;
    public javafx.scene.control.Button btn_removeAllMyMsg;

    public void showAllMsg(){
        Stage stage=new Stage();
        inboxBuyer = new ListView();
        HashMap<Vacation,Boolean> allMsg=conection_layer.InboxBuyer();

        for (Map.Entry<Vacation,Boolean> entry : allMsg.entrySet()) {
            inboxBuyer.getItems().add(buiiedMsgBuyer(entry.getKey().getDestinationCity(),entry.getKey().getUser_saller(),entry.getKey().getDateDepar(),entry.getKey().getDateArrive(),entry.getValue()));
        }
        inboxBuyer.setPrefWidth(900);
        Scene scene = new Scene(new Group());
        stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
        final VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10, 0, 0, 10));
        vBox.getChildren().addAll(inboxBuyer);
        vBox.setAlignment(Pos.CENTER);
        Group group = ((Group) scene.getRoot());
        group.getChildren().addAll(vBox);
        stage.setScene(scene);
        stage.show();
    }

    private Object buiiedMsgBuyer(String cityDest,String sellar, String dateDep,String dateArrive,boolean approve) {
        if(approve)
            return "Congratulations! The vacation to "+cityDest+" from "+dateDep+" to "+dateArrive+" is now approved by the seller "+sellar+". Have a nice trip!";
        return "We are sorry! The vacation to "+cityDest+" from "+dateDep+" to "+dateArrive+" is not approved to purchase by the seller "+sellar+". Keep on looking!";

    }


    public void removeAll(){
        conection_layer.buyerOK();
        showAlert("All of your massages have been removed successfully");
    }


}
