package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.account.AccountProfileDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    void testGetAccountById_ReturnsExpectedData() {
        Integer accountId = 1;

        AccountProfileDto result = accountService.findAccountById(accountId);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }
}
