package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.listtype.ListTypeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
class ListTypeServiceTest {
    @Autowired
    private ListTypeService listTypeService;

    @Test
    void testGetAllListTypes_ReturnsExpectedData() {
        List<ListTypeDto> result = listTypeService.getAllListTypes();

        assertNotNull(result);
        assertEquals(5, result.size());
    }
}
