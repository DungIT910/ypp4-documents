package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.listtype.ListTypeDto;
import com.ttd.microsoftlistsunittest.repository.ListTypeRepository;
import com.ttd.microsoftlistsunittest.service.ListTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTypeServiceImpl implements ListTypeService {
    private final ListTypeRepository listTypeRepository;

    @Override
    public List<ListTypeDto> getAllListTypes() {
        return listTypeRepository.findAllListTypes().stream()
                .map(ListTypeDto::from)
                .toList();
    }
}
