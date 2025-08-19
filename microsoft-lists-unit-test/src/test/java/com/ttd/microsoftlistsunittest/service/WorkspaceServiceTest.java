package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.workspace.WorkspaceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
class WorkspaceServiceTest {
    @Autowired
    private WorkspaceService workspaceService;

    @Test
    void testGetAllWorkspacesByAccountId_ReturnsExpectedData() {
        List<WorkspaceDto> workspaces = workspaceService.getAllWorkspacesByAccountId(2);
        assertThat(workspaces).hasSize(2);
    }
}