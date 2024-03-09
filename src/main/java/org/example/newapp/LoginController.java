package org.example.newapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Objects;

public class LoginController {
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    public void login() {
        String username = usernameField.getText();
        String pin = passwordField.getText();

        boolean isLogin = validateLogin(username , pin);


        if(isLogin) {
            navigateToBankPage();
        }else {
            System.out.println("Login failed");
        }
    }

    private boolean validateLogin(String username, String pin) {
        Users usersObj = new Users();
        List<InnerUsersImpl> users = usersObj.getUsers();

        for (InnerUsers user : users) {
            String fullName = (user.getFirstName().toUpperCase().charAt(0) + "" + user.getLastName().toUpperCase().charAt(0)).toUpperCase();
            System.out.println(fullName + " " + username + " " + pin + " " + user.getPassword());
            if (username.toUpperCase().equals(fullName) && pin.equals("" + user.getPassword())) {
                return true;
            }
        }
        return false;
    }



    public void navigateToBankPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bank-view.fxml"));
            VBox root = loader.load();
            BankApplication.getMainStage().setScene(new Scene(root, 600, 500));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
