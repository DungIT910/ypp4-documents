package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.account.AccountCreateDto;
import com.ttd.microsoftlistsunittest.dto.account.AccountUpdateDto;
import com.ttd.microsoftlistsunittest.dto.account.AccounDisplayDto;
import com.ttd.microsoftlistsunittest.exception.MsListRuntimeException;
import com.ttd.microsoftlistsunittest.repository.AccountRepository;
import com.ttd.microsoftlistsunittest.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public AccounDisplayDto findAccountById(Integer id) {
        return accountRepository.findAccountById(id)
                .orElseThrow(() -> new MsListRuntimeException("No account found with ID: " + id));
    }

    @Override
    public int createAccount(AccountCreateDto accountCreateDto) {
        return accountRepository.save(accountCreateDto);
    }

    @Override
    public int updateAccount(Integer accountId, AccountUpdateDto accountUpdateDto) {
        int rowsAffected = accountRepository.update(accountId, accountUpdateDto);

        if (rowsAffected == 0) {
            throw new MsListRuntimeException("No account found with ID: " + accountId);
        }

        return rowsAffected;
    }
}