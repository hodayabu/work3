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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckSallerInbox extends Acontrol {

    public javafx.scene.control.Button btn_inbox;
    public javafx.scene.control.Button btn_inboxTrade;

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


    public void checkTradeInbox() {
        TableView<InboxTrade> table = new TableView<InboxTrade>();
        final ObservableList<InboxTrade> data =FXCollections.observableArrayList();
        HashMap<ArrayList<Vacation>,String> ans=conection_layer.inboxTradeSaller();
        for(Map.Entry<ArrayList<Vacation>,String> entry : ans.entrySet()) {
            data.add(new InboxTrade(entry.getKey().get(0).getDestinationCity(), entry.getValue(), entry.getKey().get(0).getDateDepar(), entry.getKey().get(0).getDateArrive(), entry.getKey().get(0).getVacation_id(),entry.getKey().get(1).getDestinationCity(),entry.getKey().get(1).getDateDepar(),entry.getKey().get(1).getDateArrive(),entry.getKey().get(1).getVacation_id()));
        }

        Stage stage=new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View");
        stage.setWidth(1420);
        stage.setHeight(500);

        final Label label = new Label("InboxTrade");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        table.setMinWidth(1420);

        TableColumn yourVacation = new TableColumn("Yours Vacation Details");
        TableColumn offerVacation = new TableColumn("The Offered Vacation Details");


        TableColumn firstNameCol = new TableColumn("Destination");
        firstNameCol.setMinWidth(120);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<InboxTrade, String>("dest"));

        TableColumn lastNameCol = new TableColumn("Buyer Name");
        lastNameCol.setMinWidth(120);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<InboxTrade, String>("buyer"));

        TableColumn emailCol = new TableColumn("Departure Date");
        emailCol.setMinWidth(120);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<InboxTrade, String>("depar"));

        TableColumn ee = new TableColumn("Arrival Date");
        ee.setMinWidth(120);
        ee.setCellValueFactory(
                new PropertyValueFactory<InboxTrade, String>("arrive"));

        TableColumn vid = new TableColumn("Vacation_Id");
        vid.setMinWidth(120);
        vid.setCellValueFactory(
                new PropertyValueFactory<InboxTrade, String>("Vacation_Id"));

        TableColumn destT = new TableColumn("Destination");
        destT.setMinWidth(120);
        destT.setCellValueFactory(
                new PropertyValueFactory<InboxTrade, String>("destTrade"));

        TableColumn depT = new TableColumn("Departure Date");
        depT.setMinWidth(120);
        depT.setCellValueFactory(
                new PropertyValueFactory<InboxTrade, String>("deparTrde"));

        TableColumn arivT = new TableColumn("Arrival Date");
        arivT.setMinWidth(120);
        arivT.setCellValueFactory(
                new PropertyValueFactory<InboxTrade, String>("arriveTrde"));

        TableColumn vidT = new TableColumn("Vacation_Id");
        vidT.setMinWidth(120);
        vidT.setCellValueFactory(
                new PropertyValueFactory<InboxTrade, String>("Vacation_IdTrade"));


        TableColumn actionCol = new TableColumn("Approve");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        TableColumn actionCol1 = new TableColumn("Not Approve");
        actionCol1.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        actionCol1.setMinWidth(120);

        TableColumn actionCol2 = new TableColumn("More Details");
        actionCol2.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        actionCol2.setMinWidth(120);


        Callback<TableColumn<InboxTrade, String>, TableCell<InboxTrade, String>> cellFactory2
                = //
                new Callback<TableColumn<InboxTrade, String>, TableCell<InboxTrade, String>>() {
                    @Override
                    public TableCell call(final TableColumn<InboxTrade, String> param) {
                        final TableCell<InboxTrade, String> cell = new TableCell<InboxTrade, String>() {

                            final Button btn = new Button("See More Details");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        InboxTrade inbox = getTableView().getItems().get(getIndex());

                                        showAlert(conection_layer.tradeDetails(inbox.getVacation_IdTrade()));
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };


        Callback<TableColumn<InboxTrade, String>, TableCell<InboxTrade, String>> cellFactory
                = //
                new Callback<TableColumn<InboxTrade, String>, TableCell<InboxTrade, String>>() {
                    @Override
                    public TableCell call(final TableColumn<InboxTrade, String> param) {
                        final TableCell<InboxTrade, String> cell = new TableCell<InboxTrade, String>() {

                            final Button btn = new Button("Approve");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        InboxTrade inbox = getTableView().getItems().get(getIndex());
                                        conection_layer.ApproveTrade(inbox.Vacation_Id.get(),inbox.getVacation_IdTrade(),inbox.buyer.get());
                                        showAlert("Vacation approved to trade");
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };




        Callback<TableColumn<InboxTrade, String>, TableCell<InboxTrade, String>> cellFactory1
                = //
                new Callback<TableColumn<InboxTrade, String>, TableCell<InboxTrade, String>>() {
                    @Override
                    public TableCell call(final TableColumn<InboxTrade, String> param) {
                        final TableCell<InboxTrade, String> cell = new TableCell<InboxTrade, String>() {

                            final Button btn = new Button("Not Approve");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        InboxTrade inbox = getTableView().getItems().get(getIndex());
                                        conection_layer.notApproveTrade(inbox.Vacation_Id.get(),inbox.getVacation_IdTrade(),inbox.buyer.get());
                                        showAlert("Vacation disapproved to trade");
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
        actionCol2.setCellFactory(cellFactory2);

        table.setItems(data);
        table.getColumns().addAll(yourVacation,offerVacation,actionCol,actionCol1);
        yourVacation.getColumns().addAll(firstNameCol,emailCol,ee,vid,actionCol2);
        offerVacation.getColumns().addAll(lastNameCol,destT,depT,arivT,vidT);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();


    }

}





