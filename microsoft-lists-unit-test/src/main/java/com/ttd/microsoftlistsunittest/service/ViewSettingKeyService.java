package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ViewSettingKey;

import java.util.List;
import java.util.Optional;

public interface ViewSettingKeyService {
    List<ViewSettingKey> findAll();
    Optional<ViewSettingKey> findById(Integer id);
    int save(ViewSettingKey key);
    int update(ViewSettingKey key);
    int deleteById(Integer id);
}
