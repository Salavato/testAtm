package com.atm.sa;

import com.atm.sa.account.Account;
import com.atm.sa.atm.Atm;
import com.atm.sa.client.Client;
import javafx.scene.transform.Scale;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Account account = new Account(BigDecimal.valueOf(500100));
        Client client1 = new Client(1122, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500));


        atm.atmStart(client1);
        atm.enterPinCode(scanner.nextInt());

        BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(1000));

        System.out.println("Клиенту удалось снять: " + rez + " руб.");



        Account account11 = new Account(BigDecimal.valueOf(500100));
        Client client11 = new Client(1122, account11);
        atm.atmStart(client11);
        atm.enterPinCode(scanner.nextInt());
        BigDecimal rez1 = atm.getMoneyForClient(BigDecimal.valueOf(1000));

        System.out.println("Клиенту удалось снять: " + rez1 + " руб.");


    }
}
