package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.Account;
import com.ttd.microsoftlistsunittest.service.rowmapper.AccountRowMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private AccountRowMapper accountRowMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testFindAll_ShouldReturnAccounts() {
        Account mockAccount = createSampleAccount();
        List<Account> mockList = List.of(mockAccount);

        when(jdbcTemplate.query(anyString(), eq(accountRowMapper))).thenReturn(mockList);

        List<Account> result = accountService.findAll();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(jdbcTemplate).query("SELECT * FROM Account", accountRowMapper);
    }

    @Test
    void testFindAll_ShouldReturnEmptyList_WhenNoAccounts() {
        when(jdbcTemplate.query(anyString(), eq(accountRowMapper))).thenReturn(Collections.emptyList());

        List<Account> result = accountService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindById_ShouldReturnAccount() {
        Account mockAccount = createSampleAccount();
        mockAccount.setId(1);

        when(jdbcTemplate.query(anyString(), eq(accountRowMapper), eq(1))).thenReturn(List.of(mockAccount));

        Optional<Account> result = accountService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    void testFindById_ShouldReturnEmpty_WhenAccountNotFound() {
        when(jdbcTemplate.query(anyString(), eq(accountRowMapper), eq(1))).thenReturn(Collections.emptyList());

        Optional<Account> result = accountService.findById(1);

        assertFalse(result.isPresent());
    }

    @Test
    void testFindById_ShouldThrowException_WhenIdIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.findById(null));

        assertEquals("ID cannot be null or negative", exception.getMessage());
    }

    @Test
    void testFindById_ShouldThrowException_WhenIdIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.findById(-1));

        assertEquals("ID cannot be null or negative", exception.getMessage());
    }

    @Test
    void testFindByEmail_ShouldReturnAccount() {
        Account mockAccount = createSampleAccount();
        when(jdbcTemplate.query(anyString(), eq(accountRowMapper), eq("john.doe@example.com")))
                .thenReturn(List.of(mockAccount));

        Optional<Account> result = accountService.findByEmail("john.doe@example.com");

        assertTrue(result.isPresent());
        assertEquals("john.doe@example.com", result.get().getEmail());
    }

    @Test
    void testFindByEmail_ShouldThrowException_WhenEmailIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.findByEmail(null));

        assertEquals("Email cannot be null or empty", exception.getMessage());
    }

    @Test
    void testFindByEmail_ShouldThrowException_WhenEmailIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.findByEmail(""));

        assertEquals("Email cannot be null or empty", exception.getMessage());
    }

    @Test
    void testSave_ShouldInsertAccount() {
        Account account = createSampleAccount();

        when(jdbcTemplate.query(eq("SELECT * FROM Account WHERE Email = ?"),
                eq(accountRowMapper), eq(account.getEmail()))).thenReturn(Collections.emptyList());

        when(jdbcTemplate.update(anyString(),
                anyString(), anyString(), anyString(), any(LocalDate.class), anyString(),
                anyString(), anyString(), anyString(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(1);

        int result = accountService.save(account);

        assertEquals(1, result);
    }

    @Test
    void testSave_ShouldThrowException_WhenAccountIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.save(null));

        assertEquals("Account cannot be null", exception.getMessage());
    }

    @Test
    void testSave_ShouldThrowException_WhenEmailIsNull() {
        Account account = createSampleAccount();
        account.setEmail(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.save(account));

        assertEquals("Email cannot be null or empty", exception.getMessage());
    }

    @Test
    void testSave_ShouldThrowException_WhenEmailFormatIsInvalid() {
        Account account = createSampleAccount();
        account.setEmail("invalid-email");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.save(account));

        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    void testSave_ShouldThrowException_WhenEmailAlreadyExists() {
        Account account = createSampleAccount();
        Account existingAccount = createSampleAccount();
        existingAccount.setId(999);

        when(jdbcTemplate.query(eq("SELECT * FROM Account WHERE Email = ?"),
                eq(accountRowMapper), eq(account.getEmail()))).thenReturn(List.of(existingAccount));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.save(account));

        assertEquals("Email already exists: " + account.getEmail(), exception.getMessage());
    }

    @Test
    void testSave_ShouldThrowException_WhenFirstNameIsNull() {
        Account account = createSampleAccount();
        account.setFirstName(null);

        when(jdbcTemplate.query(eq("SELECT * FROM Account WHERE Email = ?"),
                eq(accountRowMapper), eq(account.getEmail()))).thenReturn(Collections.emptyList());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.save(account));

        assertEquals("First name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testSave_ShouldThrowException_WhenPasswordIsTooShort() {
        Account account = createSampleAccount();
        account.setAccountPassword("123");

        when(jdbcTemplate.query(eq("SELECT * FROM Account WHERE Email = ?"),
                eq(accountRowMapper), eq(account.getEmail()))).thenReturn(Collections.emptyList());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.save(account));

        assertEquals("Password must be at least 6 characters long", exception.getMessage());
    }

    @Test
    void testUpdate_ShouldUpdateAccount() {
        Account account = createSampleAccount();
        account.setId(1);

        when(jdbcTemplate.query(eq("SELECT * FROM Account WHERE Id = ?"),
                eq(accountRowMapper), eq(1))).thenReturn(List.of(account));

        when(jdbcTemplate.query(eq("SELECT * FROM Account WHERE Email = ?"),
                eq(accountRowMapper), eq(account.getEmail()))).thenReturn(List.of(account));

        when(jdbcTemplate.update(anyString(),
                anyString(), anyString(), anyString(), any(LocalDate.class), anyString(),
                anyString(), anyString(), anyString(), any(LocalDateTime.class), anyInt()))
                .thenReturn(1);

        int result = accountService.update(account);

        assertEquals(1, result);
    }

    @Test
    void testUpdate_ShouldThrowException_WhenIdIsNull() {
        Account account = createSampleAccount();
        account.setId(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.update(account));

        assertEquals("Account ID cannot be null or negative for update", exception.getMessage());
    }

    @Test
    void testUpdate_ShouldThrowException_WhenEmailExistsForOtherAccount() {
        Account account = createSampleAccount();
        account.setId(1);

        Account otherAccount = createSampleAccount();
        otherAccount.setId(2);

        when(jdbcTemplate.query(eq("SELECT * FROM Account WHERE Id = ?"),
                eq(accountRowMapper), eq(1))).thenReturn(List.of(account));

        when(jdbcTemplate.query(eq("SELECT * FROM Account WHERE Email = ?"),
                eq(accountRowMapper), eq(account.getEmail()))).thenReturn(List.of(otherAccount));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.update(account));

        assertEquals("Email already exists: " + account.getEmail(), exception.getMessage());
    }

    @Test
    void testDeleteById_ShouldDelete() {
        Account account = createSampleAccount();
        account.setId(1);

        when(jdbcTemplate.update(eq("UPDATE Account SET AccountStatus = 'inactive' WHERE Id = ?"), eq(1)))
                .thenReturn(1);

        int result = accountService.deleteById(1);

        assertEquals(1, result);
        verify(jdbcTemplate).update("UPDATE Account SET AccountStatus = 'inactive' WHERE Id = ?", 1);
    }

    @Test
    void testDeleteById_ShouldThrowException_WhenIdIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.deleteById(null));

        assertEquals("ID cannot be null or negative", exception.getMessage());
    }

    @Test
    void testDeleteById_ShouldThrowException_WhenIdIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> accountService.deleteById(-1));

        assertEquals("ID cannot be null or negative", exception.getMessage());
    }

    private Account createSampleAccount() {
        Account account = new Account();
        account.setAvatar("avatar.jpg");
        account.setFirstName("John");
        account.setLastName("Doe");
        account.setDateBirth(LocalDate.of(1990, 1, 1));
        account.setEmail("john.doe@example.com");
        account.setCompany("Example Corp");
        account.setAccountStatus("active");
        account.setAccountPassword("securepassword");
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        return account;
    }
}