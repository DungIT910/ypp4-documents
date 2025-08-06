package com.ttd.microsoftlistsunittest.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountWorkspacePair {
    private Account account;
    private Workspace workspace;
}
