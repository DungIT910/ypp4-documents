package com.ttd.microsoftlistsunittest.dto.list;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteListDto {
    private Integer listId;
    private String listName;
    private String icon;
    private String color;
    private Integer workspaceName;
}
