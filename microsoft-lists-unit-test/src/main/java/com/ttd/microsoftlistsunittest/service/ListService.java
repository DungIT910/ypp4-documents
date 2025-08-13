package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ListEntity;
import com.ttd.microsoftlistsunittest.dto.list.ListCreateDto;
import com.ttd.microsoftlistsunittest.dto.list.ListDisplayDto;

import java.util.List;
import java.util.Optional;

public interface ListService {

    ListDisplayDto findListById(Integer id);

    List<ListDisplayDto> getAllListsByAccountId(Integer accountId);

    List<ListDisplayDto> getFavoriteListsByAccountId(Integer accountId);

    List<ListDisplayDto> getRecentListsByAccountId(Integer accountId);

    int createList(ListCreateDto listCreateDto);

    int updateList(ListUpdateDto listUpdateDto);

    int deleteListById(Integer id);
}