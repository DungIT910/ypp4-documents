package com.ttd.microsoftlistsunittest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemDataType {
    private Integer id;
    private String icon;
    private String dataTypeDescription;
    private String coverImg;
    private String displayName;
    private String dataTypeValue;
}
