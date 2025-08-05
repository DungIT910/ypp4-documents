package com.ttd.microsoftlistsunittest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Account {
    private int id;
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

