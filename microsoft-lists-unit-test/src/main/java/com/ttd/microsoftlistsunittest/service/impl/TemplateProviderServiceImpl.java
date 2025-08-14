package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.templateprovider.TemplateProviderDto;
import com.ttd.microsoftlistsunittest.repository.TemplateProviderRepository;
import com.ttd.microsoftlistsunittest.service.TemplateProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemplateProviderServiceImpl implements TemplateProviderService {
    private final TemplateProviderRepository templateProviderRepository;

    @Override
    public List<TemplateProviderDto> getAllTemplateProviders() {
        return templateProviderRepository.findAllTemplateProviders().stream()
                .map(TemplateProviderDto::from)
                .collect(Collectors.toList());
    }
}
