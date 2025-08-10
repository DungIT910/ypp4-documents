package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.Workspace;
import com.ttd.microsoftlistsunittest.service.rowmapper.WorkspaceRowMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WorkspaceServiceImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private WorkspaceRowMapper workspaceRowMapper;

    @InjectMocks
    private WorkspaceServiceImpl workspaceService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testFindAll_ShouldReturnWorkspaces() {
        Workspace mockWorkspace = createSampleWorkspace();
        List<Workspace> mockList = List.of(mockWorkspace);

        when(jdbcTemplate.query(eq("SELECT * FROM Workspace"), eq(workspaceRowMapper))).thenReturn(mockList);

        List<Workspace> result = workspaceService.findAll();

        assertEquals(1, result.size());
        assertEquals("Test Workspace", result.get(0).getWorkspaceName());
        verify(jdbcTemplate).query("SELECT * FROM Workspace", workspaceRowMapper);
    }

    @Test
    void testFindAll_ShouldReturnEmptyList_WhenNoWorkspaces() {
        when(jdbcTemplate.query(eq("SELECT * FROM Workspace"), eq(workspaceRowMapper)))
                .thenReturn(Collections.emptyList());

        List<Workspace> result = workspaceService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindById_ShouldReturnWorkspace() {
        Workspace mockWorkspace = createSampleWorkspace();
        mockWorkspace.setId(1);

        when(jdbcTemplate.query(eq("SELECT * FROM Workspace WHERE Id = ?"), eq(workspaceRowMapper), eq(1)))
                .thenReturn(List.of(mockWorkspace));

        Optional<Workspace> result = workspaceService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    void testFindById_ShouldReturnEmpty_WhenWorkspaceNotFound() {
        when(jdbcTemplate.query(eq("SELECT * FROM Workspace WHERE Id = ?"), eq(workspaceRowMapper), eq(1)))
                .thenReturn(Collections.emptyList());

        Optional<Workspace> result = workspaceService.findById(1);

        assertFalse(result.isPresent());
    }

    @Test
    void testFindById_ShouldThrowException_WhenIdIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> workspaceService.findById(null));

        assertEquals("ID cannot be null or negative", exception.getMessage());
    }

    @Test
    void testFindById_ShouldThrowException_WhenIdIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> workspaceService.findById(-1));

        assertEquals("ID cannot be null or negative", exception.getMessage());
    }

    @Test
    void testSave_ShouldInsertWorkspace() {
        Workspace workspace = createSampleWorkspace();

        when(jdbcTemplate.update(anyString(),
                anyString(), any(LocalDateTime.class), any(LocalDateTime.class), anyString()))
                .thenReturn(1);

        int result = workspaceService.save(workspace);

        assertEquals(1, result);
    }

    @Test
    void testSave_ShouldThrowException_WhenWorkspaceIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> workspaceService.save(null));

        assertEquals("Workspace cannot be null", exception.getMessage());
    }

    @Test
    void testSave_ShouldThrowException_WhenWorkspaceNameIsNull() {
        Workspace workspace = createSampleWorkspace();
        workspace.setWorkspaceName(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> workspaceService.save(workspace));

        assertEquals("Workspace name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testSave_ShouldThrowException_WhenWorkspaceNameIsEmpty() {
        Workspace workspace = createSampleWorkspace();
        workspace.setWorkspaceName("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> workspaceService.save(workspace));

        assertEquals("Workspace name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testUpdate_ShouldUpdateWorkspace() {
        Workspace workspace = createSampleWorkspace();
        workspace.setId(1);

        when(jdbcTemplate.update(
                anyString(),
                anyString(), any(LocalDateTime.class), anyString(), anyInt()
        )).thenReturn(1);

        int result = workspaceService.update(workspace);

        assertEquals(1, result);
    }

    @Test
    void testUpdate_ShouldThrowException_WhenIdIsNull() {
        Workspace workspace = createSampleWorkspace();
        workspace.setId(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> workspaceService.update(workspace));

        assertEquals("Workspace ID cannot be null or negative for update", exception.getMessage());
    }

    @Test
    void testUpdate_ShouldThrowException_WhenIdIsNegative() {
        Workspace workspace = createSampleWorkspace();
        workspace.setId(-1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> workspaceService.update(workspace));

        assertEquals("Workspace ID cannot be null or negative for update", exception.getMessage());
    }

    @Test
    void testUpdate_ShouldThrowException_WhenWorkspaceNotFound() {
        Workspace workspace = createSampleWorkspace();
        workspace.setId(1);

        when(jdbcTemplate.update(eq("UPDATE Workspace SET WorkspaceName = ?, UpdatedAt = ?, Status = ? WHERE Id = ?"),
                eq(workspace.getWorkspaceName()), any(LocalDateTime.class), eq("active"), eq(1)))
                .thenReturn(0);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> workspaceService.update(workspace));

        assertEquals("No workspace found with ID: " + workspace.getId(), exception.getMessage());
    }

    @Test
    void testDeleteById_ShouldDelete() {
        when(jdbcTemplate.update(eq("UPDATE Workspace SET Status = 'inactive' WHERE Id = ?"), eq(1)))
                .thenReturn(1);

        int result = workspaceService.deleteById(1);

        assertEquals(1, result);
        verify(jdbcTemplate).update("UPDATE Workspace SET Status = 'inactive' WHERE Id = ?", 1);
    }

    @Test
    void testDeleteById_ShouldThrowException_WhenIdIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> workspaceService.deleteById(null));

        assertEquals("ID cannot be null or negative", exception.getMessage());
    }

    @Test
    void testDeleteById_ShouldThrowException_WhenIdIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> workspaceService.deleteById(-1));

        assertEquals("ID cannot be null or negative", exception.getMessage());
    }

    @Test
    void testDeleteById_ShouldThrowException_WhenWorkspaceNotFound() {
        when(jdbcTemplate.update(eq("UPDATE Workspace SET Status = 'inactive' WHERE Id = ?"), eq(1)))
                .thenReturn(0);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> workspaceService.deleteById(1));

        assertEquals("No workspace found with ID: 1", exception.getMessage());
    }

    private Workspace createSampleWorkspace() {
        Workspace workspace = new Workspace();
        workspace.setId(1);
        workspace.setWorkspaceName("Test Workspace");
        workspace.setCreatedAt(LocalDateTime.now());
        workspace.setUpdatedAt(LocalDateTime.now());
        return workspace;
    }
}