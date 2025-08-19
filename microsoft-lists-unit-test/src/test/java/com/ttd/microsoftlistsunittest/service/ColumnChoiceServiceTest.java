package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.model.ColumnChoiceContext;
import com.ttd.microsoftlistsunittest.dto.columnchoice.ColumnChoiceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
class ColumnChoiceServiceTest {
    @Autowired
    private ColumnChoiceService columnChoiceService;

    @Test
    void testGetColumnChoices_ReturnsExpectedData() {
        ColumnChoiceContext context = ColumnChoiceContext.LIST;
        Integer listColumnId = 1;

        List<ColumnChoiceDto> result = columnChoiceService.getColumnChoices(context, listColumnId);

        assertNotNull(result);
    }
}
