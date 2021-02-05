package com.atm.sa.service;

import com.atm.sa.account.Account;
import com.atm.sa.account.DefaultAccount;
import com.atm.sa.account.EmptyAccount;
import com.atm.sa.account.SavingAccount;
import com.atm.sa.repository.DefaultAccountsRepository;
import com.atm.sa.repository.SavingAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private DefaultAccountsRepository defaultAccountsRepository;

    @Autowired
    private SavingAccountsRepository savingAccountsRepository;

    public Account getAccount(long id) {
        Optional<DefaultAccount> defaultAccount = defaultAccountsRepository.findById(id);
        if (defaultAccount.isPresent()) {
            return defaultAccount.get();
        }

        Optional<SavingAccount> savingAccount = savingAccountsRepository.findById(id);
        if (savingAccount.isPresent()) {
            return savingAccount.get();
        }

        return emptyAccount();
    }

    private Account emptyAccount() {
        return new EmptyAccount();
    }

}
