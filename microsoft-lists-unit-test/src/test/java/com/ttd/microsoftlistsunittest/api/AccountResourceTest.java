package com.ttd.microsoftlistsunittest.api;

import com.ttd.microsoftlistsunittest.dto.account.AccountProfileDto;
import com.ttd.microsoftlistsunittest.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountResource.class)
class AccountResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void testGetAccountById() throws Exception {
        AccountProfileDto mockDto = new AccountProfileDto();
        mockDto.setAccountId(1);
        mockDto.setFirstName("John");
        mockDto.setLastName("Doe");

        Mockito.when(accountService.findAccountById(1)).thenReturn(mockDto);

        mockMvc.perform(get("/api/v1/accounts/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }
}