package View;

import javafx.beans.property.SimpleStringProperty;

public class VacationToShow {
    public final SimpleStringProperty user_saller;
    public final SimpleStringProperty AirportCompany;
    public final SimpleStringProperty dateDepar;
    public final SimpleStringProperty dateArive;
    public final SimpleStringProperty laggege;
    public final SimpleStringProperty num_of_tickets;
    public final SimpleStringProperty destcountry;
    public final SimpleStringProperty destCity;
    public final SimpleStringProperty price;
    public final SimpleStringProperty returnFlight;
    public final SimpleStringProperty ticketType;
    public final SimpleStringProperty vacationId;


    public VacationToShow(String user_saller, String airportCompany, String dateDepar, String dateArive, String laggege, String naum_of_tickets, String destcountry, String destCity, String price, Boolean returnFlight, String ticketType, int vacationId) {

        this.user_saller = new SimpleStringProperty(user_saller);
        AirportCompany = new SimpleStringProperty(airportCompany);
        this.dateDepar =new SimpleStringProperty(dateDepar);
        this.dateArive = new SimpleStringProperty(dateArive);
        this.laggege =new SimpleStringProperty(laggege) ;
        this.num_of_tickets = new SimpleStringProperty(naum_of_tickets);
        this.destcountry = new SimpleStringProperty(destcountry);
        this.destCity = new SimpleStringProperty(destCity);
        this.price = new SimpleStringProperty(price);
        if(returnFlight)
        this.returnFlight = new SimpleStringProperty("include");
        else
            this.returnFlight = new SimpleStringProperty("not include");
        this.ticketType =new SimpleStringProperty(ticketType) ;
        this.vacationId=new SimpleStringProperty(String.valueOf(vacationId));

    }

    //<editor-fold desc="getters and setters">
    public String getUser_saller() {
        return user_saller.get();
    }


    public void setUser_saller(String user_saller) {
        this.user_saller.set(user_saller);
    }

    public String getAirportCompany() {
        return AirportCompany.get();
    }


    public void setAirportCompany(String airportCompany) {
        this.AirportCompany.set(airportCompany);
    }

    public String getDateDepar() {
        return dateDepar.get();
    }


    public void setDateDepar(String dateDepar) {
        this.dateDepar.set(dateDepar);
    }

    public String getDateArive() {
        return dateArive.get();
    }


    public void setDateArive(String dateArive) {
        this.dateArive.set(dateArive);
    }

    public String getLaggege() {
        return laggege.get();
    }



    public void setLaggege(String laggege) {
        this.laggege.set(laggege);
    }

    public String getNum_of_tickets() {
        return num_of_tickets.get();
    }



    public void setNum_of_tickets(String num_of_tickets) {
        this.num_of_tickets.set(num_of_tickets);
    }

    public String getDestcountry() {
        return destcountry.get();
    }


    public void setDestcountry(String destcountry) {
        this.destcountry.set(destcountry);
    }

    public String getDestCity() {
        return destCity.get();
    }



    public void setDestCity(String destCity) {
        this.destCity.set(destCity);
    }

    public String getPrice() {
        return price.get();
    }



    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getReturnFlight() {
        return returnFlight.get();
    }



    public void setReturnFlight(String returnFlight) {
        this.returnFlight.set(returnFlight);
    }

    public String getTicketType() {
        return ticketType.get();
    }


    public void setTicketType(String ticketType) {
        this.ticketType.set(ticketType);
    }

    public String getVacationId() {
        return vacationId.get();
    }



    public void setVacationId(String vacationId) {
        this.vacationId.set(vacationId);
    }
    //</editor-fold>
}
