package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.Workspace;

import java.util.List;
import java.util.Optional;

public interface WorkspaceService {
    List<Workspace> findAll();

    Optional<Workspace> findById(Integer id);

    int save(Workspace workspace);

    int update(Workspace workspace);

    int deleteById(Integer id);
}
