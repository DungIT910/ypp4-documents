package com.ttd.microsoftlistsunittest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountProfileDto {
    private Integer id;
    private String avatar;
    private String email;
    private String firstName;
    private String lastName;
    private String company;
}
