package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.account.AccountCreateDto;
import com.ttd.microsoftlistsunittest.dto.account.AccountUpdateDto;
import com.ttd.microsoftlistsunittest.dto.account.AccounDisplayDto;

import java.util.Optional;

public interface AccountRepository {
    Optional<AccounDisplayDto> findAccountById(Integer id);

    int save(AccountCreateDto accountCreateDto);

    int update(Integer accountId, AccountUpdateDto accountUpdateDto);
}
