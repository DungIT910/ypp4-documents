package com.ttd.microsoftlistsunittest.projection.list;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public final class RecentListSummaryProjection extends ListSummaryProjection {
    private String accessedAt;
}
