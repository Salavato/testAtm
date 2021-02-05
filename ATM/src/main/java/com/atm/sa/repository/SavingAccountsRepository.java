package com.atm.sa.repository;

import com.atm.sa.account.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingAccountsRepository extends JpaRepository<SavingAccount, Long> {
}
