package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.list.FavoriteListDto;
import com.ttd.microsoftlistsunittest.dto.list.ListDetailDto;
import com.ttd.microsoftlistsunittest.dto.list.MyListDto;
import com.ttd.microsoftlistsunittest.dto.list.RecentListDto;
import com.ttd.microsoftlistsunittest.exception.MsListRuntimeException;
import com.ttd.microsoftlistsunittest.repository.ListRepository;
import com.ttd.microsoftlistsunittest.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {
    private final ListRepository listRepository;

    @Override
    public ListDetailDto findListDetailByListIdAndAccountId(Integer listId, Integer accountId) {
        return listRepository.findListDetailByListIdAndAccountId(listId, accountId)
                .orElseThrow(() -> new MsListRuntimeException("List not found with id: " + listId));
    }

    @Override
    public List<MyListDto> getAllListsByAccountId(Integer accountId) {
        return List.of();
    }

    @Override
    public List<FavoriteListDto> getFavoriteListsByAccountId(Integer accountId) {
        return List.of();
    }

    @Override
    public List<RecentListDto> getRecentListsByAccountId(Integer accountId) {
        return List.of();
    }
}
