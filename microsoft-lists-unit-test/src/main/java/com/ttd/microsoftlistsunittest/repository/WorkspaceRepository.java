package com.ttd.microsoftlistsunittest.repository;

import com.ttd.microsoftlistsunittest.projection.workspace.WorkspaceProjection;

import java.util.List;

public interface WorkspaceRepository {
    List<WorkspaceProjection> findAllWorkspacesByAccountId(Integer accountId);
}
