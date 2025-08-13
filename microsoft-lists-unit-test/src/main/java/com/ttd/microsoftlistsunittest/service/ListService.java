package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.list.FavoriteListDto;
import com.ttd.microsoftlistsunittest.dto.list.ListDetailDto;
import com.ttd.microsoftlistsunittest.dto.list.MyListDto;
import com.ttd.microsoftlistsunittest.dto.list.RecentListDto;

import java.util.List;

public interface ListService {

    ListDetailDto findListDetailByListIdAndAccountId(Integer listId, Integer accountId);

    List<MyListDto> getAllListsByAccountId(Integer accountId);

    List<FavoriteListDto> getFavoriteListsByAccountId(Integer accountId);

    List<RecentListDto> getRecentListsByAccountId(Integer accountId);
}