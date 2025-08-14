package com.ttd.microsoftlistsunittest.projection.listtype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListTypeProjection {
    private Integer listTypeId;
    private String title;
    private String icon;
    private String listTypeDescription;
    private String headerImage;
}
