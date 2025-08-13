package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.dto.account.AccountCreateDto;
import com.ttd.microsoftlistsunittest.dto.account.AccountUpdateDto;
import com.ttd.microsoftlistsunittest.dto.account.AccounDisplayDto;
import com.ttd.microsoftlistsunittest.repository.AccountRepository;
import com.ttd.microsoftlistsunittest.rowmapper.dto.AccountDisplayDtoRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final JdbcTemplate jdbcTemplate;
    private final AccountDisplayDtoRowMapper profileDtoRowMapper;

    @Override
    public Optional<AccounDisplayDto> findAccountById(Integer id) {
        String sql = """
                    SELECT
                        acc.Id,
                        acc.Avatar,
                        acc.Email,
                        acc.FirstName,
                        acc.LastName,
                        acc.Company
                    FROM
                        Account AS acc
                    WHERE 
                        acc.Id = @AccountId 
                        AND acc.AccountStatus = 'active'
                """;
        List<AccounDisplayDto> results = jdbcTemplate.query(sql, profileDtoRowMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public int save(AccountCreateDto accountCreateDto) {
        String sql = """
                    INSERT INTO Account (Avatar, FirstName, LastName, DateBirth, Email, Company, AccountStatus,
                                 AccountPassword, CreatedAt, UpdatedAt)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                accountCreateDto.getAvatar(),
                accountCreateDto.getFirstName(),
                accountCreateDto.getLastName(),
                accountCreateDto.getDateBirth(),
                accountCreateDto.getEmail(),
                accountCreateDto.getCompany(),
                accountCreateDto.getAccountPassword(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    @Override
    public int update(Integer accountId, AccountUpdateDto dto) {
        String sql = """
        UPDATE Account SET Avatar = ?, FirstName = ?, LastName = ?, DateBirth = ?, Company = ?, 
            , AccountPassword = ?, UpdatedAt = ? WHERE Id = ?
        """;
        return jdbcTemplate.update(sql,
                dto.getAvatar(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getDateBirth(),
                dto.getCompany(),
                dto.getAccountPassword(),
                LocalDateTime.now(),
                accountId
        );
    }
}
