package com.atm.sa;

import com.atm.sa.account.DefaultAccount;
import com.atm.sa.account.SavingAccount;
import com.atm.sa.atm.Atm;
import com.atm.sa.client.Client;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Scanner;

@Log
public class Main {
    public static void main(String[] args) {
        log.info("Начало программы");

        Scanner scanner = new Scanner(System.in);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        DefaultAccount account = new DefaultAccount(BigDecimal.valueOf(500100));
        Client<DefaultAccount> client1 = new Client<>(1122, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500));
        atm.atmStart(client1);
        atm.enterPinCode(scanner.nextInt());
        if (validator.validate(atm).isEmpty()) {
            BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(1000));
            log.info("Клиенту удалось снять: " + rez + " руб.");
        }

        SavingAccount account11 = new SavingAccount(BigDecimal.valueOf(500));
        Client<SavingAccount> client11 = new Client<>(1122, account11);
        atm.atmStart(client11);
        atm.enterPinCode(scanner.nextInt());
        if (validator.validate(atm).isEmpty()) {
            BigDecimal rez1 = atm.getMoneyForClient(BigDecimal.valueOf(1000));
            log.info("Клиенту удалось снять: " + rez1 + " руб.");
        }

        SavingAccount account12 = new SavingAccount(BigDecimal.valueOf(1000));
        Client<SavingAccount> client12 = new Client<>(1122, account12);
        atm.atmStart(client12);
        atm.enterPinCode(scanner.nextInt());
        if (validator.validate(atm).isEmpty()) {
            BigDecimal rez2 = atm.getMoneyForClient(BigDecimal.valueOf(100));
            log.info("Клиенту удалось снять: " + rez2 + " руб.");
        }

        log.info("Конец программы");
    }
}
