package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ColumnSettingObject;

import java.util.List;
import java.util.Optional;

public interface ColumnSettingObjectService {
    List<ColumnSettingObject> findAll();

    Optional<ColumnSettingObject> findById(Integer id);

    int save(ColumnSettingObject columnSettingObject);

    int update(ColumnSettingObject columnSettingObject);

    int deleteById(Integer id);
}
