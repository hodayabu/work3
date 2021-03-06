package View;

import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreen extends Acontrol {

    public javafx.scene.control.Button btn_LogIn;
    public javafx.scene.control.Button btn_Create;
    public javafx.scene.control.Button btn_sellaerInbox;
    public javafx.scene.control.Button btn_BuyerInbox;
    public javafx.scene.control.Button btn_AdvertiseVacation;
    public javafx.scene.control.Button btn_SearchVacation;
    public javafx.scene.control.TextField f_country;
    //public ListView inboxSellar;

    public MainScreen(){}

    public void initializ(){
        btn_BuyerInbox.setDisable(true);
        btn_sellaerInbox.setDisable(true);
    }

    public void Help(){
    showAlert("New users can create account with \"Create User\" button.\nAfter Log In you can search for vacation in the website\nIf you have fligth tickets for sell, \nUse the publish vacation button\nWe wish you have nice experience here in VACATION4U.\nHope to see you again soon");
    }


    public void search(){
        String country=f_country.getText();
        if(country.equals(""))
            showAlert("Please choose destination country");
        else{
            ArrayList<Vacation> res=conection_layer.search(country);

            TableView<VacationToShow> table = new TableView<VacationToShow>();
            final ObservableList<VacationToShow> data = FXCollections.observableArrayList();
           for(int i=0;i<res.size();i++){
               Vacation v=res.get(i);
               data.add(new VacationToShow(v.getUser_saller(),v.getAirPortCompany(),v.getDateDepar(),v.getDateArrive(),v.getLagguge(),v.getNumOftickets(),v.getDestinationCountry(),v.getDestinationCity(),v.getPrice(),v.getReturnFlight(),v.getTicketType(),v.getVacation_id()));
           }
            Stage stage=new Stage();
            Scene scene = new Scene(new Group());
            scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
            stage.setTitle("Table View Sample");
            stage.setWidth(1470);
            stage.setHeight(600);

            final Label label = new Label("Search Vacation");
            label.setFont(new Font("Arial", 20));

            table.setEditable(true);
            table.setMinWidth(1470);

            TableColumn e1 = new TableColumn("Seller Name");
            e1.setMinWidth(90);
            e1.setCellValueFactory(
                    new PropertyValueFactory<VacationToShow, String>("user_saller"));

///////
            TableColumn e2 = new TableColumn("Flight Company");
            e2.setMinWidth(90);
            e2.setCellValueFactory(
                    new PropertyValueFactory<VacationToShow, String>("AirportCompany"));

            TableColumn e3 = new TableColumn("Departure Date");
            e3.setMinWidth(90);
            e3.setCellValueFactory(
                    new PropertyValueFactory<VacationToShow, String>("dateDepar"));

            TableColumn e4 = new TableColumn("Arrival Date");
            e4.setMinWidth(90);
            e4.setCellValueFactory(
                    new PropertyValueFactory<VacationToShow, String>("dateArive"));

            TableColumn e5 = new TableColumn("Luggage");
            e5.setMinWidth(90);
            e5.setCellValueFactory(
                    new PropertyValueFactory<VacationToShow, String>("laggege"));

            TableColumn e6 = new TableColumn("Num Of Tickets");
            e6.setMinWidth(90);
            e6.setCellValueFactory(
                    new PropertyValueFactory<VacationToShow, String>("num_of_tickets"));

            TableColumn e7 = new TableColumn("Destination Country");
            e7.setMinWidth(90);
            e7.setCellValueFactory(
                    new PropertyValueFactory<VacationToShow, String>("destcountry"));

            TableColumn e8 = new TableColumn("Destination City");
            e8.setMinWidth(90);
            e8.setCellValueFactory(
                    new PropertyValueFactory<VacationToShow, String>("destCity"));

            TableColumn e9 = new TableColumn("Price");
            e9.setMinWidth(90);
            e9.setCellValueFactory(
                    new PropertyValueFactory<VacationToShow, String>("price"));

            TableColumn e10 = new TableColumn("Return Flight");
            e10.setMinWidth(90);
            e10.setCellValueFactory(
                    new PropertyValueFactory<VacationToShow, String>("returnFlight"));

            TableColumn e11 = new TableColumn("Ticket Type");
            e11.setMinWidth(90);
            e11.setCellValueFactory(
                    new PropertyValueFactory<VacationToShow, String>("ticketType"));






            TableColumn actionCol1 = new TableColumn("Parches");
            actionCol1.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

            TableColumn actionCol2 = new TableColumn("Trade");
            actionCol2.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));


            Callback<TableColumn<VacationToShow, String>, TableCell<VacationToShow, String>> cellFactory
                    = //
                    new Callback<TableColumn<VacationToShow, String>, TableCell<VacationToShow, String>>() {
                        @Override
                        public TableCell call(final TableColumn<VacationToShow, String> param) {
                            final TableCell<VacationToShow, String> cell = new TableCell<VacationToShow, String>() {

                                final Button btn = new Button("Parches");

                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btn.setOnAction(event -> {
                                            VacationToShow vts = getTableView().getItems().get(getIndex());
                                            payment(vts.getVacationId());
                                        });
                                        setGraphic(btn);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };




            Callback<TableColumn<VacationToShow, String>, TableCell<VacationToShow, String>> cellFactory1
                    = //
                    new Callback<TableColumn<VacationToShow, String>, TableCell<VacationToShow, String>>() {
                        @Override
                        public TableCell call(final TableColumn<VacationToShow, String> param) {
                            final TableCell<VacationToShow, String> cell = new TableCell<VacationToShow, String>() {

                                final Button btn = new Button("Trade");

                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btn.setOnAction(event -> {
                                            VacationToShow vts = getTableView().getItems().get(getIndex());
                                            openTrade(vts.getVacationId(),vts.getUser_saller());
                                        });
                                        setGraphic(btn);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };



                       actionCol1.setCellFactory(cellFactory);
                       actionCol2.setCellFactory(cellFactory1);







            table.setItems(data);
            table.getColumns().addAll(e1, e2, e3,e4,e5,e6,e7,e8,e9,e10,e11/**,e12**/,actionCol1,actionCol2);

            final VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(label, table);

            ((Group) scene.getRoot()).getChildren().addAll(vbox);

            stage.setScene(scene);
            stage.show();


        }
    }

    private void openTrade(String vacationId,String user_saller) {
        if(!(conection_layer.isConnect()))
            showAlert("you must log-in first before you can exchange vacations");
        else {
            Stage stage=new Stage();
            stage.setTitle("trade");
            FXMLLoader fxmlLoader=new FXMLLoader();
            try {
                Parent root=fxmlLoader.load(getClass().getResource("trade.fxml").openStream());
                Scene scene=new Scene(root,600,600);
                scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                Trade trade=fxmlLoader.getController();
                trade.Init(vacationId,user_saller,stage);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void payment(String vacationId) {
        if(!(conection_layer.isConnect()))
            showAlert("only register users can parches");
        else {
                String phone=conection_layer.buy_vacation(Integer.valueOf(vacationId));
                showAlert("Dear user,\nWe have got your purchase request.\nPlease check your inboxBuyer for respond");
                }

    }


    public void CheckSallerInbox(){
        Stage stage=new Stage();
        stage.setTitle("Seller Inbox");
        FXMLLoader fxmlLoader=new FXMLLoader();
        try {
            Parent root=fxmlLoader.load(getClass().getResource("CheckSallerInbox.fxml").openStream());
            Scene scene=new Scene(root,400,320);
            scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            //CheckBuyerInbox checkBuyerInbox=fxmlLoader.getController();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CheckBuyerInbox(){
        Stage stage=new Stage();
        stage.setTitle("Buyer Inbox");
        FXMLLoader fxmlLoader=new FXMLLoader();
        try {
            Parent root=fxmlLoader.load(getClass().getResource("CheckBuyerInbox.fxml").openStream());
            Scene scene=new Scene(root,400,320);
            scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            //CheckBuyerInbox checkBuyerInbox=fxmlLoader.getController();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LogInOrOut(){

        if(btn_LogIn.getText().equals("Log In")){
            Stage stage=new Stage();
            stage.setTitle("LogIn");
            FXMLLoader fxmlLoader=new FXMLLoader();
            try {
                Parent root=fxmlLoader.load(getClass().getResource("LogIn.fxml").openStream());
                Scene scene=new Scene(root,300,300);
                scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                logIn logIn=fxmlLoader.getController();
                logIn.setStage(stage);
                logIn.setMainScreen(this);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(btn_LogIn.getText().equals("Log Out")){
            conection_layer.LogOut();
            btn_LogIn.setText("Log In");
        }
    }

    public void create()
    {
        try{
            Stage stage=new Stage();
            stage.setTitle("Create new user");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("create.fxml").openStream());
            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();


        } catch (Exception e) {

        }
    }


    public void advertise(){
        Stage stage=new Stage();
        stage.setTitle("Advertise Vacation");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            Parent root = fxmlLoader.load(getClass().getResource("adverties.fxml").openStream());
        Scene scene = new Scene(root, 550, 550);
            scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
            stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            adverties add=fxmlLoader.getController();
            add.Initiate();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
