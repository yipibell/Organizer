package Subapps.Logger.Login;

import javafx.scene.control.CheckBox;

public class Login {
    /*Constructor*/
    private String UserName;
    private String PassWord;
    private String SiteURL;
    private String Description;

    private CheckBox Check;

    public Login(String userName, String passWord, String siteURL, String description) {
        this.UserName = userName;
        this.PassWord = passWord;
        this.SiteURL = siteURL;
        this.Description = description;
        this.Check = new CheckBox();
    }


    /*Getters Setters & ToString*/
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getSiteURL() {
        return SiteURL;
    }

    public void setSiteURL(String siteURL) {
        SiteURL = siteURL;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public CheckBox getCheck() {
        return Check;
    }

    public void setCheck(CheckBox check) {
        Check = check;
    }

    @Override
    public String toString() {
        return "Login:" +
                "UserName=" + UserName +
                ", PassWord='" + PassWord +
                ", SiteURL='" + SiteURL +
                ", Description='" + Description + "\n";
    }
}