package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

public class purches extends Acontrol {

    public String vacationId;
    public javafx.scene.control.Button btn_done;
    public javafx.scene.control.TextField cvd;
    public javafx.scene.control.TextField cardnumber;
    public javafx.scene.control.TextField experation;
    public javafx.scene.control.ChoiceBox cardType;
    public javafx.scene.control.TextField username_paypal;
    public javafx.scene.control.TextField password_paypay;
    public ObservableList<String> list= FXCollections.observableArrayList();



    public void Init(String vacationId){
        this.vacationId=vacationId;
        list.add("masterCard");
        list.add("visa");
        list.add("american express");
        cardType.setItems(list);
    }

    public void done(){
        String cvd=this.cvd.getText();
        String experation=this.experation.getText();
        String cardnumber=this.cardnumber.getText();
        // String cardType=this.cardType.getValue().toString();
        String user_paypal=this.username_paypal.getText();
        String password_paypal=this.password_paypay.getText();



        if(cardIsFull(cvd,experation,cardnumber,cardType) && peypalIsEmpty(user_paypal,password_paypal)) {
            conection_layer.buy_vacation_with_credit(Integer.valueOf(vacationId), cardnumber, cvd, experation, cardType.getValue().toString());
            showAlert("Dear user,\nWe have got your purchase request.\nPlease check soon for final approval in your buyerInbox");
        }

        else if(paypalIsFull(user_paypal,password_paypal) && cardIsEmpty(cvd,experation,cardnumber,cardType))
        {
            conection_layer.buy_vacation_with_paypal(Integer.valueOf(vacationId), user_paypal, password_paypal);
            showAlert("Dear user,\nWe have got your purchase request.\nPlease check soon for final approval in your buyerInbox");
        }
        else
            showAlert("plase fill all the fileds, only in one way to payment-credit card or paypal");




    }

    private boolean cardIsFull(String cvd,String experation,String cardNumber,ChoiceBox cardType)
    {
        if( cvd.equals("") || experation.equals("") || cardNumber.equals("") || cardType.getValue()==null)
            return false;
        else
            return true;
    }
    private boolean paypalIsFull(String user,String pass)
    {
        if( user.equals("") || pass.equals("") )
            return false;
        else
            return true;
    }

    private boolean cardIsEmpty(String cvd,String experation,String cardNumber,ChoiceBox cardType)
    {
        if(cvd.equals("") && experation.equals("") && cardNumber.equals("") && cardType.getValue()==null)
            return true;
        else
            return false;
    }

    private boolean peypalIsEmpty(String user,String pass)
    {
        if(user.equals("") && pass.equals("") )
            return true;
        else
            return false;
    }
}