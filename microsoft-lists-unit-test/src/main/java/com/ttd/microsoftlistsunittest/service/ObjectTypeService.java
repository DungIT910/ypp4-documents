package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ObjectType;

import java.util.List;
import java.util.Optional;

public interface ObjectTypeService {
    List<ObjectType> findAll();

    Optional<ObjectType> findById(Integer id);

    int save(ObjectType objectType);

    int update(ObjectType objectType);

    int deleteById(Integer id);
}
