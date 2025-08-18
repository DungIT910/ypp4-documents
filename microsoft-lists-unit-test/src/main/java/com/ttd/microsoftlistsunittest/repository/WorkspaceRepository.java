package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.dto.workspace.WorkspaceDto;

import java.util.List;

public interface WorkspaceRepository {
    List<WorkspaceDto> findAllWorkspacesByAccountId(Integer accountId);
}
