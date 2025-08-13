package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.domain.ListEntity;
import com.ttd.microsoftlistsunittest.dto.list.ListDisplayDto;

import java.util.List;
import java.util.Optional;

public interface ListRepository {
    Optional<ListDisplayDto> findById(Integer id);

    List<ListDisplayDto> findAllByAccountId(Integer accountId);

    List<ListDisplayDto> findAllFavoriteListsByAccountId(Integer accountId);

    List<ListDisplayDto> findAllRecentListsByAccountId(Integer accountId);

    int save(ListEntity list);

    int update(ListEntity list);

    int deleteById(Integer id);
}
