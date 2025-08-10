package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ShareLink;
import com.ttd.microsoftlistsunittest.service.ShareLinkService;
import com.ttd.microsoftlistsunittest.service.rowmapper.ShareLinkRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShareLinkServiceImpl implements ShareLinkService {

    private final JdbcTemplate jdbcTemplate;
    private final ShareLinkRowMapper rowMapper;

    @Override
    public List<ShareLink> findAll() {
        String sql = "SELECT * FROM ShareLink";
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving share links", e);
        }
    }

    @Override
    public Optional<ShareLink> findById(Integer id) {
        String sql = "SELECT * FROM ShareLink WHERE Id = ?";
        try {
            return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving share link with ID: " + id, e);
        }
    }

    @Override
    public int save(ShareLink link) {
        String sql = """
            INSERT INTO ShareLink (ListId, TargetUrl, ScopeId, PermissionId, LinkStatus, CreatedBy)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        try {
            return jdbcTemplate.update(sql,
                    link.getListId(),
                    link.getTargetUrl(),
                    link.getScopeId(),
                    link.getPermissionId(),
                    link.getLinkStatus(),
                    link.getCreatedBy()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error saving share link", e);
        }
    }

    @Override
    public int update(ShareLink link) {
        if (link.getId() == null || link.getId() <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        String sql = """
            UPDATE ShareLink 
            SET ListId = ?, TargetUrl = ?, ScopeId = ?, PermissionId = ?, LinkStatus = ?, CreatedBy = ?
            WHERE Id = ?
        """;
        try {
            return jdbcTemplate.update(sql,
                    link.getListId(),
                    link.getTargetUrl(),
                    link.getScopeId(),
                    link.getPermissionId(),
                    link.getLinkStatus(),
                    link.getCreatedBy(),
                    link.getId()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating share link", e);
        }
    }

    @Override
    public int deleteById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must not be null or negative");
        }
        String sql = "DELETE FROM ShareLink WHERE Id = ?";
        try {
            return jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting share link", e);
        }
    }
}
