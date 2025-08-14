package com.ttd.microsoftlistsunittest.dto.account;

import com.ttd.microsoftlistsunittest.projection.account.AccountProfileProjection;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountProfileDto {
    private Integer accountId;
    private String avatar;
    private String email;
    private String firstName;
    private String lastName;
    private String company;

    public static AccountProfileDto from(AccountProfileProjection projection) {
        return AccountProfileDto.builder()
                .accountId(projection.getAccountId())
                .avatar(projection.getAvatar())
                .email(projection.getEmail())
                .firstName(projection.getFirstName())
                .lastName(projection.getLastName())
                .company(projection.getCompany())
                .build();
    }
}
