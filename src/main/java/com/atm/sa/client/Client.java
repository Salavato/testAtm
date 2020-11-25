package com.atm.sa.client;

import com.atm.sa.account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private int pin;
    private Account account;

}
