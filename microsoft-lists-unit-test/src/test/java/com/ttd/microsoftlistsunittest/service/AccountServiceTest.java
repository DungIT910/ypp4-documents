package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.account.AccountProfileDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
class AccountServiceTest {
    @MockitoSpyBean
    private AccountService accountService;

    @Test
    void testGetAccountById_ReturnsExpectedData() {
        Integer accountId = 1;

        AccountProfileDto result = accountService.findAccountById(accountId);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }
}
