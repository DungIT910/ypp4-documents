package com.ttd.microsoftlistsunittest.api;

import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;
import com.ttd.microsoftlistsunittest.dto.list.RecentListSummaryDto;
import com.ttd.microsoftlistsunittest.repository.ListRepository;
import com.ttd.microsoftlistsunittest.service.ListService;
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
class ListResourceTest {
    @Autowired
    private ListResource listResource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM FavoriteList");
        jdbcTemplate.execute("DELETE FROM RecentList");
        jdbcTemplate.execute("DELETE FROM WorkspaceMember");
        jdbcTemplate.execute("DELETE FROM List");
        jdbcTemplate.execute("DELETE FROM Workspace");
        jdbcTemplate.execute("DELETE FROM Account");

        jdbcTemplate.execute("INSERT INTO Account (Id, Email) VALUES (1, 'test@example.com')");

        jdbcTemplate.execute("INSERT INTO Workspace (Id, WorkspaceName, CreatedBy, IsPersonal) VALUES (1, 'Workspace A', 1, TRUE)");

        jdbcTemplate.execute("INSERT INTO List (Id, ListName, Icon, Color, WorkspaceId, ListStatus, CreatedBy) " +
                "VALUES (1, 'List 1', 'icon.png', 'blue', 1, 'active', 1)");

        jdbcTemplate.execute("INSERT INTO FavoriteList (Id, ListId, AccountId) VALUES (1, 1, 1)");

        jdbcTemplate.execute("INSERT INTO RecentList (Id, ListId, AccountId, AccessedAt) VALUES (1, 1, 1, NOW())");

        jdbcTemplate.execute("INSERT INTO WorkspaceMember (Id, WorkspaceId, AccountId, MemberStatus) VALUES (1, 1, 1, 'active')");
    }

    @Test
    void testGetListSummaryById_ReturnsExpectedData() {
        Integer listId = 1;
        Integer accountId = 1;

        ListSummaryDto apiResult = listResource.getListSummaryById(listId, accountId);
        assertNotNull(apiResult);
        assertEquals("List 1", apiResult.getListName());
    }

    @Test
    void testFindPersonalListsByAccountId_ReturnsExpectedLists() {
        Integer accountId = 1;

        List<ListSummaryDto> apiResults = listResource.getPersonalListsByAccountId(accountId);

        assertNotNull(apiResults);
        assertEquals(1, apiResults.size());

        var list = apiResults.get(0);
        assertEquals("List 1", list.getListName());
        assertEquals("Workspace A", list.getWorkspaceName());
        assertTrue(list.getIsFavorite());
    }

    @Test
    void testGetFavoriteListsByAccountId_ReturnsExpectedLists() {
        Integer accountId = 1;

        List<ListSummaryDto> apiResults = listResource.getFavoriteListsByAccountId(accountId);

        assertNotNull(apiResults);
        assertEquals(1, apiResults.size());

        var list = apiResults.get(0);
        assertEquals("List 1", list.getListName());
        assertEquals("Workspace A", list.getWorkspaceName());
        assertTrue(list.getIsFavorite());
    }

    @Test
    void testGetRecentListsByAccountId_ReturnsExpectedLists() {
        Integer accountId = 1;

        List<RecentListSummaryDto> apiResults = listResource.getRecentListsByAccountId(accountId);

        assertNotNull(apiResults);
        assertEquals(1, apiResults.size());

        var list = apiResults.get(0);
        assertEquals("List 1", list.getListName());
        assertEquals("Workspace A", list.getWorkspaceName());
    }

}
