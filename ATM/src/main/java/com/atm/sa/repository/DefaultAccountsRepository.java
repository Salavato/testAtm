package com.atm.sa.repository;

import com.atm.sa.account.DefaultAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultAccountsRepository extends JpaRepository<DefaultAccount, Long> {
}
