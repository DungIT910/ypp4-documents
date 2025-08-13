package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ListEntity;
import com.ttd.microsoftlistsunittest.dto.list.ListDisplayDto;
import com.ttd.microsoftlistsunittest.exception.MsListRuntimeException;
import com.ttd.microsoftlistsunittest.repository.ListRepository;
import com.ttd.microsoftlistsunittest.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {
    private final ListRepository listRepository;

    @Override
    public ListDisplayDto findListById(Integer id) {
        return listRepository.findById(id).orElseThrow(() -> new MsListRuntimeException("List not found with id: " + id));
    }

    @Override
    public List<ListDisplayDto> getAllListsByAccountId(Integer accountId) {
        return List.of();
    }

    @Override
    public List<ListDisplayDto> getFavoriteListsByAccountId(Integer accountId) {
        return List.of();
    }

    @Override
    public List<ListDisplayDto> getRecentListsByAccountId(Integer accountId) {
        return List.of();
    }

    @Override
    public int createList(ListEntity list) {
        return 0;
    }

    @Override
    public int updateList(ListEntity list) {
        return 0;
    }

    @Override
    public int deleteListById(Integer id) {
        return 0;
    }
}
