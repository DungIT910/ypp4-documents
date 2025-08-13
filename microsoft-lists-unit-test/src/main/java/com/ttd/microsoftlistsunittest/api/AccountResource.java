package com.ttd.microsoftlistsunittest.api;

import com.ttd.microsoftlistsunittest.dto.account.AccounDisplayDto;
import com.ttd.microsoftlistsunittest.dto.account.AccountCreateDto;
import com.ttd.microsoftlistsunittest.dto.account.AccountUpdateDto;
import com.ttd.microsoftlistsunittest.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountResource {
    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public AccounDisplayDto getAccountById(@PathVariable Integer accountId) {
        return accountService.findAccountById(accountId);
    }

    @PostMapping
    public int createAccount(@RequestBody AccountCreateDto accountCreateDto) {
        return accountService.createAccount(accountCreateDto);
    }

    @PutMapping("/{accountId}")
    public int updateAccount(@PathVariable Integer accountId, @RequestBody AccountUpdateDto accountUpdateDto) {
        return accountService.updateAccount(accountId, accountUpdateDto);
    }
}
