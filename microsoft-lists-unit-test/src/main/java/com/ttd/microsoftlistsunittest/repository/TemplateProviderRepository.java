package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.projection.templateprovider.TemplateProviderProjection;

import java.util.List;

public interface TemplateProviderRepository {
    List<TemplateProviderProjection> findAllTemplateProviders();
}
