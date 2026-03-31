package model;

public class User {
    private String nameValue ;
    private String gmailValue;
    private String phoneValue;
    private String addressValue;
    private String passwordValue;
    private String role;

    public User(String nameValue, String gmailValue, String phoneValue, String addressValue, String passwordValue, String role) {
        this.nameValue = nameValue;
        this.gmailValue = gmailValue;
        this.phoneValue = phoneValue;
        this.addressValue = addressValue;
        this.passwordValue = passwordValue;
        this.role = role;
    }

    public String getNameValue() {
        return nameValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

    public String getGmailValue() {
        return gmailValue;
    }

    public void setGmailValue(String gmailValue) {
        this.gmailValue = gmailValue;
    }

    public String getPhoneValue() {
        return phoneValue;
    }

    public void setPhoneValue(String phoneValue) {
        this.phoneValue = phoneValue;
    }

    public String getAddressValue() {
        return addressValue;
    }

    public void setAddressValue(String addressValue) {
        this.addressValue = addressValue;
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "nameValue='" + nameValue + '\'' +
                ", gmailValue='" + gmailValue + '\'' +
                ", phoneValue='" + phoneValue + '\'' +
                ", addressValue='" + addressValue + '\'' +
                ", passwordValue='" + passwordValue + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
