package com.ttd.microsoftlistsunittest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewType {
    private int id;
    private String title;
    private String headerImage;
    private String icon;
    private String viewTypeDescription;
}
