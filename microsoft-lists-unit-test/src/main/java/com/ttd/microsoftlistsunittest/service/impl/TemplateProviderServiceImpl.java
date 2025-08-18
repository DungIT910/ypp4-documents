package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.templateprovider.TemplateProviderDto;
import com.ttd.microsoftlistsunittest.repository.TemplateProviderRepository;
import com.ttd.microsoftlistsunittest.service.TemplateProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateProviderServiceImpl implements TemplateProviderService {
    private final TemplateProviderRepository templateProviderRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(cacheNames = "templateProviders", key = "'all'")
    public List<TemplateProviderDto> getAllTemplateProviders() {
        return templateProviderRepository.findAllTemplateProviders().stream()
                .toList();
    }
}
