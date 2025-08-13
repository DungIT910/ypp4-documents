package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.account.AccountProfileDto;

public interface AccountService {
    AccountProfileDto findAccountById(Integer accountId);
}