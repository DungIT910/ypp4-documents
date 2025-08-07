package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.Account;
import com.ttd.microsoftlistsunittest.service.AccountService;
import com.ttd.microsoftlistsunittest.service.rowmapper.AccountRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final JdbcTemplate jdbcTemplate;
    private final AccountRowMapper accountRowMapper;

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM Account";
        return jdbcTemplate.query(sql, accountRowMapper);
    }

    @Override
    public Optional<Account> findById(Integer id) {
        String sql = "SELECT * FROM Account WHERE id = ?";
        List<Account> results = jdbcTemplate.query(sql, accountRowMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public int save(Account account) {
        String sql = """
                    INSERT INTO Account (avatar, firstName, lastName, dateBirth, email, company, accountStatus,
                                 accountPassword, createdAt, updatedAt)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                account.getAvatar(),
                account.getFirstName(),
                account.getLastName(),
                account.getDateBirth(),
                account.getEmail(),
                account.getCompany(),
                account.getAccountStatus(),
                account.getAccountPassword(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }

    @Override
    public int update(Account account) {
        String sql = """
                    UPDATE Account SET avatar = ?, firstName = ?, lastName = ?, dateBirth = ?, email = ?, company = ?, 
                        accountStatus = ?, accountPassword = ?, createdAt = ?, updatedAt = ? WHERE id = ?
                """;
        return jdbcTemplate.update(sql,
                account.getAvatar(),
                account.getFirstName(),
                account.getLastName(),
                account.getDateBirth(),
                account.getEmail(),
                account.getCompany(),
                account.getAccountStatus(),
                account.getAccountPassword(),
                account.getCreatedAt(),
                account.getUpdatedAt(),
                account.getId()
        );
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM Account WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}