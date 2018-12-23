package View;

import javafx.beans.property.SimpleStringProperty;

import java.awt.*;

public  class InboxTrade {
    public final SimpleStringProperty dest;
    public final SimpleStringProperty buyer;
    public final SimpleStringProperty depar;
    public final SimpleStringProperty arrive;
    public Button approve;
    public Button notApprove;
    public final SimpleStringProperty Vacation_Id;

    public final SimpleStringProperty destTrade;
    public final SimpleStringProperty deparTrade;
    public final SimpleStringProperty arriveTrade;

    public final SimpleStringProperty Vacation_IdTrade;



    public Button getNotApprove() {
        return notApprove;
    }

    public void setNotApprove(Button notApprove) {
        this.notApprove = notApprove;
    }

    public String getVacation_Id() {
        return Vacation_Id.get();
    }

    public SimpleStringProperty vacation_IdProperty() {
        return Vacation_Id;
    }

    public void setVacation_Id(String vacation_Id) {
        this.Vacation_Id.set(vacation_Id);
    }

    public String getVacation_IdTrade() {
        return Vacation_IdTrade.get();
    }

    public SimpleStringProperty vacation_IdTradeProperty() {
        return Vacation_IdTrade;
    }

    public void setVacation_IdTrade(String vacation_Id) {
        this.Vacation_IdTrade.set(vacation_Id);
    }

    public Button getApprove() {
        return approve;
    }

    public void setApprove(Button approve) {
        this.approve = approve;
    }


    public String getDest() {
        return dest.get();
    }


    public void setDest(String dest) {
        this.dest.set(dest);
    }

    public String getDestTrade() {
        return destTrade.get();
    }


    public void setDestTrade(String dest) {
        this.destTrade.set(dest);
    }

    public String getBuyer() {
        return buyer.get();
    }

    public void setBuyer(String buyer) {
        this.buyer.set(buyer);
    }

    public String getDepar() {
        return depar.get();
    }


    public void setDepar(String depar) {
        this.depar.set(depar);
    }

    public String getArrive() {
        return arrive.get();
    }


    public void setArrive(String arrive) {
        this.arrive.set(arrive);
    }

    public String getDeparTrade() {
        return deparTrade.get();
    }


    public void setDeparTrade(String depar) {
        this.deparTrade.set(depar);
    }

    public String getArriveTrade() {
        return arriveTrade.get();
    }


    public void setArriveTrade(String arrive) {
        this.arriveTrade.set(arrive);
    }



    public InboxTrade(String dest,String buyer,String depar,String arrive,int Vacation_id,String destTrade,String deparTrade,String ariveTrade,int vacIdTrade){
        this.dest=new SimpleStringProperty(dest);
        this.buyer=new SimpleStringProperty(buyer);
        this.depar=new SimpleStringProperty(depar);
        this.arrive=new SimpleStringProperty(arrive);
        this.approve=new Button("Approve");
        this.notApprove=new Button("Disapprove");
        this.Vacation_Id=new SimpleStringProperty(String.valueOf(Vacation_id));
        this.Vacation_IdTrade=new SimpleStringProperty(String.valueOf(vacIdTrade));
        this.destTrade=new SimpleStringProperty(String.valueOf(destTrade));
        this.deparTrade=new SimpleStringProperty(String.valueOf(deparTrade));
        this.arriveTrade=new SimpleStringProperty(String.valueOf(ariveTrade));
    }



}