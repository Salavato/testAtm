package com.atm.sa.atm;

import com.atm.sa.account.Account;
import com.atm.sa.client.Client;

import java.math.BigDecimal;

public class Atm {
    private BigDecimal money;
    private Client client; //клиент который вставил свою карту


    public Atm(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }

    //проверка пин-кода
    public boolean isPinValid(int pin) {
        if (client.getPin() == pin) {
            return true;
        }
        System.out.println("Пин код не верный");
        client = null; // зануляем данные о клиенте, чтобы банкомат больше ничего не знал о клиенте
        return false;
    }

    //клиент вставил карту и банкомат получил информацию о клиенте
    public void atmStart(Client client) {
        this.client = client;
        System.out.println("Здравствуйте Клиент");
    }

    //снятие наличных
    public BigDecimal getAmount(BigDecimal amount) {
        //проверка наличия денег на счете клиента
        Account account = client.getAccount();
        BigDecimal accountMoney = account.getMoney();
        if (accountMoney.compareTo(amount) < 0) {
            System.out.println("Недостаточно средств на счете");
            return BigDecimal.ZERO;
        }
        //проверка наличия денег в банкомате
        if (money.compareTo(amount) >= 0) {
            money = money.subtract(amount);
            System.out.println("Ваша сумма: " + amount);
            client.getAccount().subtractAmount(amount); //вычитаем со счета клиента в банке
            client = null; // зануляем данные о клиенте, чтобы банкомат больше ничего не знал о клиенте
            return amount;
        }
        System.out.println("В банкомате нет указанной суммы, воспользуйтесь другим банкоматом");
        // зануляем данные о клиенте, чтобы банкомат больше ничего не знал о клиенте
        client = null;
        return BigDecimal.ZERO;
    }

}
