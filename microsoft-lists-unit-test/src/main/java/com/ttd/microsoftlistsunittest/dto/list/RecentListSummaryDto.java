package com.ttd.microsoftlistsunittest.dto.list;

import com.ttd.microsoftlistsunittest.projection.list.RecentListSummaryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class RecentListSummaryDto extends ListSummaryDto {
    private String accessedAt;

    public static RecentListSummaryDto from(RecentListSummaryProjection projection) {
        return RecentListSummaryDto.builder()
                .listId(projection.getListId())
                .listName(projection.getListName())
                .icon(projection.getIcon())
                .color(projection.getColor())
                .workspaceName(projection.getWorkspaceName())
                .isFavorite(projection.getIsFavorite())
                .accessedAt(projection.getAccessedAt())
                .build();
    }
}
