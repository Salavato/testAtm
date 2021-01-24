package com.atm.sa;

import com.atm.sa.account.Account;
import com.atm.sa.account.DefaultAccount;
import com.atm.sa.account.SavingAccount;
import com.atm.sa.atm.Atm;
import com.atm.sa.client.Client;
import com.atm.sa.exception.BusinessException;
import com.atm.sa.service.AccountService;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AtmUnitTest {

    @Test
    public void successfulGetMoney() {

        Account account = new DefaultAccount(BigDecimal.valueOf(500100));
        Client client1 = new Client(1122, 1, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500), new AccountService());

        atm.atmStart(client1);
        atm.enterPinCode(1122);
        BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(1000));

        Assert.assertEquals(BigDecimal.valueOf(1000), rez);
        Assert.assertEquals(BigDecimal.valueOf(499100), account.getMoney());
        Assert.assertEquals(BigDecimal.valueOf(10099500), atm.getMoney());
    }


    @Test(expected = BusinessException.class)
    public void wrongPin() {

        Account account = new DefaultAccount(BigDecimal.valueOf(500100));
        Client client1 = new Client(1122, 2, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500), new AccountService());

        atm.atmStart(client1);
        atm.enterPinCode(1111);
        atm.getMoneyForClient(BigDecimal.valueOf(1000));

    }

    @Test(expected = BusinessException.class)
    public void doublePin() {

        Account account = new DefaultAccount(BigDecimal.valueOf(500100));
        Client client1 = new Client(1122, 3, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500), new AccountService());

        atm.atmStart(client1);
        atm.enterPinCode(1122);
        atm.enterPinCode(1111);
    }


    @Test
    public void notEnoughMoneyInAccount() {
        Account account = new DefaultAccount(BigDecimal.valueOf(100));
        Client client1 = new Client(1122, 1, account);
        Atm atm = new Atm(BigDecimal.valueOf(10100500), new AccountService());

        atm.atmStart(client1);
        atm.enterPinCode(1122);
        BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(1000));

        Assert.assertEquals(BigDecimal.valueOf(0), rez);
        Assert.assertEquals(BigDecimal.valueOf(100), account.getMoney());
        Assert.assertEquals(BigDecimal.valueOf(10100500), atm.getMoney());


    }

    @Test
    public void notEnoughMoneyInAtm() {
        Account account = new DefaultAccount(BigDecimal.valueOf(500100));
        Client client1 = new Client(1122, 1, account);
        Atm atm = new Atm(BigDecimal.valueOf(1010), new AccountService());

        atm.atmStart(client1);
        atm.enterPinCode(1122);
        BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(2000));

        Assert.assertEquals(BigDecimal.valueOf(0), rez);
        Assert.assertEquals(BigDecimal.valueOf(500100), account.getMoney());
        Assert.assertEquals(BigDecimal.valueOf(1010), atm.getMoney());
    }

    @Test
    public void cantGetMoneyInSavingAccount() {
        SavingAccount account = new SavingAccount(BigDecimal.valueOf(1000));
        Client<SavingAccount> client = new Client<>(1122, 1, account);
        Atm atm = new Atm(BigDecimal.valueOf(100500), new AccountService());

        atm.atmStart(client);
        atm.enterPinCode(1122);
        BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(100));

        Assert.assertEquals(BigDecimal.valueOf(0), rez);
        Assert.assertEquals(BigDecimal.valueOf(1000), account.getMoney());
        Assert.assertEquals(BigDecimal.valueOf(100500), atm.getMoney());
    }

}
