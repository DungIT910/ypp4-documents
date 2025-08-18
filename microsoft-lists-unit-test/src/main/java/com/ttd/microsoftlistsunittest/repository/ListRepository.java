package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;

import java.util.List;
import java.util.Optional;

public interface ListRepository {
    Optional<ListSummaryDto> findListSummaryByListIdAndAccountId(Integer id, Integer accountId);

    List<ListSummaryDto> findAllPersonalListsByAccountId(Integer accountId);

    List<ListSummaryDto> findAllFavoriteListsByAccountId(Integer accountId);

    List<ListSummaryDto> findAllRecentListsByAccountId(Integer accountId);
}
