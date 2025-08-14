package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;
import com.ttd.microsoftlistsunittest.dto.list.RecentListSummaryDto;
import com.ttd.microsoftlistsunittest.exception.MsListRuntimeException;
import com.ttd.microsoftlistsunittest.repository.ListRepository;
import com.ttd.microsoftlistsunittest.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {
    private final ListRepository listRepository;

    @Override
    public ListSummaryDto getListSummaryByListIdAndAccountId(Integer listId, Integer accountId) {
        return listRepository.findListSummaryByListIdAndAccountId(listId, accountId)
                .map(ListSummaryDto::from)
                .orElseThrow(() -> new MsListRuntimeException("List not found with id: " + listId));
    }

    @Override
    public List<ListSummaryDto> getPersonalListsByAccountId(Integer accountId) {
        return listRepository.findAllPersonalListsByAccountId(accountId).stream()
                .map(ListSummaryDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ListSummaryDto> getFavoriteListsByAccountId(Integer accountId) {
        return listRepository.findAllFavoriteListsByAccountId(accountId).stream()
                .map(ListSummaryDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecentListSummaryDto> getRecentListsByAccountId(Integer accountId) {
        return listRepository.findAllRecentListsByAccountId(accountId).stream()
                .map(RecentListSummaryDto::from)
                .collect(Collectors.toList());
    }
}
