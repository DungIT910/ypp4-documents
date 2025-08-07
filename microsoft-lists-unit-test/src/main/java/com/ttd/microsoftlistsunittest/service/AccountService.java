package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> findAll();

    Optional<Account> findById(Integer id);

    int save(Account account);

    int update(Account account);

    int deleteById(Integer id);
}