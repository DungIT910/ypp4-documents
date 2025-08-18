package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.workspace.WorkspaceDto;
import com.ttd.microsoftlistsunittest.repository.WorkspaceRepository;
import com.ttd.microsoftlistsunittest.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(cacheNames = "workspacesByAccount", key = "#accountId")
    public List<WorkspaceDto> getAllWorkspacesByAccountId(Integer accountId) {
        return workspaceRepository.findAllWorkspacesByAccountId(accountId)
                .stream()
                .toList();
    }
}
