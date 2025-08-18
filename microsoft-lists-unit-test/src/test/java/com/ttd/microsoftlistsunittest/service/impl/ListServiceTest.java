package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;
import com.ttd.microsoftlistsunittest.dto.list.RecentListSummaryDto;
import com.ttd.microsoftlistsunittest.service.ListService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class ListServiceTest {
    @MockitoSpyBean
    private ListService listService;

    @Test
    void testGetListSummaryById_ReturnsExpectedData() {
        Integer listId = 1;
        Integer accountId = 1;

        ListSummaryDto result = listService.getListSummaryByListIdAndAccountId(listId, accountId);
        assertNotNull(result);
        assertEquals("Project Tasks", result.getListName());
    }

    @Test
    void testFindPersonalListsByAccountId_ReturnsExpectedLists() {
        Integer accountId = 3;

        List<ListSummaryDto> results = listService.getPersonalListsByAccountId(accountId);

        assertNotNull(results);
        assertEquals(2, results.size());

        var list = results.get(0);
        assertEquals("Project Tasks", list.getListName());
        assertEquals("Workspace C", list.getWorkspaceName());
    }

    @Test
    void testGetFavoriteListsByAccountId_ReturnsExpectedLists() {
        Integer accountId = 3;

        List<ListSummaryDto> results = listService.getFavoriteListsByAccountId(accountId);

        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    void testGetRecentListsByAccountId_ReturnsExpectedLists() {
        Integer accountId = 3;

        List<RecentListSummaryDto> results = listService.getRecentListsByAccountId(accountId);

        assertNotNull(results);
        assertEquals(2, results.size());
    }
}
