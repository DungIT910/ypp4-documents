package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;
import com.ttd.microsoftlistsunittest.dto.list.RecentListSummaryDto;

import java.util.List;

public interface ListService {

    ListSummaryDto getListSummaryByListIdAndAccountId(Integer listId, Integer accountId);

    List<ListSummaryDto> getPersonalListsByAccountId(Integer accountId);

    List<ListSummaryDto> getFavoriteListsByAccountId(Integer accountId);

    List<RecentListSummaryDto> getRecentListsByAccountId(Integer accountId);
}