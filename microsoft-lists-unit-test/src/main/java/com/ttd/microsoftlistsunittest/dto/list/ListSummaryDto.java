package com.ttd.microsoftlistsunittest.dto.list;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ListSummaryDto {
    private Integer listId;
    private String listName;
    private String icon;
    private String color;
    private String workspaceName;
    private Boolean isFavorite;
}
