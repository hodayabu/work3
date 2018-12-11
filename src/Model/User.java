package Model;

public class User {

    private String UserName;
    private String FirstName;
    private String LastName;
    private String City;
    private String Password;
    private String DateOfBirth;

    public User(String userName, String firstName, String lastName, String city, String password, String dateOfBirth) {
        UserName = userName;
        FirstName = firstName;
        LastName = lastName;
        City = city;
        Password = password;
        DateOfBirth = dateOfBirth;

    }//constractor

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getUserName() {
        return UserName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getCity() {
        return City;
    }

    public String getPassword() {
        return Password;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }
}
