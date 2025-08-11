package com.ttd.microsoftlistsunittest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DashboardDto {
    private AccountProfileDto accountProfileDto;
    private List<ListSummaryDto> recentLists;
    private List<ListSummaryDto> favoriteLists;
    private List<ListSummaryDto> myLists;
}
