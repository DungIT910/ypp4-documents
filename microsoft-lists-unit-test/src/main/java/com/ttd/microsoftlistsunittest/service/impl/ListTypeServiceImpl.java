package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.listtype.ListTypeDto;
import com.ttd.microsoftlistsunittest.repository.ListTypeRepository;
import com.ttd.microsoftlistsunittest.service.ListTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTypeServiceImpl implements ListTypeService {
    private final ListTypeRepository listTypeRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(cacheNames = "listTypes", key = "'all'")
    public List<ListTypeDto> getAllListTypes() {
        return listTypeRepository.findAllListTypes().stream().toList();
    }
}
