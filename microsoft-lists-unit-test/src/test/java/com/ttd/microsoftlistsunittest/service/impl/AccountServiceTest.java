package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.account.AccountProfileDto;
import com.ttd.microsoftlistsunittest.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
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

        AccountProfileDto apiResult = accountService.findAccountById(accountId);
        assertNotNull(apiResult);
        assertEquals("John", apiResult.getFirstName());
    }
}
