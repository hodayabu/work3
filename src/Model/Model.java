package Model;

        import java.sql.*;
        import java.util.*;

public class Model {



    private String currentLogInUser="";


    public void setCurrentLogInUser(String currentLogInUser) {
        this.currentLogInUser = currentLogInUser;
    }
    public String getCurrentLogInUser() {
        return currentLogInUser; }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:resources\\db.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;






    }


    public void Insert(String user, String pass, String birth, String first, String last, String city) {

        String sql = "INSERT INTO users (user_name,password,BDay,first_name,last_name, city) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            pstmt.setString(3, birth);
            pstmt.setString(4, first);
            pstmt.setString(5, last);
            pstmt.setString(6, city);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean exist(String userr) {

        String sql = "SELECT user_name FROM users where user_name=\""+userr+"\"" ;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            if(rs.getString("user_name").equals(userr) && !(userr.equals("")) ){
                return true;
            }

            //stmt.close();
            //conn.close();
            //rs.close();
            //conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public String read(String userr) throws InterruptedException {
        String sql = "SELECT * FROM users where user_name=\""+userr+"\"" ;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            if(rs.getString("user_name").equals(userr)){
                String ans="user name:"+" "+rs.getString(1)+"\npassword:"+" "+rs.getString(2)+"\nday of birth:"+" "+rs.getString(3)+
                        "\nfirst name:"+" "+rs.getString(4)+"\nlast name:"+" "+rs.getString(5)+"\ncity:"+" "+rs.getString(6);
                System.out.println(ans);
                return ans;
            }

            //stmt.close();
            //conn.close();
            //rs.close();
            //conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void delete(String userr) {
        String sql = "DELETE FROM users where user_name=?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, userr);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<String> help (String  currentuser,String newuser  ,String pass, String birth, String first, String last, String city)
    {
        List<String> ans=new ArrayList<>();

        String sql = "SELECT * FROM users where user_name=\""+currentuser+"\"" ;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            if(newuser.equals(""))
                ans.add(0,rs.getString("user_name"));
            else
                ans.add(newuser);
            if(pass.equals(""))
                ans.add(1,rs.getString("password"));
            else
                ans.add(pass);
            if(birth.equals(""))
                ans.add(2,rs.getString("BDay"));
            else
                ans.add(pass);
            if(first.equals(""))
                ans.add(3,rs.getString("first_name"));
            else
                ans.add(first);
            if(last.equals(""))
                ans.add(4,rs.getString("last_name"));
            else
                ans.add(last);
            if(city.equals(""))
                ans.add(5,rs.getString("city"));
            else
                ans.add(city);

            //stmt.close();
            //conn.close();
            //rs.close();
            //conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ans;
    }

    public void update(String currentuser, String newuser ,String pass, String birth, String first, String last, String city) {


        String sql = "UPDATE users SET user_name = ? , "
                + "password = ? ,"
                + "BDay = ? ,"
                + "first_name = ? ,"
                + "last_name = ? ,"
                + "city = ?"
                + "WHERE user_name=?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(7, currentuser);


            // set the corresponding param
            /*
            if((!newuser.equals("")))
                pstmt.setString(1, newuser);
            if((!pass.equals("")))
                pstmt.setString(2, pass);
            if((!birth.equals("")))
                pstmt.setString(3, birth);
            if((!first.equals("")))
                pstmt.setString(4, first);
            if((!last.equals("")))
                pstmt.setString(5, last);
            if((!city.equals("")))
                pstmt.setString(6, city);
            */
            // update
            List<String> ans= help(currentuser,newuser  ,pass, birth, first, last, city);
            pstmt.setString(1,ans.get(0));
            pstmt.setString(2,ans.get(1));
            pstmt.setString(3,ans.get(2));
            pstmt.setString(4,ans.get(3));
            pstmt.setString(5,ans.get(4));
            pstmt.setString(6,ans.get(5));




            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void approveRequest(int vactionId,String buyer){
        String sellar=getCurrentLogInUser();
        //insert record into fanalAprove
        String sql = "INSERT INTO FinalApprove (vacationId,buyerUser,sellarUser) VALUES(?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, vactionId);
            pstmt.setString(2, buyer);
            pstmt.setString(3, sellar);
            pstmt.executeUpdate();

            //delete vacation fron waiting requests
            String sql2 = "DELETE FROM waitForSellar where vacationId=?";
            PreparedStatement pstmt1 = conn.prepareStatement(sql2);
            pstmt1.setInt(1, vactionId);
            pstmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean logIn (String userName,String password) {

        if (!exist(userName))
            return false;


        //make sure the password is correct
        String sql = "SELECT password FROM users where user_name=\"" + userName + "\"";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println(rs.getString("password"));
            if (!rs.getString("password").equals(password)) {
                return false;
            }

            currentLogInUser = userName;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void buyerOK(String buyerUser){

        String sql = "DELETE FROM FinalApprove where buyerUser=?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, buyerUser);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql1 = "DELETE FROM notApprove where buyerUser=?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql1)) {

            // set the corresponding param
            pstmt.setString(1, buyerUser);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public HashMap<Vacation,String> checkMySellarInbox(String userName) {

        HashMap<Vacation, String> ans = new HashMap<>();
        String sql = "SELECT * FROM waitForSellar INNER JOIN vacationsForSale ON vacationsForSale.vacationId = waitForSellar.vacationId where sellarUser=\"" + userName + "\"  ";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vacation vacation = new Vacation(rs.getString("saller"),
                        rs.getString("airPortCampany"),
                        rs.getString("dateDeparture"),
                        rs.getString("dateArrive"),
                        rs.getString("luggage"),
                        rs.getString("numOfTickets"),
                        rs.getString("destenationCountry"),
                        rs.getString("destinationCity"),
                        rs.getBoolean("returnFligth"),
                        rs.getString("ticketType"),
                        rs.getBoolean("available"),
                        rs.getInt("vacationId"),
                        rs.getString("price"));
                ans.put(vacation, rs.getString("buyerUser"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ans;

    }

    public HashMap<Vacation,Boolean> checkMyBuyerInbox(String userName){
        HashMap<Vacation, Boolean> ans = new HashMap<>();
        String sql = "SELECT * FROM FinalApprove INNER JOIN vacationsForSale ON vacationsForSale.vacationId = FinalApprove.vacationId where buyerUser=\"" + userName + "\"  ";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vacation vacation = new Vacation(rs.getString("saller"),
                        rs.getString("airPortCampany"),
                        rs.getString("dateDeparture"),
                        rs.getString("dateArrive"),
                        rs.getString("luggage"),
                        rs.getString("numOfTickets"),
                        rs.getString("destenationCountry"),
                        rs.getString("destinationCity"),
                        rs.getBoolean("returnFligth"),
                        rs.getString("ticketType"),
                        rs.getBoolean("available"),
                        rs.getInt("vacationId"),
                        rs.getString("price"));
                ans.put(vacation,true);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql1 = "SELECT * FROM notApprove INNER JOIN vacationsForSale ON vacationsForSale.vacationId = notApprove.vacationId where buyerUser=\"" + userName + "\"  ";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql1)) {
            while (rs.next()) {
                Vacation vacation = new Vacation(rs.getString("saller"),
                        rs.getString("airPortCampany"),
                        rs.getString("dateDeparture"),
                        rs.getString("dateArrive"),
                        rs.getString("luggage"),
                        rs.getString("numOfTickets"),
                        rs.getString("destenationCountry"),
                        rs.getString("destinationCity"),
                        rs.getBoolean("returnFligth"),
                        rs.getString("ticketType"),
                        rs.getBoolean("available"),
                        rs.getInt("vacationId"),
                        rs.getString("price"));
                ans.put(vacation,false);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return ans;

    }

    public void notApproveRequest(int vacation_id,String buyer){
        String sellar=getCurrentLogInUser();
        //insert record into notApprove
        String sql = "INSERT INTO notApprove (vacationId,buyerUser,sellarUser) VALUES(?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, vacation_id);
            pstmt.setString(2, buyer);
            pstmt.setString(3, sellar);
            pstmt.executeUpdate();


            //delete from waitForSaller
            String sql2 = "DELETE FROM waitForSellar where vacationId=?";
            PreparedStatement pstmt1 = conn.prepareStatement(sql2);
            pstmt1.setInt(1, vacation_id);
            pstmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //make the vacation avialble again
        update_availbility(vacation_id,true);


    }

    public ArrayList<Vacation> search_vacation_by_country(String country){
        ArrayList<Vacation> ans=new ArrayList<>();
        String sql = "SELECT * FROM vacationsForSale where destenationCountry=\""+country+"\" AND available=1 ";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()){
                Vacation vacation = new Vacation(rs.getString("saller"),
                        rs.getString("airPortCampany"),
                        rs.getString("dateDeparture"),
                        rs.getString("dateArrive"),
                        rs.getString("luggage"),
                        rs.getString("numOfTickets"),
                        rs.getString("destenationCountry"),
                        rs.getString("destinationCity"),
                        rs.getBoolean("returnFligth"),
                        rs.getString("ticketType"),
                        rs.getBoolean("available"),
                        rs.getInt("vacationId"),
                        rs.getString("price"));

                ans.add(vacation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ans;
    }

    public void  advertize_vacation(Vacation vacation)
    {

        String sql = "INSERT INTO vacationsForSale (vacationId,saller,airPortCampany,dateDeparture,dateArrive,luggage, numOfTickets,destenationCountry,destinationCity,returnFligth,ticketType,available,price) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, vacation.getVacation_id());
            pstmt.setString(2, vacation.getUser_saller());
            pstmt.setString(3, vacation.getAirPortCompany());
            pstmt.setString(4, vacation.getDateDepar());
            pstmt.setString(5, vacation.getDateArrive());
            pstmt.setString(6, vacation.getLagguge());
            pstmt.setString(7, vacation.getNumOftickets());
            pstmt.setString(8, vacation.getDestinationCountry());
            pstmt.setString(9, vacation.getDestinationCity());
            pstmt.setBoolean(10, vacation.getReturnFlight());
            pstmt.setString(11, vacation.getTicketType());
            pstmt.setBoolean(12, vacation.getAvailble());
            pstmt.setString(13, vacation.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buy_vacation_with_credit(int vacationId,String user_buyer,String credit_number,String cvd,String experation,String cardType){

        add_Record_of_payment_table_credit(user_buyer, credit_number, cvd, experation, cardType);
        update_availbility(vacationId,false);
        add_recordIn_waitingForSallerVerefication(vacationId,user_buyer, get_the_saller(vacationId));
    }

    private void add_recordIn_waitingForSallerVerefication(int vacationId, String user_buyer, String user_saller) {
        String sql = "INSERT INTO waitForSellar (vacationId,buyerUser,sellarUser) VALUES(?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, vacationId);
            pstmt.setString(2, user_buyer);
            pstmt.setString(3, user_saller);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String get_the_saller(int vacationId) {
        String saller="";
        String sql = "SELECT * FROM vacationsForSale where vacationId=\""+vacationId+"\"" ;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            saller= rs.getString("saller");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return saller;
    }

    private void update_availbility(int vacationId, boolean b) {
        String sql = "UPDATE vacationsForSale SET available = ?  "
                + "WHERE vacationId=?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1,b);
            pstmt.setInt(2,vacationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void add_Record_of_payment_table_credit(String user_buyer, String credit_number, String cvd, String experation, String cardType){
        String sql = "INSERT INTO PaymentData (userName,creditNumber,cvd,experation,cardType) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user_buyer);
            pstmt.setString(2, credit_number);
            pstmt.setString(3, cvd);
            pstmt.setString(4, experation);
            pstmt.setString(5, cardType);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void buy_vacation_with_paypal(int vacationId, String buyer, String userpaypal, String pass) {
        add_Record_of_payment_table_paypal(buyer,userpaypal,pass);
        update_availbility(vacationId,false);
        add_recordIn_waitingForSallerVerefication(vacationId,buyer, get_the_saller(vacationId));
    }

    private void add_Record_of_payment_table_paypal(String buyer, String userpaypal, String pass) {
        String sql = "INSERT INTO paypal (userpaypal,password,buyer) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userpaypal);
            pstmt.setString(2, pass);
            pstmt.setString(3, buyer);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}