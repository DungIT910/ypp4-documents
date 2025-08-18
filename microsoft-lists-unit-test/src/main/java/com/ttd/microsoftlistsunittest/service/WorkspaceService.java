package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.workspace.WorkspaceDto;

import java.util.List;

public interface WorkspaceService {
    List<WorkspaceDto> getAllWorkspacesByAccountId(Integer accountId);
}
