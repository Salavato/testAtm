package com.atm.sa.account;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "default_accounts")
public class DefaultAccount extends Account {


    public DefaultAccount() {
    }

    public DefaultAccount(BigDecimal money) {
        super(money, true);
    }

    public void subtractAmount(BigDecimal amount) {
        setMoney(getMoney().subtract(amount));

    }

    public boolean isEnoughMoney(BigDecimal amount) {
        return isCanWithdraw() && getMoney().compareTo(amount) >= 0;
    }
}
