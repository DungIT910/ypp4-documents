package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.listtemplate.ListTemplateSampleDataDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase
class ListTemplateDataServiceTest {
    @MockitoSpyBean
    private ListTemplateDataService listTemplateDataService;

    @Test
    void testGetListTemplateSampleData_ReturnsExpectedData() {
        Integer listTemplateId = 1;

        List<ListTemplateSampleDataDto> result = listTemplateDataService.getListTemplateSampleData(listTemplateId);
        assertNotNull(result);
        assertTrue(result.size() > 1);
    }
}
