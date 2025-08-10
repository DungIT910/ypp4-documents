package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ViewType;

import java.util.List;
import java.util.Optional;

public interface ViewTypeService {
    List<ViewType> findAll();

    Optional<ViewType> findById(Integer id);

    int save(ViewType viewType);

    int update(ViewType viewType);

    int deleteById(Integer id);
}
