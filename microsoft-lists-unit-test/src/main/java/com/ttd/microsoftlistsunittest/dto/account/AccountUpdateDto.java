package com.ttd.microsoftlistsunittest.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateDto {
    private String avatar;
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private String company;
    private String accountPassword;
}
