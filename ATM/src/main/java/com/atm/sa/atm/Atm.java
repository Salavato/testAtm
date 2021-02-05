package com.atm.sa.atm;

import com.atm.sa.account.Account;
import com.atm.sa.account.EmptyAccount;
import com.atm.sa.client.Client;
import com.atm.sa.exception.BusinessException;
import com.atm.sa.service.AccountService;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.function.Predicate;

@Data
public class Atm {
    private static final Client EMPTY_CLIENT = new Client(-1, -1, new EmptyAccount());
    @NotNull
    @PositiveOrZero
    private BigDecimal money;
    @NotNull(message = "Используйте вместо null EMPTY_CLIENT")
    private Client client; //клиент который вставил свою карту
    private int pinCode;
    private int pinCodeCount;
    private AccountService accountService;

    public Atm(BigDecimal money, AccountService accountService) {
        this.money = money;
        this.client = EMPTY_CLIENT;
        this.pinCodeCount = 0;
        this.accountService = accountService;
    }

    //клиент вставил карту и банкомат получил информацию о клиенте
    public void atmStart(Client client) {
        this.client = client;
        long id = client.getAccountId();
        Account account = accountService.getAccount(id);
        client.setAccount(account);
    }

    //проверка дублирования запроса
    public void enterPinCode(int pinCode) {
        if (pinCodeCount < 1) {
            this.pinCode = pinCode;
            pinCodeCount++;
        } else {
            throw new BusinessException("пин-код введен дважды!");
        }
    }


    //снятие наличных
    public BigDecimal getMoneyForClient(BigDecimal amount) {
        BigDecimal cash = BigDecimal.ZERO;

        if (!isPinValid(client1 -> client1.getPin() == pinCode)) {
            throw new BusinessException("Пин-код не верный");
        }

        if (client.getAccount().isEnoughMoney(amount) &&
                isEnoughMoneyAtm(bigDecimal -> money.compareTo(bigDecimal) >= 0, amount)) {
            money = money.subtract(amount);
            client.getAccount().subtractAmount(amount); //вычитаем со счета клиента в банке
            cash = amount;
        }
        // зануляем данные о клиенте, чтобы банкомат больше ничего не знал о клиенте
        client = EMPTY_CLIENT;
        //зануляем данные проверки дублирования
        pinCodeCount = 0;
        return cash;
    }

    //проверка наличия денег в банкомате
    private boolean isEnoughMoneyAtm(Predicate<BigDecimal> predicate, BigDecimal amount) {
        return predicate.test(amount);
        //money.compareTo(amount) >= 0;
    }

    //проверка пин-кода
    private boolean isPinValid(Predicate<Client> predicate) {
        return predicate.test(client);
    }

}
