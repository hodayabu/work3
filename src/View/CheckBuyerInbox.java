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

import java.util.ArrayList;
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
        HashMap<ArrayList<Vacation>,Boolean> allTradeMsg=conection_layer.inboxTradeBuyer();

        for (Map.Entry<Vacation,Boolean> entry : allMsg.entrySet()) {
            inboxBuyer.getItems().add(buiiedMsgBuyer(entry.getKey().getDestinationCity(),entry.getKey().getUser_saller(),entry.getKey().getDateDepar(),entry.getKey().getDateArrive(),entry.getValue()));
        }
        for (Map.Entry<ArrayList<Vacation>,Boolean> entry : allTradeMsg.entrySet()) {
            inboxBuyer.getItems().add(buiiedTradeMsgBuyer(entry.getKey().get(0).getDestinationCity(),entry.getKey().get(0).getUser_saller(),entry.getKey().get(0).getDateDepar(),entry.getKey().get(0).getDateArrive(),entry.getKey().get(1).getDestinationCity(),entry.getKey().get(1).getUser_saller(),entry.getKey().get(1).getDateDepar(),entry.getKey().get(1).getDateArrive(),entry.getValue()));
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

    private Object buiiedTradeMsgBuyer(String destinationCity, String user_saller, String dateDepar, String dateArrive, String destinationCity1, String user_saller1, String dateDepar1, String dateArrive1, Boolean approve) {
        if(approve)
            return "Congratulations! Your request for trade with "+user_saller+" your vacation to "+destinationCity+" from "+dateDepar+" to "+dateArrive+" approved!!\nThe vacation you asked for is from "+destinationCity1+" at "+dateDepar1+" until "+dateArrive1+". Have a nice trip!";
        return "We are sorry! Your request for trade with "+user_saller+" your vacation to "+destinationCity+" from "+dateDepar+" to "+dateArrive+" was not approved!!\nThe vacation you asked for is from "+destinationCity1+" at "+dateDepar1+" until "+dateArrive1+". Keep on looking!";


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
