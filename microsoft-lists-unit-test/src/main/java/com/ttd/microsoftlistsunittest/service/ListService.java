package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ListEntity;
import com.ttd.microsoftlistsunittest.dto.ListSummaryDto;

import java.util.List;
import java.util.Optional;

public interface ListService {
    List<ListEntity> findAll();

    Optional<ListEntity> findById(Integer id);

    List<ListSummaryDto> findAllByAccountId(Integer accountId);

    List<ListSummaryDto> findAllFavoriteListsByAccountId(Integer accountId);

    List<ListSummaryDto> findAllRecentListsByAccountId(Integer accountId);

    int save(ListEntity list);

    int update(ListEntity list);

    int deleteById(Integer id);
}
