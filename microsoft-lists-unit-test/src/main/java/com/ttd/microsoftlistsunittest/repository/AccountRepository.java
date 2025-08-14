package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.projection.account.AccountProfileProjection;

import java.util.Optional;

public interface AccountRepository {
    Optional<AccountProfileProjection> findAccountById(Integer id);
}
