package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.controller.WorkspaceController;
import com.ttd.microsoftlistsunittest.dto.workspace.WorkspaceDto;
import com.ttd.microsoftlistsunittest.service.WorkspaceService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
class WorkspaceServiceTest {
    @MockitoSpyBean
    private WorkspaceService workspaceService;

    @Test
    void testGetAllWorkspacesByAccountId_ReturnsExpectedData() {
        List<WorkspaceDto> workspaces = workspaceService.getAllWorkspacesByAccountId(2);
        assertThat(workspaces).hasSize(2);
    }
}