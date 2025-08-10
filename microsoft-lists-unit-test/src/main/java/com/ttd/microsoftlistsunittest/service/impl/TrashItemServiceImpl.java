package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.TrashItem;
import com.ttd.microsoftlistsunittest.service.TrashItemService;
import com.ttd.microsoftlistsunittest.service.rowmapper.TrashItemRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrashItemServiceImpl implements TrashItemService {

    private final JdbcTemplate jdbcTemplate;
    private final TrashItemRowMapper rowMapper;

    @Override
    public List<TrashItem> findAll() {
        String sql = "SELECT * FROM TrashItem";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<TrashItem> findById(Integer id) {
        String sql = "SELECT * FROM TrashItem WHERE Id = ?";
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
    }

    @Override
    public int save(TrashItem item) {
        String sql = """
                    INSERT INTO TrashItem (ObjectTypeId, ObjectId, UserDeleteId, DeletedAt, OriginalPath)
                    VALUES (?, ?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                item.getObjectTypeId(),
                item.getObjectId(),
                item.getUserDeleteId(),
                item.getDeletedAt(),
                item.getOriginalPath());
    }

    @Override
    public int update(TrashItem item) {
        String sql = """
                    UPDATE TrashItem
                    SET ObjectTypeId = ?, ObjectId = ?, UserDeleteId = ?, DeletedAt = ?, OriginalPath = ?
                    WHERE Id = ?
                """;
        return jdbcTemplate.update(sql,
                item.getObjectTypeId(),
                item.getObjectId(),
                item.getUserDeleteId(),
                item.getDeletedAt(),
                item.getOriginalPath(),
                item.getId());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM TrashItem WHERE Id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
