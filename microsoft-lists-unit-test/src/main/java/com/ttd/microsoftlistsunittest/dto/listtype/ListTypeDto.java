package com.ttd.microsoftlistsunittest.dto.listtype;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListTypeDto {
    private Integer listTypeId;
    private String title;
    private String icon;
    private String listTypeDescription;
    private String headerImage;
}
