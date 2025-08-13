package com.ttd.microsoftlistsunittest.dto.list;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListDisplayDto {
    private Integer id;
    private String listName;
    private String icon;
    private String color;
    private Integer workspaceId;
    private Integer createdBy;
}
