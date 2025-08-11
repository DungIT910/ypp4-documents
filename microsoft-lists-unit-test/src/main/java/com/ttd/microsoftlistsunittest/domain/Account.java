package com.ttd.microsoftlistsunittest.domain;

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
public class Account {
    private Integer id;
    private String avatar;
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private String email;
    private String company;
    private String accountStatus;
    private String accountPassword;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
