package com.atm.sa.account;

import java.math.BigDecimal;

public class SavingAccount extends Account {
    public SavingAccount(BigDecimal money) {
        super(money, false);
    }

    @Override
    public void subtractAmount(BigDecimal amount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEnoughMoney(BigDecimal amount) {
        return isCanWithdraw();
    }
}
