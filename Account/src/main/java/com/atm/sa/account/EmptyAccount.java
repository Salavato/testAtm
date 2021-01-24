package com.atm.sa.account;

import java.math.BigDecimal;

public class EmptyAccount extends Account {
    public EmptyAccount() {
        super(-1, BigDecimal.ZERO, false);
    }

    @Override
    public void subtractAmount(BigDecimal amount) {

    }

    @Override
    public boolean isEnoughMoney(BigDecimal amount) {
        return false;
    }
}
