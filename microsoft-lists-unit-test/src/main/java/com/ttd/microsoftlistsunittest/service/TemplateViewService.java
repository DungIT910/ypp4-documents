package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.TemplateView;

import java.util.List;
import java.util.Optional;

public interface TemplateViewService {
    List<TemplateView> findAll();

    Optional<TemplateView> findById(Integer id);

    List<TemplateView> findByListTemplateId(Integer listTemplateId);

    int save(TemplateView view);

    int update(TemplateView view);

    int deleteById(Integer id);
}
