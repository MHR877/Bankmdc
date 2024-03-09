package org.example.newapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class BankController {
    @FXML
    private Label balanceLabel;

    @FXML
    private TextField addMoneyField;

    @FXML
    private TextField withdrawMoneyField;


    @FXML 
    private Label mushChange;

    public float total = 0;
    public int amount = 0;

    public void DisplayBalance() {
        balanceLabel.setText("Current Balance: $" + total);
    }

    @FXML
    protected void addMoney() {
        String text = addMoneyField.getText();
        if (text == null) {
            return;
        } else {
            float getValue = Float.parseFloat(text);
            total = total + getValue;

            DisplayBalance();
            addMoneyField.setText("");
        }
    }

    @FXML
    protected void withdrawMoney() {
        String text = withdrawMoneyField.getText();
        if (text == null) {
            return;
        } else {
            float getValue = Float.parseFloat(text);
            total = total - getValue;

            DisplayBalance();
            withdrawMoneyField.setText("");
        }
    }

    @FXML
    protected void changeAmount() {
        String text = mushChange.getText();
        int getValue = Integer.parseInt(text);

        amount = getValue;
    }

    @FXML
    protected void increase() {
        changeAmount();
        amount++;

        mushChange.setText(amount + "");
    }

    @FXML
    protected void decrease() {
        changeAmount();
        amount--;

        mushChange.setText(amount + "");
    }

    @FXML
    protected void addChange() {
        total = total + amount;
        DisplayBalance();
    }

    @FXML
    protected void logout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            BorderPane root = loader.load();
            BankApplication.getMainStage().setScene(new Scene(root, 600, 500));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
