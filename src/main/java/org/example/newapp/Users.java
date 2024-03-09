package org.example.newapp;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

enum EType {
    WITHDRAWAL, DEPOSIT
}

interface InnerUsers {
    int getId();
    String getFirstName();
    String getLastName();
    int getPassword();
    float getBalance();
    InnerTransactions[] getTransactions();
}

interface InnerTransactions {
    String getDate();
    float getAmount();
    EType getType();
}

class InnerTransactionsImpl implements InnerTransactions {
    private String date;
    private float amount;
    private EType type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public EType getType() {
        return type;
    }

    public void setType(EType type) {
        this.type = type;
    }
}

class InnerUsersImpl implements InnerUsers {
    private int id;
    private String firstName;
    private String lastName;
    private int password;
    private float balance;
    private InnerTransactionsImpl[] transactions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public InnerTransactionsImpl[] getTransactions() {
        return transactions;
    }

    public void setTransactions(InnerTransactionsImpl[] transactions) {
        this.transactions = transactions;
    }
}


public class Users {

    public List<InnerUsersImpl> getUsers() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<InnerUsersImpl> users = mapper.readValue(new File("src/main/resources/data/users.json"), new TypeReference<List<InnerUsersImpl>>() {});
            return users;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Users usersObj = new Users();
        List<InnerUsersImpl> users = usersObj.getUsers();

        if (users != null) {
            for (InnerUsers user : users) {
                System.out.println("ID: " + user.getId());
                System.out.println("First Name: " + user.getFirstName());
                System.out.println("Last Name: " + user.getLastName());
                System.out.println("Password: " + user.getPassword());
                System.out.println("Balance: " + user.getBalance());
                System.out.println("Transactions: ");
                for (InnerTransactions transaction : user.getTransactions()) {
                    System.out.println("  Date: " + transaction.getDate());
                    System.out.println("  Amount: " + transaction.getAmount());
                    System.out.println("  Type: " + transaction.getType());
                }
            }
        } else {
            System.out.println("Failed to read users from JSON file.");
        }
    }
}
