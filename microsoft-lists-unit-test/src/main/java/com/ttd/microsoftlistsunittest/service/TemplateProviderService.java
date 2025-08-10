package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.TemplateProvider;

import java.util.List;
import java.util.Optional;

public interface TemplateProviderService {
    List<TemplateProvider> findAll();

    Optional<TemplateProvider> findById(Integer id);

    int save(TemplateProvider provider);

    int update(TemplateProvider provider);

    int deleteById(Integer id);
}
