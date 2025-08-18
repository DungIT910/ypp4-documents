package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSummaryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
class ListTemplateServiceTest {

    @Autowired
    private ListTemplateService listTemplateService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testGetALlTemplatesByTemplateProviderId_ReturnsExpectedData() {
        Integer providerId = 1;
        List<ListTemplateSummaryDto> result = listTemplateService.getALlTemplatesByTemplateProviderId(providerId);

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
