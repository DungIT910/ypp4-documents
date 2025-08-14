package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.dto.account.AccountProfileDto;
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
    public Optional<AccountProfileDto> findAccountById(Integer id) {
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
                        AND acc.AccountStatus = 'active'
                """;

        List<AccountProfileDto> results = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(AccountProfileDto.class),
                id
        );

        return results.stream().findFirst();
    }
}
