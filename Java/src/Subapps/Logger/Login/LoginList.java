package Subapps.Logger.Login;


import Utility.CommonCommands;
import Utility.Encryption;
import Utility.FileEditing;

import java.util.ArrayList;
import java.util.List;

public class LoginList {
    private String SecretKey = "Alarm";
    private CommonCommands CC = new CommonCommands();
    private FileEditing fe = new FileEditing();
    private Encryption encryption;
    private String SaveFilelocation = "Java/src/Subapps/Logger/Login/SavedLoginList.loginfo";
    private List<Login> LoginList = new ArrayList();
    /*Selected login Save & Load*/
    private String SelectedSaveFilelocation = "Java/src/Subapps/Logger/Login/OperetedLogin.loginfo";
    private int SavedLoginIndex;

    public LoginList() {
    }

    /*Alarm list*/
    public List<Login> getLoginList() {
        return LoginList;
    }

    public void SaveLoginList() {
        String saveLogin = "";
        for (Login Login : LoginList) {
            saveLogin += LoginToString(Login);
        }
        saveLogin.replace(" ", "_");
        byte[] savelogin = encryption.Encryption(saveLogin, SecretKey);
        fe.SavebitFile(savelogin, SaveFilelocation);
    }

    public void LoadLoginList() {
        String SecretKey = "Alarm";
        byte[] Decrypt = fe.LoadbitFile(SaveFilelocation);
        Decrypt = encryption.Decryption(Decrypt, SecretKey);
        String sDecrypt = new String(Decrypt);
        String[] Decrypted = sDecrypt.replace("!@", "\\n").split("\\n");
        for (String decrypted : Decrypted) {
            if (decrypted.equals("")) break;
            String[] arg = decrypted.split("\\|");
            if (arg[3].contains("_")) arg[3].replace("_", " ");
            AddLogin(new Login(arg[0], arg[1], arg[2], arg[3]));
        }
        ;
    }

    public void AddLogin(Login Login) {
        this.LoginList.add(Login);
    }

    public void ImportLoginList(String path) throws Exception {
        String SecretKey = "Alarm";
        byte[] Decrypt = fe.LoadbitFile(path);
        Decrypt = encryption.Decryption(Decrypt, SecretKey);
        String sDecrypt = new String(Decrypt);
        String[] Decrypted = sDecrypt.replace("!@", "\\n").split("\\n");
        for (String decrypted : Decrypted) {
            if (decrypted.equals("")) break;
            String[] arg = decrypted.split("\\|");
            if (arg[3].contains("_")) arg[3].replace("_", " ");
            AddLogin(new Login(arg[0], arg[1], arg[2], arg[3]));
        }
        ;
    }

    public int getSavedLoginIndex() {
        byte[] Decrypt = fe.LoadbitFile(SelectedSaveFilelocation);
        Decrypt = encryption.Decryption(Decrypt, SecretKey);
        String sDecrypt = new String(Decrypt);
        String[] Decrypted = sDecrypt.replace("!@", "\\n").split("\\n");
        for (String decrypted : Decrypted) {
            if (decrypted.equals("")) break;
            String[] arg = decrypted.split("\\|");
            if (arg[3].contains("_")) arg[3].replace("_", " ");
            SavedLoginIndex = CC.SetNum(arg[0]);
        }
        return SavedLoginIndex;
    }

    public void SaveLogin(Login login, int index) {
        String saveLogin = "";
        saveLogin += index + "|";
        saveLogin += LoginToString(login);
        saveLogin.replace(" ", "_");
        byte[] savelogin = encryption.Encryption(saveLogin, SecretKey);
        fe.SavebitFile(savelogin, SelectedSaveFilelocation);
    }

    public Login LoadLogin() {
        Login login = (new Login("error", "error", "error", "error"));
        byte[] Decrypt = fe.LoadbitFile(SelectedSaveFilelocation);
        Decrypt = encryption.Decryption(Decrypt, SecretKey);
        String sDecrypt = new String(Decrypt);
        String[] Decrypted = sDecrypt.replace("!@", "\\n").split("\\n");
        for (String decrypted : Decrypted) {
            if (decrypted.equals("")) break;
            String[] arg = decrypted.split("\\|");
            if (arg[3].contains("_")) arg[3].replace("_", " ");
            login = (new Login(arg[1], arg[2], arg[3], arg[4]));
        }
        return login;
    }

    public String LoginToString(Login Login) {
        if (Login.getDescription().equals("")) Login.setDescription("no_description_enterd");
        String AsString =
                Login.getUserName() + "|" +
                        Login.getPassWord() + "|" +
                        Login.getSiteURL() + "|" +
                        Login.getDescription() + "\n";
        return AsString;
    }

}
