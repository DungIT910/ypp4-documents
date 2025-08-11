package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.SystemDataType;

import java.util.List;
import java.util.Optional;

public interface SystemDataTypeService {
    List<SystemDataType> findAll();

    Optional<SystemDataType> findById(Integer id);

    int save(SystemDataType systemDataType);

    int update(SystemDataType systemDataType);

    int deleteById(Integer id);
}
