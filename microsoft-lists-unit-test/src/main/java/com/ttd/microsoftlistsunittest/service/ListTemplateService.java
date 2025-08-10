package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ListTemplate;

import java.util.List;
import java.util.Optional;

public interface ListTemplateService {
    List<ListTemplate> findAll();

    Optional<ListTemplate> findById(Integer id);

    List<ListTemplate> findAllByProviderId(Integer providerId);

    int save(ListTemplate template);

    int update(ListTemplate template);

    int deleteById(Integer id);
}