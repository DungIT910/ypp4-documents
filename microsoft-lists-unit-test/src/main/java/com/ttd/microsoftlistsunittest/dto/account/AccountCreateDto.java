package com.ttd.microsoftlistsunittest.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateDto {
    private String avatar;
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private String email;
    private String company;
    private String accountPassword;
}
