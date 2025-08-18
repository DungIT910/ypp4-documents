package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSummaryDto;
import com.ttd.microsoftlistsunittest.repository.ListTemplateRepository;
import com.ttd.microsoftlistsunittest.service.ListTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTemplateServiceImpl implements ListTemplateService {
    private final ListTemplateRepository listTemplateRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(cacheNames = "listTemplatesByProvider", key = "#templateProviderId")
    public List<ListTemplateSummaryDto> getALlTemplatesByTemplateProviderId(Integer templateProviderId) {
        return listTemplateRepository.findALlTemplatesByTemplateProviderId(templateProviderId).stream().toList();
    }
}
