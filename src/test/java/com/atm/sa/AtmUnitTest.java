package com.atm.sa;

import com.atm.sa.account.Account;
import com.atm.sa.atm.Atm;
import com.atm.sa.client.Client;
import com.atm.sa.exception.BusinessException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AtmUnitTest {

    @Test
    public void successfulGetMoney() {

        Account account = new Account(BigDecimal.valueOf(500100));
        Client client1 = new Client(1122, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500));

        atm.atmStart(client1);
        atm.enterPinCode(1122);
        BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(1000));

        Assert.assertEquals(BigDecimal.valueOf(1000), rez);
        Assert.assertEquals(BigDecimal.valueOf(499100), account.getMoney());
        Assert.assertEquals(BigDecimal.valueOf(10099500), atm.getMoney());
    }


    @Test(expected = BusinessException.class)
    public void wrongPin() {

        Account account = new Account(BigDecimal.valueOf(500100));
        Client client1 = new Client(1122, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500));

        atm.atmStart(client1);
        atm.enterPinCode(1111);
        atm.getMoneyForClient(BigDecimal.valueOf(1000));

    }

    @Test(expected = BusinessException.class)
    public void doublePin() {

        Account account = new Account(BigDecimal.valueOf(500100));
        Client client1 = new Client(1122, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500));

        atm.atmStart(client1);
        atm.enterPinCode(1122);
        atm.enterPinCode(1111);
    }

}
