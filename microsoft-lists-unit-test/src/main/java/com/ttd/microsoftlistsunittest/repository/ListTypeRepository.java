package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.projection.listtype.ListTypeProjection;

import java.util.List;

public interface ListTypeRepository {
    List<ListTypeProjection> findAllListTypes();
}
