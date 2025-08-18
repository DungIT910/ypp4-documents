package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.workspace.WorkspaceDto;
import com.ttd.microsoftlistsunittest.repository.WorkspaceRepository;
import com.ttd.microsoftlistsunittest.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;

    @Override
    public List<WorkspaceDto> getAllWorkspacesByAccountId(Integer accountId) {
        return workspaceRepository.findAllWorkspacesByAccountId(accountId)
                .stream()
                .map(WorkspaceDto::from)
                .toList();
    }
}
