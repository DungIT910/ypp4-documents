package com.ttd.microsoftlistsunittest.api;

import com.ttd.microsoftlistsunittest.dto.listtype.ListTypeDto;
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
class ListTypeResourceTest {

    @Autowired
    private ListTypeResource listTypeResource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM ListType");
        jdbcTemplate.execute("INSERT INTO ListType (Id, Title, Icon, ListTypeDescription, HeaderImage) " +
                "VALUES (1, 'List', 'icon-list', 'A standard list', 'header1.png')");
        jdbcTemplate.execute("INSERT INTO ListType (Id, Title, Icon, ListTypeDescription, HeaderImage) " +
                "VALUES (2, 'Board', 'icon-board', 'A kanban board', 'header2.png')");
    }

    @Test
    void testGetAllListTypes_ReturnsExpectedData() {
        List<ListTypeDto> result = listTypeResource.getAllListTypes();

        assertNotNull(result);
        assertEquals(2, result.size());

        ListTypeDto first = result.get(0);
        assertEquals("List", first.getTitle());
        assertEquals("icon-list", first.getIcon());
        assertEquals("A standard list", first.getListTypeDescription());
        assertEquals("header1.png", first.getHeaderImage());

        ListTypeDto second = result.get(1);
        assertEquals("Board", second.getTitle());
        assertEquals("icon-board", second.getIcon());
        assertEquals("A kanban board", second.getListTypeDescription());
        assertEquals("header2.png", second.getHeaderImage());
    }
}
