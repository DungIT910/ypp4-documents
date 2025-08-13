package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.account.AccountCreateDto;
import com.ttd.microsoftlistsunittest.dto.account.AccountUpdateDto;
import com.ttd.microsoftlistsunittest.dto.account.AccounDisplayDto;

public interface AccountService {
    AccounDisplayDto findAccountById(Integer id);

    int createAccount(AccountCreateDto accountCreateDto);

    int updateAccount(Integer accountId, AccountUpdateDto accountUpdateDto);
}