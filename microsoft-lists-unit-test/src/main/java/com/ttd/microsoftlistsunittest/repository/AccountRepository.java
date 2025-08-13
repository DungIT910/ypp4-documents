package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.account.AccountProfileDto;

import java.util.Optional;

public interface AccountRepository {
    Optional<AccountProfileDto> findAccountById(Integer id);
}
