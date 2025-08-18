package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.templateprovider.TemplateProviderDto;

import java.util.List;

public interface TemplateProviderRepository {
    List<TemplateProviderDto> findAllTemplateProviders();
}
