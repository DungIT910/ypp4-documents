package com.ttd.microsoftlistsunittest.dto.list;

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
}
