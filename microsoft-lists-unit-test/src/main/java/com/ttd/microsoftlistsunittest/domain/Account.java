package com.ttd.microsoftlistsunittest.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @NonNull
    private Integer id;
    private String avatar;
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    @NonNull
    private String email;
    private String company;
    private String accountStatus;
    private String accountPassword;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
