package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.TrashItem;

import java.util.List;
import java.util.Optional;

public interface TrashItemService {
    List<TrashItem> findAll();
    Optional<TrashItem> findById(Integer id);
    int save(TrashItem item);
    int update(TrashItem item);
    int deleteById(Integer id);
}
