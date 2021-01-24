package com.atm.sa.account;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "saving_accounts")
public class SavingAccount extends Account {

    public SavingAccount() {

    }

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
