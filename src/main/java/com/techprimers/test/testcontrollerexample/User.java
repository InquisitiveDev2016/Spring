package com.techprimers.test.testcontrollerexample;

public class User {

    private String title;
    private String userId;
    private String userDateOfBirth;
    private String userJob;
    private String userAddress;
    private String canOpenBankAccount;
    private String canTradeStocks;
    private String promotionalMessage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User(String title, String userId) {
        this.title = title;
        this.userId = userId;
    }

    public String getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(String userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String  isCanOpenBankAccount() {
        return canOpenBankAccount;
    }

    public void setCanOpenBankAccount(String  canOpenBankAccount) {
        this.canOpenBankAccount = canOpenBankAccount;
    }

    public String  isCanTradeStocks() {
        return canTradeStocks;
    }

    public void setCanTradeStocks(String  canTradeStocks) {
        this.canTradeStocks = canTradeStocks;
    }

    public String getPromotionalMessage() {
        return promotionalMessage;
    }

    public void setPromotionalMessage(String promotionalMessage) {
        this.promotionalMessage = promotionalMessage;
    }

    public User() {
    }
}
