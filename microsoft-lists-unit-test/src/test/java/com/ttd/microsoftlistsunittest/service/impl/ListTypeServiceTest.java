package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.listtype.ListTypeDto;
import com.ttd.microsoftlistsunittest.service.ListTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class ListTypeServiceTest {

    @Autowired
    private ListTypeService listTypeService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testGetAllListTypes_ReturnsExpectedData() {
        List<ListTypeDto> result = listTypeService.getAllListTypes();

        assertNotNull(result);
        assertEquals(5, result.size());
    }
}
