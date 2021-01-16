package com.atm.sa;

import com.atm.sa.account.DefaultAccount;
import com.atm.sa.atm.Atm;
import com.atm.sa.client.Client;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

        ApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");

        Atm atm = context.getBean("Atm", Atm.class);
        Client client1 = context.getBean("Client1", Client.class);
        atm.atmStart(client1);
        atm.enterPinCode(scanner.nextInt());
        if (validator.validate(atm).isEmpty()) {
            BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(1000));
            log.info("Клиенту удалось снять: " + rez + " руб.");
        }


        Client client11 = context.getBean("Client2", Client.class);
        atm.atmStart(client11);
        atm.enterPinCode(scanner.nextInt());
        if (validator.validate(atm).isEmpty()) {
            BigDecimal rez1 = atm.getMoneyForClient(BigDecimal.valueOf(1000));
            log.info("Клиенту удалось снять: " + rez1 + " руб.");
        }

        Client client12 = context.getBean("Client3", Client.class);
        atm.atmStart(client12);
        atm.enterPinCode(scanner.nextInt());
        if (validator.validate(atm).isEmpty()) {
            BigDecimal rez2 = atm.getMoneyForClient(BigDecimal.valueOf(100));
            log.info("Клиенту удалось снять: " + rez2 + " руб.");
        }

        log.info("Конец программы");
    }
}
