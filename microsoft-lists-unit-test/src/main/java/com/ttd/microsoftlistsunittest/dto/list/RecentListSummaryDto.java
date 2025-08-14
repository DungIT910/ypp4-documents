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
        ListSummaryDto base = ListSummaryDto.from(projection);
        return RecentListSummaryDto.builder()
                .listId(base.getListId())
                .listName(base.getListName())
                .icon(base.getIcon())
                .color(base.getColor())
                .workspaceName(base.getWorkspaceName())
                .isFavorite(base.getIsFavorite())
                .accessedAt(projection.getAccessedAt())
                .build();
    }
}
