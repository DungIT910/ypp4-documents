package com.ttd.microsoftlistsunittest.api;

import com.ttd.microsoftlistsunittest.dto.account.AccountProfileDto;
import com.ttd.microsoftlistsunittest.repository.AccountRepository;
import com.ttd.microsoftlistsunittest.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Sql(scripts = "/schema.sql")
class AccountResourceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountResource accountResource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM Account");
        jdbcTemplate.execute("INSERT INTO Account (Id, Avatar, FirstName, LastName, DateBirth, Email, Company, AccountStatus, AccountPassword) " +
                "VALUES (1, 'avatar.png', 'John', 'Doe', '1990-01-01', 'john.doe@example.com', 'ExampleCorp', 'active', 'password')");
    }

    @Test
    void testGetAccountById_ReturnsExpectedData() {
        Integer accountId = 1;

        var repoResult = accountRepository.findAccountById(accountId);
        assertTrue(repoResult.isPresent());
        assertEquals("John", repoResult.get().getFirstName());

        AccountProfileDto serviceResult = accountService.findAccountById(accountId);
        assertNotNull(serviceResult);
        assertEquals("John", serviceResult.getFirstName());

        AccountProfileDto apiResult = accountResource.getAccountById(accountId);
        assertNotNull(apiResult);
        assertEquals("John", apiResult.getFirstName());
    }
}
