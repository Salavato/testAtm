package com.atm.sa;

import com.atm.sa.account.DefaultAccount;
import com.atm.sa.account.SavingAccount;
import com.atm.sa.atm.Atm;
import com.atm.sa.client.Client;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        DefaultAccount account = new DefaultAccount(BigDecimal.valueOf(500100));
        Client<DefaultAccount> client1 = new Client<>(1122, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500));
        atm.atmStart(client1);
        atm.enterPinCode(scanner.nextInt());
        BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(1000));
        System.out.println("Клиенту удалось снять: " + rez + " руб.");


        SavingAccount account11 = new SavingAccount(BigDecimal.valueOf(500));
        Client<SavingAccount> client11 = new Client<>(1122, account11);
        atm.atmStart(client11);
        atm.enterPinCode(scanner.nextInt());
        BigDecimal rez1 = atm.getMoneyForClient(BigDecimal.valueOf(1000));
        System.out.println("Клиенту удалось снять: " + rez1 + " руб.");


        SavingAccount account12 = new SavingAccount(BigDecimal.valueOf(1000));
        Client<SavingAccount> client12 = new Client<>(1122, account12);
        atm.atmStart(client12);
        atm.enterPinCode(scanner.nextInt());
        BigDecimal rez2 = atm.getMoneyForClient(BigDecimal.valueOf(100));
        System.out.println("Клиенту удалось снять: " + rez2 + " руб.");


    }
}
