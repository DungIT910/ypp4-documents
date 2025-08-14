package com.ttd.microsoftlistsunittest.dto.list;

import com.ttd.microsoftlistsunittest.projection.list.ListSummaryProjection;
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

    public static ListSummaryDto from(ListSummaryProjection projection) {
        return ListSummaryDto.builder()
                .listId(projection.getListId())
                .listName(projection.getListName())
                .icon(projection.getIcon())
                .color(projection.getColor())
                .workspaceName(projection.getWorkspaceName())
                .isFavorite(projection.getIsFavorite())
                .build();
    }
}
