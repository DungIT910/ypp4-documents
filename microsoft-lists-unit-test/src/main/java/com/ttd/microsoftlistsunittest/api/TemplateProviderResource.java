package com.ttd.microsoftlistsunittest.api;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSummaryDto;
import com.ttd.microsoftlistsunittest.dto.templateprovider.TemplateProviderDto;
import com.ttd.microsoftlistsunittest.service.ListTemplateService;
import com.ttd.microsoftlistsunittest.service.TemplateProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/template-providers")
@RequiredArgsConstructor
public class TemplateProviderResource {
    private final TemplateProviderService templateProviderService;
    private final ListTemplateService listTemplateService;

    @GetMapping
    public List<TemplateProviderDto> getAllProviders() {
        return templateProviderService.getAllTemplateProviders();
    }

    @GetMapping("/{templateProviderId}/templates")
    public List<ListTemplateSummaryDto> getALlTemplatesByTemplateProviderId(@PathVariable Integer templateProviderId) {
        return listTemplateService.getALlTemplatesByTemplateProviderId(templateProviderId);
    }
}
