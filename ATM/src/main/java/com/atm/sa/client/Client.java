package com.atm.sa.client;

import com.atm.sa.account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client<T extends Account> {

    public Client(int pin, long accountId) {
        this.pin = pin;
        this.accountId = accountId;
    }

    private int pin;
    private long accountId;
    private T account;

}
