package com.ttd.microsoftlistsunittest.dto.listtype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListTypeCardDto {
    private Integer id;
    private String icon;
    private String title;
    private String headerImage;
    private String listTypeDescription;
}
