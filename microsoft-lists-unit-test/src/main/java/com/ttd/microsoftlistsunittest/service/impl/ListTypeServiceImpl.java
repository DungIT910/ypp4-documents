package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.ListType;
import com.ttd.microsoftlistsunittest.dto.ListTypeCardDto;
import com.ttd.microsoftlistsunittest.service.ListTypeService;
import com.ttd.microsoftlistsunittest.service.rowmapper.domain.ListTypeRowMapper;
import com.ttd.microsoftlistsunittest.service.rowmapper.dto.ListTypeCardDtoRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListTypeServiceImpl implements ListTypeService {

    private final JdbcTemplate jdbcTemplate;
    private final ListTypeRowMapper listTypeRowMapper;
    private final ListTypeCardDtoRowMapper listTypeCardDtoRowMapper;

    // Get list types for list creation screen
    @Override
    public List<ListTypeCardDto> findAll() {
        String sql = """
                    SELECT
                        lt.Id, 
                        lt.Icon,
                        lt.Title,
                        lt.HeaderImage,
                        lt.ListTypeDescription
                    FROM ListType lt
                """;
        return jdbcTemplate.query(sql, listTypeCardDtoRowMapper);
    }

    @Override
    public Optional<ListType> findById(Integer id) {
        String sql = "SELECT * FROM ListType WHERE Id = ?";
        List<ListType> results = jdbcTemplate.query(sql, listTypeRowMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public int save(ListType listType) {
        String sql = """
                    INSERT INTO ListType (Title, Icon, ListTypeDescription, HeaderImage)
                    VALUES (?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                listType.getTitle(),
                listType.getIcon(),
                listType.getListTypeDescription(),
                listType.getHeaderImage());
    }

    @Override
    public int update(ListType listType) {
        String sql = """
                    UPDATE ListType SET Title = ?, Icon = ?, ListTypeDescription = ?, HeaderImage = ?
                    WHERE Id = ?
                """;
        return jdbcTemplate.update(sql,
                listType.getTitle(),
                listType.getIcon(),
                listType.getListTypeDescription(),
                listType.getHeaderImage(),
                listType.getId());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM ListType WHERE Id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
