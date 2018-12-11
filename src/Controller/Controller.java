package Controller;

import Model.Model;
import Model.Vacation;
import View.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Controller {

    public Model model;
    public View view;

    public void setModel(Model model){this.model=model;}

    public ArrayList<Vacation> search(String country){
        return model.search_vacation_by_country(country);
    }

    public boolean isConnect(){
        if(model.getCurrentLogInUser().equals(""))
            return false;
        return true;
    }

    public void Insert(String user, String pass, String birth, String first, String last, String city){
        model.Insert(user,pass,birth,first,last,city);
    }

    public boolean exist(String userr) {
        return model.exist(userr);
    }

    public String read(String userr) throws InterruptedException {
        return model.read(userr);
    }

    public boolean advertize(String airPortCompany,String depatrureDate,String arrivalDate,String lugg,String numTicket,String destcountry,String destCity, Boolean returnFlight, String ticketType,String price){
        if(model.getCurrentLogInUser().equals(""))
            return false;//user need to log in
        model.advertize_vacation(new Vacation(model.getCurrentLogInUser(),airPortCompany,depatrureDate,arrivalDate,lugg,numTicket,destcountry,destCity,returnFlight,ticketType,true,price));
        return true;
    }

    public boolean buy_vacation_with_credit(int vacationId,String credit_number,String cvd,String experation,String cardType) {
        if(model.getCurrentLogInUser().equals(""))
            return false;//user need to log in
        model.buy_vacation_with_credit(vacationId,model.getCurrentLogInUser(),credit_number,cvd,experation,cardType);
        return true;
    }

    public void delete(String userr) {
        model.delete(userr);
    }

    public boolean logIn (String userName,String password) {
        return model.logIn(userName,password);
    }

    public void update(String currentuser, String newuser ,String pass, String birth, String first, String last, String city) {
        model.update(currentuser,newuser,pass,birth,first,last,city);
    }

    public void LogOut(){
        model.setCurrentLogInUser("");
    }

    public void Approve(String vactionId,String buyer){
        model.approveRequest(Integer.valueOf(vactionId),buyer);
    }
    public void notApprove(String vactionId,String buyer){
        model.notApproveRequest(Integer.valueOf(vactionId),buyer);
    }
    public HashMap<Vacation,Boolean> InboxBuyer(){
        return model.checkMyBuyerInbox(model.getCurrentLogInUser());
    }

    public void buyerOK(){
        model.buyerOK(model.getCurrentLogInUser());
    }

    public HashMap<Vacation,String> inboxSaller(){
        return model.checkMySellarInbox(model.getCurrentLogInUser());
    }


    public ArrayList<Vacation> search_vacation_by_country(String country){
        return model.search_vacation_by_country(country);
    }

    public boolean buy_vacation_with_paypal(int vacationId,String user,String pass) {
        if(model.getCurrentLogInUser().equals(""))
            return false;//user need to log in
        model.buy_vacation_with_paypal(vacationId,model.getCurrentLogInUser(),user,pass);
        return true;
    }

}