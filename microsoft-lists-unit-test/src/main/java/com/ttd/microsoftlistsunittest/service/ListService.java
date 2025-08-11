package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ListEntity;

import java.util.List;
import java.util.Optional;

public interface ListService {
    List<ListEntity> findAll();

    Optional<ListEntity> findById(Integer id);

    int save(ListEntity list);

    int update(ListEntity list);

    int deleteById(Integer id);
}
