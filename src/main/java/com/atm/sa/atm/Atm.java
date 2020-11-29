package com.atm.sa.atm;

import com.atm.sa.client.Client;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Atm {
    private BigDecimal money;
    private Client client; //клиент который вставил свою карту

    public Atm(BigDecimal money) {
        this.money = money;
    }

    //клиент вставил карту и банкомат получил информацию о клиенте
    public void atmStart(Client client) {
        this.client = client;
    }

    //проверка пин-кода
    public boolean isPinValid(int pin) {
        if (client.getPin() == pin) {
            return true;
        }
        client = null; // зануляем данные о клиенте, чтобы банкомат больше ничего не знал о клиенте
        return false;
    }



    //снятие наличных
    public BigDecimal getMoneyForClient(BigDecimal amount) {
        if (client.getAccount().isEnoughMoney(amount) && isEnoughMoney(amount)) {
            money = money.subtract(amount);
            client.getAccount().subtractAmount(amount); //вычитаем со счета клиента в банке
            client = null; // зануляем данные о клиенте, чтобы банкомат больше ничего не знал о клиенте
            return amount;
        }
        // зануляем данные о клиенте, чтобы банкомат больше ничего не знал о клиенте
        client = null;
        return BigDecimal.ZERO;
    }

    //проверка наличия денег в банкомате
    private boolean isEnoughMoney(BigDecimal amount) {
        return money.compareTo(amount) >= 0;
    }


}
