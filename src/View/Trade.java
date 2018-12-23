package View;

public class Trade extends Acontrol {
    public String vacationId;
    public String user_saller;
    public javafx.scene.control.Button btn_trade;
    public javafx.scene.control.TextField dest_country;
    public javafx.scene.control.DatePicker d_arrival;
    public javafx.scene.control.DatePicker d_departure;

    public void Init(String vacationId,String user_name){
        this.vacationId=vacationId;
        this.user_saller=user_name;
    }

    public void trade(){
        if(d_departure.getValue().toString().equals("") ||d_arrival.getValue().toString().equals("") || dest_country.getText().equals(""))
            showAlert("you must enter all your vacation details that you want to trade ");
        if(conection_layer.trade(vacationId,user_saller,dest_country.getText(),d_arrival.getValue().toString(),d_departure.getValue().toString()))
            showAlert("the system got your requst, we are waiting for an answere from the saller, please check your inbox soon");
        else
            showAlert("you dont have this vacation that you advertised ");
    }



}
