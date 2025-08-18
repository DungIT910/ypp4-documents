package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.account.AccountProfileDto;
import com.ttd.microsoftlistsunittest.exception.MsListRuntimeException;
import com.ttd.microsoftlistsunittest.repository.AccountRepository;
import com.ttd.microsoftlistsunittest.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(cacheNames = "accountProfile", key = "#accountId")
    public AccountProfileDto findAccountById(Integer accountId) {
        return accountRepository.findAccountById(accountId)
                .orElseThrow(() -> new MsListRuntimeException("No account found with Id: " + accountId));
    }
}