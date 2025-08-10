package com.ttd.microsoftlistsunittest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListTemplate {
    private Integer id;
    private String title;
    private String headerImage;
    private String templateDescription;
    private String icon;
    private String color;
    private String summary;
    private String feature;
    private Integer providerId;
}