package com.atm.sa.client;

import com.atm.sa.account.Account;

public class Client {
    private int pin;
    private Account account;

    public Client(int pin, Account account) {
        this.pin = pin;
        this.account = account;
    }

    public int getPin() {
        return pin;
    }

    public Account getAccount() {
        return account;
    }

}
