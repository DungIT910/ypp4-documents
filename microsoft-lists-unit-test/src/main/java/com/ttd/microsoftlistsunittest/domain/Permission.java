package com.ttd.microsoftlistsunittest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private int id;
    private String permissionName;
    private String permissionCode;
    private String permissionDescription;
    private String icon;
}
