package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ListRowComment;

import java.util.List;
import java.util.Optional;

public interface ListRowCommentService {
    List<ListRowComment> findAll();

    Optional<ListRowComment> findById(Integer id);

    int save(ListRowComment comment);

    int update(ListRowComment comment);

    int deleteById(Integer id);
}
