package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.listtype.ListTypeDto;

import java.util.List;

public interface ListTypeRepository {
    List<ListTypeDto> findAllListTypes();
}
