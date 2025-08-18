package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;
import com.ttd.microsoftlistsunittest.exception.MsListRuntimeException;
import com.ttd.microsoftlistsunittest.repository.ListRepository;
import com.ttd.microsoftlistsunittest.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {
    private final ListRepository listRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(cacheNames = "listSummary", key = "{#listId, #accountId}")
    public ListSummaryDto getListSummaryByListIdAndAccountId(Integer listId, Integer accountId) {
        return listRepository.findListSummaryByListIdAndAccountId(listId, accountId)
                .orElseThrow(() -> new MsListRuntimeException("List not found with id: " + listId));
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(cacheNames = "personalLists", key = "#accountId")
    public List<ListSummaryDto> getPersonalListsByAccountId(Integer accountId) {
        return listRepository.findAllPersonalListsByAccountId(accountId).stream().toList();
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(cacheNames = "favoriteLists", key = "#accountId")
    public List<ListSummaryDto> getFavoriteListsByAccountId(Integer accountId) {
        return listRepository.findAllFavoriteListsByAccountId(accountId).stream().toList();
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(cacheNames = "recentLists", key = "#accountId")
    public List<ListSummaryDto> getRecentListsByAccountId(Integer accountId) {
        return listRepository.findAllRecentListsByAccountId(accountId).stream().toList();
    }
}