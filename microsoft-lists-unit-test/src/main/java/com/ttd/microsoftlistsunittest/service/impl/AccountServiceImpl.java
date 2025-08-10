package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.Account;
import com.ttd.microsoftlistsunittest.service.AccountService;
import com.ttd.microsoftlistsunittest.service.rowmapper.AccountRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final JdbcTemplate jdbcTemplate;
    private final AccountRowMapper accountRowMapper;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$"
    );

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM Account";
        try {
            return jdbcTemplate.query(sql, accountRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving accounts", e);
        }
    }

    @Override
    public Optional<Account> findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or negative");
        }

        String sql = "SELECT * FROM Account WHERE Id = ?";
        try {
            List<Account> results = jdbcTemplate.query(sql, accountRowMapper, id);
            return results.stream().findFirst();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error finding account by ID: " + id, e);
        }
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        String sql = "SELECT * FROM Account WHERE Email = ?";
        try {
            List<Account> results = jdbcTemplate.query(sql, accountRowMapper, email);
            return results.stream().findFirst();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error finding account by email: " + email, e);
        }
    }

    @Override
    public int save(Account account) {
        validateAccount(account);
        validateEmailNotExists(account.getEmail());

        String sql = """
                    INSERT INTO Account (Avatar, FirstName, LastName, DateBirth, Email, Company, AccountStatus,
                                 AccountPassword, CreatedAt, UpdatedAt)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try {
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
        } catch (DataAccessException e) {
            throw new RuntimeException("Error saving account", e);
        }
    }

    @Override
    public int update(Account account) {
        if (account.getId() == null || account.getId() <= 0) {
            throw new IllegalArgumentException("Account ID cannot be null or negative for update");
        }

        validateAccount(account);
        validateAccountExists(account.getId());
        validateEmailNotExistsForUpdate(account.getEmail(), account.getId());

        String sql = """
                    UPDATE Account SET Avatar = ?, FirstName = ?, LastName = ?, DateBirth = ?, Email = ?, Company = ?, 
                        AccountStatus = ?, AccountPassword = ?, UpdatedAt = ? WHERE Id = ?
                """;
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    account.getAvatar(),
                    account.getFirstName(),
                    account.getLastName(),
                    account.getDateBirth(),
                    account.getEmail(),
                    account.getCompany(),
                    account.getAccountStatus(),
                    account.getAccountPassword(),
                    account.getUpdatedAt(),
                    account.getId()
            );

            if (rowsAffected == 0) {
                throw new RuntimeException("No account found with ID: " + account.getId());
            }

            return rowsAffected;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating account", e);
        }
    }

    @Override
    public int deleteById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or negative");
        }

        String sql = "UPDATE Account SET AccountStatus = 'inactive' WHERE Id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);

            if (rowsAffected == 0) {
                throw new RuntimeException("No account found with ID: " + id);
            }

            return rowsAffected;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting account", e);
        }
    }

    private void validateAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        if (account.getEmail() == null || account.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if (!EMAIL_PATTERN.matcher(account.getEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (account.getFirstName() == null || account.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }

        if (account.getLastName() == null || account.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }

        if (account.getAccountPassword() == null || account.getAccountPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
    }

    private void validateEmailNotExists(String email) {
        Optional<Account> existingAccount = findByEmail(email);
        if (existingAccount.isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }
    }

    private void validateEmailNotExistsForUpdate(String email, Integer id) {
        Optional<Account> existingAccount = findByEmail(email);
        if (existingAccount.isPresent() && !existingAccount.get().getId().equals(id)) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }
    }

    private void validateAccountExists(Integer id) {
        Optional<Account> account = findById(id);
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found with ID: " + id);
        }
    }
}