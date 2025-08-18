package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.templateprovider.TemplateProviderDto;
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
class TemplateProviderServiceTest {
    @Autowired
    private TemplateProviderService templateProviderService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testGetAllProviders_ReturnsExpectedData() {
        List<TemplateProviderDto> result = templateProviderService.getAllTemplateProviders();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
