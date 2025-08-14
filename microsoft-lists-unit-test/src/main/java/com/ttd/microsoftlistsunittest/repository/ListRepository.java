package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.projection.list.ListSummaryProjection;
import com.ttd.microsoftlistsunittest.projection.list.RecentListSummaryProjection;

import java.util.List;
import java.util.Optional;

public interface ListRepository {
    Optional<ListSummaryProjection> findListSummaryByListIdAndAccountId(Integer id, Integer accountId);

    List<ListSummaryProjection> findAllPersonalListsByAccountId(Integer accountId);

    List<ListSummaryProjection> findAllFavoriteListsByAccountId(Integer accountId);

    List<RecentListSummaryProjection> findAllRecentListsByAccountId(Integer accountId);
}
