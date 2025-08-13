package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.list.FavoriteListDto;
import com.ttd.microsoftlistsunittest.dto.list.ListDetailDto;
import com.ttd.microsoftlistsunittest.dto.list.MyListDto;
import com.ttd.microsoftlistsunittest.dto.list.RecentListDto;

import java.util.List;
import java.util.Optional;

public interface ListRepository {
    Optional<ListDetailDto> findListDetailByListIdAndAccountId(Integer id, Integer accountId);

    List<MyListDto> findAllByAccountId(Integer accountId);

    List<FavoriteListDto> findAllFavoriteListsByAccountId(Integer accountId);

    List<RecentListDto> findAllRecentListsByAccountId(Integer accountId);
}
