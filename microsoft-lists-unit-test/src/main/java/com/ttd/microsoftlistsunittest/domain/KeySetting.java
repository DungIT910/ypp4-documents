package com.ttd.microsoftlistsunittest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KeySetting {
    private Integer id;
    private String icon;
    private String keyName;
    private String valueType;
    private Boolean isDefaultValue;
    private String valueOfDefault;
    private Boolean isShareLinkSetting;
}
