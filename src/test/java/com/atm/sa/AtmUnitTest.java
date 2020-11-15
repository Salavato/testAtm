package com.atm.sa;

import com.atm.sa.account.Account;
import com.atm.sa.atm.Atm;
import com.atm.sa.client.Client;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AtmUnitTest {

    @Test
    public void successfulGetMoney() {

        Account account = new Account(BigDecimal.valueOf(500100));
        Client client1 = new Client(1122, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500));


        BigDecimal rez = BigDecimal.ZERO;
        atm.atmStart(client1);
        if (atm.isPinValid(1122)) {  // atm.isPinValid(client1.getPin());
            rez = atm.getAmount(BigDecimal.valueOf(1000));
        }
        System.out.println("Клиенту удалось снять: " + rez + " руб.");

        Assert.assertEquals(BigDecimal.valueOf(1000), rez);
        Assert.assertEquals(BigDecimal.valueOf(499100), account.getMoney());
        Assert.assertEquals(BigDecimal.valueOf(10099500), atm.getMoney());

    }


    @Test
    public void wrongPin() {

        Account account = new Account(BigDecimal.valueOf(500100));
        Client client1 = new Client(1122, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500));


        BigDecimal rez = BigDecimal.ZERO;
        atm.atmStart(client1);
        if (atm.isPinValid(1111)) {  // atm.isPinValid(client1.getPin());
            rez = atm.getAmount(BigDecimal.valueOf(1000));
        }
        System.out.println("Клиенту удалось снять: " + rez + " руб.");

        Assert.assertEquals(BigDecimal.valueOf(0), rez);
    }

}
