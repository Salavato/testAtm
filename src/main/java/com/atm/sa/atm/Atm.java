package com.atm.sa.atm;

import com.atm.sa.account.Account;
import com.atm.sa.client.Client;
import com.atm.sa.exception.BusinessException;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Atm {
    private static final Client EMPTY_CLIENT = new Client(0000, new Account(BigDecimal.ZERO));
    private BigDecimal money;
    private Client client; //клиент который вставил свою карту
    private int pinCode;
    private int pinCodeCount;

    public Atm(BigDecimal money) {
        this.money = money;
        this.client = EMPTY_CLIENT;
        this.pinCodeCount = 0;
    }

    //клиент вставил карту и банкомат получил информацию о клиенте
    public void atmStart(Client client) {
        this.client = client;
    }

    //проверка дублирования запроса
    public void enterPinCode(int pinCode) {
        if (pinCodeCount < 1) {
            this.pinCode = pinCode;
            pinCodeCount++;
        } else {
            throw new BusinessException("пин-код введен дважды!");
        }
    }


    //снятие наличных
    public BigDecimal getMoneyForClient(BigDecimal amount) {
        BigDecimal cash = BigDecimal.ZERO;

        if (!isPinValid()) {
            throw new BusinessException("Пин-код не верный");
        }

        if (client.getAccount().isEnoughMoney(amount) && isEnoughMoney(amount)) {
            money = money.subtract(amount);
            client.getAccount().subtractAmount(amount); //вычитаем со счета клиента в банке
            cash = amount;
        }
        // зануляем данные о клиенте, чтобы банкомат больше ничего не знал о клиенте
        client = EMPTY_CLIENT;
        //зануляем данные проверки дублирования
        pinCodeCount = 0;
        return cash;
    }

    //проверка наличия денег в банкомате
    private boolean isEnoughMoney(BigDecimal amount) {
        return money.compareTo(amount) >= 0;
    }

    //проверка пин-кода
    private boolean isPinValid() {
        return client.getPin() == pinCode;
    }

}
