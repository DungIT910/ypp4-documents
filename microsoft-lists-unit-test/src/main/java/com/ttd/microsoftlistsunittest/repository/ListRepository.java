package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;
import com.ttd.microsoftlistsunittest.dto.list.RecentListSummaryDto;

import java.util.List;
import java.util.Optional;

public interface ListRepository {
    Optional<ListSummaryDto> findListDetailByListIdAndAccountId(Integer id, Integer accountId);

    List<ListSummaryDto> findAllByAccountId(Integer accountId);

    List<ListSummaryDto> findAllFavoriteListsByAccountId(Integer accountId);

    List<RecentListSummaryDto> findAllRecentListsByAccountId(Integer accountId);
}
