package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ListType;

import java.util.List;
import java.util.Optional;

public interface ListTypeService {
    List<ListType> findAll();

    Optional<ListType> findById(Integer id);

    int save(ListType listType);

    int update(ListType listType);

    int deleteById(Integer id);
}
