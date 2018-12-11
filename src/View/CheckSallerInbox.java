package View;

import Model.Vacation;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;

public class CheckSallerInbox extends Acontrol {

    public javafx.scene.control.Button btn_inbox;

    public void checkInbox() {
        TableView<Inbox> table = new TableView<Inbox>();
        final ObservableList<Inbox> data =FXCollections.observableArrayList();
        HashMap<Vacation,String> ans=conection_layer.inboxSaller();
        for(Map.Entry<Vacation,String> entry : ans.entrySet())
            data.add(new Inbox(entry.getKey().getDestinationCity(),entry.getValue(),entry.getKey().getDateDepar(),entry.getKey().getDateArrive(),entry.getKey().getVacation_id()));


        Stage stage=new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(720);
        stage.setHeight(500);

        final Label label = new Label("Inbox");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Destination");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Inbox, String>("dest"));

        TableColumn lastNameCol = new TableColumn("Buyer Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Inbox, String>("buyer"));

        TableColumn emailCol = new TableColumn("Departure Date");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Inbox, String>("depar"));

        TableColumn ee = new TableColumn("Arrival Date");
        ee.setMinWidth(100);
        ee.setCellValueFactory(
                new PropertyValueFactory<Inbox, String>("arrive"));

        TableColumn vid = new TableColumn("Vacation_Id");
        vid.setMinWidth(100);
        vid.setCellValueFactory(
                new PropertyValueFactory<Inbox, String>("Vacation_Id"));


        TableColumn actionCol = new TableColumn("Approve");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        TableColumn actionCol1 = new TableColumn("Not Approve");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Inbox, String>, TableCell<Inbox, String>> cellFactory
                = //
                new Callback<TableColumn<Inbox, String>, TableCell<Inbox, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Inbox, String> param) {
                        final TableCell<Inbox, String> cell = new TableCell<Inbox, String>() {

                            final Button btn = new Button("Approve");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Inbox inbox = getTableView().getItems().get(getIndex());
                                        conection_layer.Approve(inbox.Vacation_Id.get(),inbox.buyer.get());
                                        showAlert("Vacation approved to sell");
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };




        Callback<TableColumn<Inbox, String>, TableCell<Inbox, String>> cellFactory1
                = //
                new Callback<TableColumn<Inbox, String>, TableCell<Inbox, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Inbox, String> param) {
                        final TableCell<Inbox, String> cell = new TableCell<Inbox, String>() {

                            final Button btn = new Button("Not Approve");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Inbox inbox = getTableView().getItems().get(getIndex());
                                        conection_layer.notApprove(inbox.Vacation_Id.get(),inbox.buyer.get());
                                        showAlert("Vacation disapproved to sell");
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);
        actionCol1.setCellFactory(cellFactory1);







        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol,ee,vid,actionCol,actionCol1);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();


    }
}