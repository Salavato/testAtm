package com.atm.sa;

import com.atm.sa.account.Account;
import com.atm.sa.atm.Atm;
import com.atm.sa.client.Client;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        Account account = new Account(BigDecimal.valueOf(500100));
        Client client1 = new Client(1122, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500));


        BigDecimal rez = BigDecimal.ZERO;
        atm.atmStart(client1);
        if (atm.isPinValid(1122)) {  // atm.isPinValid(client1.getPin());
            rez = atm.getMoneyForClient(BigDecimal.valueOf(1000));
        } else {
            System.out.println("Пин код не верный");
        }

        System.out.println("Клиенту удалось снять: " + rez + " руб.");

    }
}
