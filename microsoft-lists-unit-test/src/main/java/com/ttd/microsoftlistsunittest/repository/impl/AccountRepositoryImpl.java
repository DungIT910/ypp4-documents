package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.domain.model.AccountStatus;
import com.ttd.microsoftlistsunittest.projection.account.AccountProfileProjection;
import com.ttd.microsoftlistsunittest.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<AccountProfileProjection> findAccountById(Integer id) {
        String sql = """
                    SELECT
                        acc.Id AS id,
                        acc.Avatar AS avatar,
                        acc.Email AS email,
                        acc.FirstName AS firstName,
                        acc.LastName AS lastName,
                        acc.Company AS company
                    FROM
                        Account acc
                    WHERE 
                        acc.Id = ?
                        AND acc.AccountStatus = ?
                """;

        List<AccountProfileProjection> results = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(AccountProfileProjection.class),
                id,
                AccountStatus.ACTIVE.name()
        );

        return results.stream().findFirst();
    }
}
