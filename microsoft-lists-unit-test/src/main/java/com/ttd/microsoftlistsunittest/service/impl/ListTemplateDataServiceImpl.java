package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSampleDataDto;
import com.ttd.microsoftlistsunittest.repository.ListTemplateDataRepository;
import com.ttd.microsoftlistsunittest.service.ListTemplateDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTemplateDataServiceImpl implements ListTemplateDataService {
    private final ListTemplateDataRepository listTemplateDataRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(cacheNames = "listTemplateData", key = "#listTemplateId")
    public List<ListTemplateSampleDataDto> getListTemplateSampleData(Integer listTemplateId) {
        return listTemplateDataRepository.getListTemplateSampleData(listTemplateId).stream().toList();
    }
}
