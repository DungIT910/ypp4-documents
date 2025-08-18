package com.ttd.microsoftlistsunittest.controller;

import com.ttd.microsoftlistsunittest.dto.workspace.WorkspaceDto;
import com.ttd.microsoftlistsunittest.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @GetMapping("/personal")
    public List<WorkspaceDto> getAllWorkspacesByAccountId(@RequestParam(value = "accountId", required = true) Integer accountId) {
        return workspaceService.getAllWorkspacesByAccountId(accountId);
    }
}
