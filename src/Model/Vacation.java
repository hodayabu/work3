package Model;

import java.io.*;
import java.util.Objects;

public class Vacation {
    private static int counter =counter();
    private String User_saller;
    private String AirPortCompany;
    private String dateDepar;
    private String dateArrive;
    private String lagguge;
    private String NumOftickets;
    private String destinationCountry;



    private String price;
    private String destinationCity;
    private Boolean ReturnFlight;
    private String ticketType;
    private Boolean Availble;
    private int Vacation_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacation vacation = (Vacation) o;
        if(this.Vacation_id==vacation.Vacation_id)
            return true;
        return false;
    }



    //constructor that does not get vacation_id- mean its create complete new vacation
    public Vacation(String user_saller, String airPortCompany, String dateDeparture,String arrive, String lagguge, String numOftickets, String destinationCountry,String destCity, Boolean returnFlight, String ticketType, Boolean availble,String price) {
        User_saller = user_saller;
        AirPortCompany = airPortCompany;
        this.dateDepar = dateDeparture;
        this.dateArrive=arrive;
        this.lagguge = lagguge;
        NumOftickets = numOftickets;
        this.destinationCountry = destinationCountry;
        this.destinationCity=destCity;
        ReturnFlight = returnFlight;
        this.ticketType = ticketType;
        Availble = availble;
        this.price=price;
        Vacation_id=Vacation.counter;
        Vacation.counter+=1;
        writecounter();
    }

    private void writecounter() {
        try {
            FileWriter f2 = new FileWriter(new File("resources\\counter.txt"),false);
            f2.write(String.valueOf(Vacation.counter));
            f2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //constructor that get vacation_id= mean this vacation has been created in the passed
    public Vacation(String user_saller, String airPortCompany, String dateDeparture,String arrive, String lagguge, String numOftickets, String destinationCountry,String destCity, Boolean returnFlight, String ticketType, Boolean availble,int vacation_id,String price) {
        User_saller = user_saller;
        AirPortCompany = airPortCompany;
        this.dateDepar = dateDeparture;
        this.dateArrive=arrive;
        this.lagguge = lagguge;
        NumOftickets = numOftickets;
        this.destinationCountry = destinationCountry;
        this.destinationCity=destCity;
        ReturnFlight = returnFlight;
        this.ticketType = ticketType;
        this.price = price;
        Availble = availble;
       this.Vacation_id=vacation_id;
    }


    //<editor-fold desc="getters and setters">
    public static int getCounter() {
        return counter;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public int getVacation_id(){
        return this.Vacation_id;
    }

    public String getUser_saller() {
        return User_saller;
    }

    public void setUser_saller(String user_saller) {
        User_saller = user_saller;
    }

    public String getAirPortCompany() {
        return AirPortCompany;
    }

    public void setAirPortCompany(String airPortCompany) {
        AirPortCompany = airPortCompany;
    }



    public String getLagguge() {
        return lagguge;
    }

    public void setLagguge(String lagguge) {
        this.lagguge = lagguge;
    }

    public String getNumOftickets() {
        return NumOftickets;
    }

    public void setNumOftickets(String numOftickets) {
        NumOftickets = numOftickets;
    }

    public String getDateDepar() {
        return dateDepar;
    }

    public void setDateDepar(String dateDepar) {
        this.dateDepar = dateDepar;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public Boolean getReturnFlight() {
        return ReturnFlight;
    }

    public void setReturnFlight(Boolean returnFlight) {
        ReturnFlight = returnFlight;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Boolean getAvailble() {
        return Availble;
    }

    public void setAvailble(Boolean availble) {
        Availble = availble;
    }
    //</editor-fold>

    private static int counter(){

        File file = new File("resources\\counter.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int ans=  Integer.valueOf(br.readLine());
            br.close();
            return ans;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;

    }
}
