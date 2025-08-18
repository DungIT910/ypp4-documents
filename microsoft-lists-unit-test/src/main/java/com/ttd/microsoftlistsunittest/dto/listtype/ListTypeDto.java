package com.ttd.microsoftlistsunittest.dto.listtype;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListTypeDto {
    private Integer listTypeId;
    private String title;
    private String icon;
    private String listTypeDescription;
    private String headerImage;
}
