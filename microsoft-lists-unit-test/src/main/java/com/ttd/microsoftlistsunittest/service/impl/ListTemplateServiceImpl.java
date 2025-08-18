package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSummaryDto;
import com.ttd.microsoftlistsunittest.repository.ListTemplateRepository;
import com.ttd.microsoftlistsunittest.service.ListTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTemplateServiceImpl implements ListTemplateService {
    private final ListTemplateRepository listTemplateRepository;

    @Override
    public List<ListTemplateSummaryDto> getALlTemplatesByTemplateProviderId(Integer templateProviderId) {
        return listTemplateRepository.findALlTemplatesByTemplateProviderId(templateProviderId).stream()
                .map(ListTemplateSummaryDto::from)
                .toList();
    }
}
