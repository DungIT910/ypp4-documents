package com.ttd.microsoftlistsunittest.api;

import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;
import com.ttd.microsoftlistsunittest.repository.ListRepository;
import com.ttd.microsoftlistsunittest.service.ListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Sql(scripts = "/schema.sql")
class ListResourceTest {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private ListService listService;

    @Autowired
    private ListResource listResource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM FavoriteList");
        jdbcTemplate.execute("DELETE FROM List");
        jdbcTemplate.execute("DELETE FROM Workspace");
        jdbcTemplate.execute("DELETE FROM Account");

        jdbcTemplate.execute("INSERT INTO Account (Id, Email) VALUES (1, 'test@example.com')");
        jdbcTemplate.execute("INSERT INTO Workspace (Id, WorkspaceName, CreatedBy) VALUES (1, 'Workspace A', 1)");
        jdbcTemplate.execute("INSERT INTO List (Id, ListName, Icon, Color, WorkspaceId, ListStatus, CreatedBy) VALUES (1, 'List 1', 'icon.png', 'blue', 1, 'active', 1)");
        jdbcTemplate.execute("INSERT INTO FavoriteList (Id, ListId, AccountId) VALUES (1, 1, 1)");
    }

    @Test
    void testGetListDetailsById_ReturnsExpectedData() {
        Integer listId = 1;
        Integer accountId = 1;

        var repoResult = listRepository.findListSummaryByListIdAndAccountId(listId, accountId);
        assertTrue(repoResult.isPresent());
        assertEquals("List 1", repoResult.get().getListName());

        ListSummaryDto serviceResult = listService.findListSummaryByListIdAndAccountId(listId, accountId);
        assertNotNull(serviceResult);
        assertEquals("List 1", serviceResult.getListName());

        ListSummaryDto apiResult = listResource.getListDetailsById(listId, accountId);
        assertNotNull(apiResult);
        assertEquals("List 1", apiResult.getListName());
    }
}
