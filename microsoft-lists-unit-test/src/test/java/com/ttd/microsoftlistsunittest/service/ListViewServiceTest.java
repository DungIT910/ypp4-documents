package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.listview.ListViewDto;
import com.ttd.microsoftlistsunittest.dto.listview.ListViewSettingValueDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase
class ListViewServiceTest {
    @Autowired
    private ListViewService listViewService;

    @Test
    void testGetListViewsByListId_ReturnsExpectedData() {
        Integer listId = 1;
        List<ListViewDto> result = listViewService.getListViewsByListId(listId);

        assertNotNull(result);
        assertTrue(result.size() > 1);
    }

    @Test
    void testGetListViewSettingValues_ReturnsExpectedData() {
        Integer listViewId = 1;
        List<ListViewSettingValueDto> result = listViewService.getListViewSettingValues(listViewId);
        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }
}
