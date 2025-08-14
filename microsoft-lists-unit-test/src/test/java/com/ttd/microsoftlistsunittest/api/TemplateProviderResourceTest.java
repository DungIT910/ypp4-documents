package com.ttd.microsoftlistsunittest.api;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSummaryDto;
import com.ttd.microsoftlistsunittest.dto.templateprovider.TemplateProviderDto;
import com.ttd.microsoftlistsunittest.service.ListTemplateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Sql(scripts = "/schema.sql")
class TemplateProviderResourceTest {

    @Autowired
    private TemplateProviderResource templateProviderResource;

    @Autowired
    private ListTemplateService listTemplateService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM ListTemplate");
        jdbcTemplate.execute("DELETE FROM TemplateProvider");
        jdbcTemplate.execute("INSERT INTO TemplateProvider (Id, ProviderName) VALUES (1, 'Provider A')");
        jdbcTemplate.execute("INSERT INTO TemplateProvider (Id, ProviderName) VALUES (2, 'Provider B')");
        jdbcTemplate.execute("INSERT INTO ListTemplate (Id, Title, HeaderImage, TemplateDescription, Icon, Color, Summary, Feature, ProviderId) " +
                "VALUES (1, 'Template 1', 'header1.png', 'Description 1', 'icon1', 'blue', 'summary1', 'feature1', 1)");
        jdbcTemplate.execute("INSERT INTO ListTemplate (Id, Title, HeaderImage, TemplateDescription, Icon, Color, Summary, Feature, ProviderId) " +
                "VALUES (2, 'Template 2', 'header2.png', 'Description 2', 'icon2', 'red', 'summary2', 'feature2', 1)");
        jdbcTemplate.execute("INSERT INTO ListTemplate (Id, Title, HeaderImage, TemplateDescription, Icon, Color, Summary, Feature, ProviderId) " +
                "VALUES (3, 'Template 3', 'header3.png', 'Description 3', 'icon3', 'green', 'summary3', 'feature3', 2)");
    }

    @Test
    void testGetAllProviders_ReturnsExpectedData() {
        List<TemplateProviderDto> result = templateProviderResource.getAllProviders();

        assertNotNull(result);
        assertEquals(2, result.size());

        TemplateProviderDto first = result.get(0);
        assertEquals(1, first.getProviderId());
        assertEquals("Provider A", first.getProviderName());

        TemplateProviderDto second = result.get(1);
        assertEquals(2, second.getProviderId());
        assertEquals("Provider B", second.getProviderName());
    }

    @Test
    void testGetALlTemplatesByTemplateProviderId_ReturnsExpectedData() {
        Integer providerId = 1;
        List<ListTemplateSummaryDto> result = listTemplateService.getALlTemplatesByTemplateProviderId(providerId);

        assertNotNull(result);
        assertEquals(2, result.size());

        ListTemplateSummaryDto first = result.get(0);
        assertEquals(1, first.getListTemplateId());
        assertEquals("Template 1", first.getTitle());
        assertEquals("header1.png", first.getHeaderImage());
        assertEquals("Description 1", first.getTemplateDescription());

        ListTemplateSummaryDto second = result.get(1);
        assertEquals(2, second.getListTemplateId());
        assertEquals("Template 2", second.getTitle());
        assertEquals("header2.png", second.getHeaderImage());
        assertEquals("Description 2", second.getTemplateDescription());
    }
}
