package com.ttd.microsoftlistsunittest.dto.account;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountProfileDto {
    private Integer accountId;
    private String avatar;
    private String email;
    private String firstName;
    private String lastName;
    private String company;
}
