package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.KeySetting;

import java.util.List;
import java.util.Optional;

public interface KeySettingService {
    List<KeySetting> findAll();
    Optional<KeySetting> findById(Integer id);
    int save(KeySetting keySetting);
    int update(KeySetting keySetting);
    int deleteById(Integer id);
}
