package com.atm.sa.account;

import java.math.BigDecimal;

public class Account {
    private BigDecimal money;

    public Account(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }

    //вычитание со счета клиента в банке
    public void subtractAmount(BigDecimal amount) {
        money = money.subtract(amount);
    }

}
