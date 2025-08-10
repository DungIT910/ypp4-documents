package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.Workspace;
import com.ttd.microsoftlistsunittest.service.WorkspaceService;
import com.ttd.microsoftlistsunittest.service.rowmapper.WorkspaceRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final JdbcTemplate jdbcTemplate;
    private final WorkspaceRowMapper workspaceRowMapper;

    @Override
    public List<Workspace> findAll() {
        String sql = "SELECT * FROM Workspace";
        try {
            return jdbcTemplate.query(sql, workspaceRowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving workspaces", e);
        }
    }

    @Override
    public Optional<Workspace> findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or negative");
        }

        String sql = "SELECT * FROM Workspace WHERE Id = ?";
        try {
            List<Workspace> results = jdbcTemplate.query(sql, workspaceRowMapper, id);
            return results.stream().findFirst();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error finding workspace by ID: " + id, e);
        }
    }

    @Override
    public int save(Workspace workspace) {
        validateWorkspace(workspace);

        String sql = """
                    INSERT INTO Workspace (WorkspaceName, CreatedAt, UpdatedAt, Status)
                    VALUES (?, ?, ?, ?)
                """;
        try {
            return jdbcTemplate.update(sql,
                    workspace.getWorkspaceName(),
                    workspace.getCreatedAt(),
                    workspace.getUpdatedAt(),
                    "active"
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error saving workspace", e);
        }
    }

    @Override
    public int update(Workspace workspace) {
        if (workspace.getId() == null || workspace.getId() <= 0) {
            throw new IllegalArgumentException("Workspace ID cannot be null or negative for update");
        }

        validateWorkspace(workspace);

        String sql = """
                    UPDATE Workspace SET WorkspaceName = ?, UpdatedAt = ?, Status = ? WHERE Id = ?
                """;
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    workspace.getWorkspaceName(),
                    workspace.getUpdatedAt(),
                    "active",
                    workspace.getId()
            );

            if (rowsAffected == 0) {
                throw new RuntimeException("No workspace found with ID: " + workspace.getId());
            }

            return rowsAffected;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating workspace", e);
        }
    }

    @Override
    public int deleteById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID cannot be null or negative");
        }

        String sql = "UPDATE Workspace SET Status = 'inactive' WHERE Id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);

            if (rowsAffected == 0) {
                throw new RuntimeException("No workspace found with ID: " + id);
            }

            return rowsAffected;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting workspace", e);
        }
    }

    private void validateWorkspace(Workspace workspace) {
        if (workspace == null) {
            throw new IllegalArgumentException("Workspace cannot be null");
        }

        if (workspace.getWorkspaceName() == null || workspace.getWorkspaceName().trim().isEmpty()) {
            throw new IllegalArgumentException("Workspace name cannot be null or empty");
        }
    }
}
