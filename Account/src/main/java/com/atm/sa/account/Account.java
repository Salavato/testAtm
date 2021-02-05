package com.atm.sa.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {
    @Id
    private long id;
    private BigDecimal money;
    private boolean canWithdraw;

    public Account() {
    }

    public Account(BigDecimal money, boolean canWithdraw) {
        this.money = money;
        this.canWithdraw = canWithdraw;
    }

    //вычитание со счета клиента в банке
    public abstract void subtractAmount(BigDecimal amount);

    //проверка наличия денег на счете клиента
    public abstract boolean isEnoughMoney(BigDecimal amount);

}
