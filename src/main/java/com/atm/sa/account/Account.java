package com.atm.sa.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Account {
    private BigDecimal money;

    //вычитание со счета клиента в банке
    public void subtractAmount(BigDecimal amount) {
        money = money.subtract(amount);
    }

    //проверка наличия денег на счете клиента
    public boolean isEnoughMoney(BigDecimal amount) {
        return money.compareTo(amount) >= 0;
    }


}
