package com.atm.sa;

import com.atm.sa.account.DefaultAccount;
import com.atm.sa.account.SavingAccount;
import com.atm.sa.atm.Atm;
import com.atm.sa.client.Client;
import com.atm.sa.exception.BusinessException;
import com.atm.sa.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class AtmUnitTest {

    @Test
    public void successfulGetMoney() {

        AccountService accountService = Mockito.mock(AccountService.class);
        Mockito.when(accountService.getAccount(1))
                .thenReturn(new DefaultAccount(BigDecimal.valueOf(500100)));
        Client client1 = new Client(1122, 1);
        Atm atm = new Atm(BigDecimal.valueOf(10100500), accountService);

        atm.atmStart(client1);
        atm.enterPinCode(1122);
        BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(1000));

        Assert.assertEquals(BigDecimal.valueOf(1000), rez);
        Assert.assertEquals(BigDecimal.valueOf(499100), client1.getAccount().getMoney());
        Assert.assertEquals(BigDecimal.valueOf(10099500), atm.getMoney());
    }

    @Test(expected = BusinessException.class)
    public void wrongPin() {

        AccountService accountService = Mockito.mock(AccountService.class);
        Mockito.when(accountService.getAccount(1))
                .thenReturn(new DefaultAccount(BigDecimal.valueOf(500100)));

        Client client1 = new Client(1122, 2);
        Atm atm = new Atm(BigDecimal.valueOf(10100500), accountService);

        atm.atmStart(client1);
        atm.enterPinCode(1111);
        atm.getMoneyForClient(BigDecimal.valueOf(1000));

    }

    @Test(expected = BusinessException.class)
    public void doublePin() {

        AccountService accountService = Mockito.mock(AccountService.class);
        Mockito.when(accountService.getAccount(1))
                .thenReturn(new DefaultAccount(BigDecimal.valueOf(500100)));

        Client client1 = new Client(1122, 3);
        Atm atm = new Atm(BigDecimal.valueOf(10100500), accountService);

        atm.atmStart(client1);
        atm.enterPinCode(1122);
        atm.enterPinCode(1111);
    }


    @Test
    public void notEnoughMoneyInAccount() {

        AccountService accountService = Mockito.mock(AccountService.class);
        Mockito.when(accountService.getAccount(1))
                .thenReturn(new DefaultAccount(BigDecimal.valueOf(100)));

        Client client1 = new Client(1122, 1);
        Atm atm = new Atm(BigDecimal.valueOf(10100500), accountService);

        atm.atmStart(client1);
        atm.enterPinCode(1122);
        BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(1000));

        Assert.assertEquals(BigDecimal.valueOf(0), rez);
        Assert.assertEquals(BigDecimal.valueOf(100), client1.getAccount().getMoney());
        Assert.assertEquals(BigDecimal.valueOf(10100500), atm.getMoney());
    }

    @Test
    public void notEnoughMoneyInAtm() {

        AccountService accountService = Mockito.mock(AccountService.class);
        Mockito.when(accountService.getAccount(1))
                .thenReturn(new DefaultAccount(BigDecimal.valueOf(1000)));

        Client client1 = new Client(1122, 1);
        Atm atm = new Atm(BigDecimal.valueOf(0), accountService);

        atm.atmStart(client1);
        atm.enterPinCode(1122);
        BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(1000));

        Assert.assertEquals(BigDecimal.valueOf(0), rez);
        Assert.assertEquals(BigDecimal.valueOf(1000), client1.getAccount().getMoney());
        Assert.assertEquals(BigDecimal.valueOf(0), atm.getMoney());
    }

    @Test
    public void cantGetMoneyInSavingAccount() {

        AccountService accountService = Mockito.mock(AccountService.class);
        Mockito.when(accountService.getAccount(2))
                .thenReturn(new SavingAccount(BigDecimal.valueOf(1000)));

        Client<SavingAccount> client = new Client<>(1122, 2);
        Atm atm = new Atm(BigDecimal.valueOf(100500), accountService);

        atm.atmStart(client);
        atm.enterPinCode(1122);
        BigDecimal rez = atm.getMoneyForClient(BigDecimal.valueOf(100));

        Assert.assertEquals(BigDecimal.valueOf(0), rez);
        Assert.assertEquals(BigDecimal.valueOf(1000), client.getAccount().getMoney());
        Assert.assertEquals(BigDecimal.valueOf(100500), atm.getMoney());
    }

}
