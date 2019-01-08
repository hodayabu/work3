package Model;

        import java.sql.*;
        import java.util.*;

public class Model {


    private String currentLogInUser = "";


    public void setCurrentLogInUser(String currentLogInUser) {
        this.currentLogInUser = currentLogInUser;
    }

    public String getCurrentLogInUser() {
        return currentLogInUser;
    }

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


    public void Insert(String phone,String user, String pass, String birth, String first, String last, String city) {

        String sql = "INSERT INTO users (user_name,password,BDay,first_name,last_name, city, phone) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            pstmt.setString(3, birth);
            pstmt.setString(4, first);
            pstmt.setString(5, last);
            pstmt.setString(6, city);
            pstmt.setString(7, phone);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean exist(String userr) {

        String sql = "SELECT user_name FROM users where user_name=\"" + userr + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.getString("user_name").equals(userr) && !(userr.equals(""))) {
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
        String sql = "SELECT * FROM users where user_name=\"" + userr + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.getString("user_name").equals(userr)) {
                String ans = "user name:" + " " + rs.getString(1) + "\npassword:" + " " + rs.getString(2) + "\nday of birth:" + " " + rs.getString(3) +
                        "\nfirst name:" + " " + rs.getString(4) + "\nlast name:" + " " + rs.getString(5) + "\ncity:" + " " + rs.getString(6);
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

    private List<String> help(String currentuser, String newuser, String pass, String birth, String first, String last, String city) {
        List<String> ans = new ArrayList<>();

        String sql = "SELECT * FROM users where user_name=\"" + currentuser + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (newuser.equals(""))
                ans.add(0, rs.getString("user_name"));
            else
                ans.add(newuser);
            if (pass.equals(""))
                ans.add(1, rs.getString("password"));
            else
                ans.add(pass);
            if (birth.equals(""))
                ans.add(2, rs.getString("BDay"));
            else
                ans.add(pass);
            if (first.equals(""))
                ans.add(3, rs.getString("first_name"));
            else
                ans.add(first);
            if (last.equals(""))
                ans.add(4, rs.getString("last_name"));
            else
                ans.add(last);
            if (city.equals(""))
                ans.add(5, rs.getString("city"));
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

    public void update(String currentuser, String newuser, String pass, String birth, String first, String last, String city) {


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


            // update
            List<String> ans = help(currentuser, newuser, pass, birth, first, last, city);
            pstmt.setString(1, ans.get(0));
            pstmt.setString(2, ans.get(1));
            pstmt.setString(3, ans.get(2));
            pstmt.setString(4, ans.get(3));
            pstmt.setString(5, ans.get(4));
            pstmt.setString(6, ans.get(5));


            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void approveRequest(int vactionId, String buyer) {
        String sellar = getCurrentLogInUser();
        //insert record into fanalAprove
        String sql = "INSERT INTO FinalApprove (vacationId,buyerUser,sellarUser) VALUES(?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, vactionId);
            pstmt.setString(2, buyer);
            pstmt.setString(3, sellar);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void ApproveTradeRequest(int vacation_idSeller,int vacation_idBuyer, String buyer) {
        String sellar = getCurrentLogInUser();
        //insert record into fanalAprove
        String sql = "INSERT INTO TradeApprove (buyer,buyerVacation,seller,sellerVacation) VALUES(?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, buyer);
            pstmt.setInt(2, vacation_idBuyer);
            pstmt.setString(3, sellar);
            pstmt.setInt(4, vacation_idSeller);
            pstmt.executeUpdate();

            //delete vacation fron waiting requests
            String sql2 = "DELETE FROM WaitingForTrades where vacationOfOffer=? AND vacationOfOfferd=?";
            PreparedStatement pstmt1 = conn.prepareStatement(sql2);
            pstmt1.setInt(1, vacation_idBuyer);
            pstmt1.setInt(2, vacation_idSeller);
            pstmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean logIn(String userName, String password) {

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

    public void buyerOK(String buyerUser) {

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

        String sql2 = "DELETE FROM TradeNotApprove where buyer=?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql2)) {

            // set the corresponding param
            pstmt.setString(1, buyerUser);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql3 = "DELETE FROM TradeApprove where buyer=?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql3)) {

            // set the corresponding param
            pstmt.setString(1, buyerUser);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public HashMap<Vacation, String> checkMySellarInbox(String userName) {

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

    public HashMap<ArrayList<Vacation>, String> checkTradeSellarInbox(String userName) {

        HashMap<ArrayList<Vacation>, String> ans = new HashMap<>();
        String sql = "SELECT * FROM WaitingForTrades INNER JOIN vacationsForSale ON vacationsForSale.vacationId = WaitingForTrades.vacationOfOfferd where offeredUser=\"" + userName + "\"  ";
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
                Vacation buy=getTradeDetails(rs.getInt("vacationOfOffer"));
                ArrayList<Vacation> vec=new ArrayList<>();
                vec.add(vacation);
                vec.add(buy);
                ans.put(vec, rs.getString("offer_user"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ans;

    }

    public Vacation getTradeDetails(int vacationOfOffer) {
        String sql = "SELECT * FROM vacationsForSale where vacationId=\"" + vacationOfOffer + "\"  ";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
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

            return  vacation;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }


    public HashMap<Vacation, Boolean> checkMyBuyerInbox(String userName) {
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
                ans.put(vacation, true);

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
                ans.put(vacation, false);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return ans;

    }

    public HashMap<ArrayList<Vacation>, Boolean> checkTradeBuyerInbox(String userName) {

        HashMap<ArrayList<Vacation>, Boolean> ans = new HashMap<>();
        String sql = "SELECT * FROM TradeApprove INNER JOIN vacationsForSale ON vacationsForSale.vacationId = TradeApprove.buyerVacation where buyer=\"" + userName + "\"  ";
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
                Vacation sell=getTradeDetails(rs.getInt("sellerVacation"));
                ArrayList<Vacation> vec=new ArrayList<>();
                vec.add(vacation);
                vec.add(sell);
                ans.put(vec,true);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql1 = "SELECT * FROM TradeNotApprove INNER JOIN vacationsForSale ON vacationsForSale.vacationId = TradeNotApprove.buyerVacation where buyer=\"" + userName + "\"  ";
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
                Vacation sell=getTradeDetails(rs.getInt("sellerVacation"));
                ArrayList<Vacation> vec=new ArrayList<>();
                vec.add(vacation);
                vec.add(sell);
                ans.put(vec,false);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ans;

    }


    public void notApproveRequest(int vacation_id, String buyer) {
        String sellar = getCurrentLogInUser();
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
        update_availbility(vacation_id, true);


    }

    public void notApproveTradeRequest(int vacation_idSeller,int vacation_idBuyer, String buyer) {
        String sellar = getCurrentLogInUser();
        //insert record into notApprove
        String sql = "INSERT INTO TradeNotApprove (buyer,buyerVacation,seller,sellerVacation) VALUES(?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, buyer);
            pstmt.setInt(2, vacation_idBuyer);
            pstmt.setString(3, sellar);
            pstmt.setInt(4, vacation_idSeller);
            pstmt.executeUpdate();


            //delete from waitForSaller
            String sql2 = "DELETE FROM WaitingForTrades where vacationOfOffer=? AND vacationOfOfferd=?";
            PreparedStatement pstmt1 = conn.prepareStatement(sql2);
            pstmt1.setInt(1, vacation_idBuyer);
            pstmt1.setInt(2, vacation_idSeller);
            pstmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //make the vacation avialble again
        update_availbility(vacation_idBuyer, true);
        update_availbility(vacation_idSeller, true);


    }



    public ArrayList<Vacation> search_vacation_by_country(String country) {
        ArrayList<Vacation> ans = new ArrayList<>();
        String sql = "SELECT * FROM vacationsForSale where destenationCountry=\"" + country + "\" AND available=1 ";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String seller=rs.getString("saller");
                String userNow=getCurrentLogInUser();
                if(userNow.equals(seller))
                    continue;
                Vacation vacation = new Vacation(seller,
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

    public void advertize_vacation(Vacation vacation) {

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

    public String buy_vacation(int vacationId, String user_buyer) {
        update_availbility(vacationId, false);
        add_recordIn_waitingForSallerVerefication(vacationId, user_buyer, get_the_saller(vacationId));
        String Phone=getPhone(vacationId);
        return Phone;
    }

    public String getPhone(int vacationId) {
        String sql = "SELECT * FROM vacationsForSale INNER JOIN users ON vacationsForSale.saller = users.user_name where vacationId=\"" + vacationId + "\"  ";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            String phone=rs.getString("phone");
            return phone;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";

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
        String saller = "";
        String sql = "SELECT * FROM vacationsForSale where vacationId=\"" + vacationId + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            saller = rs.getString("saller");
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
            pstmt.setBoolean(1, b);
            pstmt.setInt(2, vacationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean trade(String vacationId, String user_saller, String dest_country, String arrival, String departure) {
        int vac2=vacation_exist(dest_country, arrival, departure);
        if (vac2==-1)
            return false;
        insert_To_waiting_trades(vac2,currentLogInUser,user_saller,Integer.valueOf(vacationId));
        update_availbility(vac2,false);
        update_availbility(Integer.valueOf(vacationId),false);
        return true;
    }

    private void insert_To_waiting_trades(int vac2, String currentLogInUser, String user_saller, int vacationId) {
        String sql1 = "INSERT INTO WaitingForTrades (offer_user,vacationOfOffer,offeredUser,vacationOfOfferd) VALUES(?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {
            pstmt1.setString(1, currentLogInUser);
            pstmt1.setInt(2, vac2);
            pstmt1.setString(3, user_saller);
            pstmt1.setInt(4, vacationId);
            pstmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private int vacation_exist(String dest_country, String arrival, String departure) {
        String user = this.currentLogInUser;
        String sql = "SELECT * FROM vacationsForSale where saller=\"" + user + "\"  AND dateArrive=\"" + arrival + "\" AND dateDeparture=\"" + departure + "\" AND destenationCountry=\"" + dest_country + "\" ";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.getString("saller").equals(user) && rs.getBoolean("available")==true)
                return rs.getInt("vacationId");


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void gotTheMoney(String id, String buyer) {
        String sql1 = "DELETE FROM waitForSellar where vacationId=?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {
            pstmt1.setInt(1, Integer.valueOf(id));
            pstmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}