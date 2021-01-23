package com.atm.sa.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public abstract class Account {
    private BigDecimal money;
    private boolean canWithdraw;

    //вычитание со счета клиента в банке
    public abstract void subtractAmount(BigDecimal amount);

    //проверка наличия денег на счете клиента
    public abstract boolean isEnoughMoney(BigDecimal amount);

}
