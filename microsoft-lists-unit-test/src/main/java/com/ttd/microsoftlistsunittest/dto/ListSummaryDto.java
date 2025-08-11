package com.ttd.microsoftlistsunittest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListSummaryDto {
    private Integer id;
    private String color;
    private String icon;
    private String listName;
}
