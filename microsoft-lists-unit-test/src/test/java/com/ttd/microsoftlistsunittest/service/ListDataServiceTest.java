package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.list.ListDataDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase
class ListDataServiceTest {
    @Autowired
    private ListDataService listDataService;

    @Test
    void testGetListData_ReturnsExpectedData() {
        Integer listId = 1;

        List<ListDataDto> result = listDataService.getListDataByListId(listId);
        assertNotNull(result);
        assertTrue(result.size() > 1);
    }
}

