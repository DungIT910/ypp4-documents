package com.ttd.microsoftlistsunittest.repository.impl;

import com.ttd.microsoftlistsunittest.projection.workspace.WorkspaceProjection;
import com.ttd.microsoftlistsunittest.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WorkspaceRepositoryImpl implements WorkspaceRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<WorkspaceProjection> findAllWorkspacesByAccountId(Integer accountId) {
        String sql = """
                SELECT
                    wsp.Id AS id,
                    wsp.WorkspaceName AS workspaceName
                FROM
                    Workspace wsp
                INNER JOIN  
                    WorkspaceMember wm ON wsp.Id = wm.WorkspaceId
                WHERE 
                    wm.AccountId = ?
                ORDER BY
                    wm.JoinedAt DESC
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkspaceProjection.class), accountId);
    }
}
