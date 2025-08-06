package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.*;

import java.util.List;

public interface AccountWorkspaceService {
    List<AccountWorkspacePair> crossJoinAccountWorkspace(List<Account> accountData, List<Workspace> workspaceData);
    List<AccountWorkspaceMemberPair> innerJoinAccountWorkspaceMember(List<Account> accountData, List<WorkspaceMember> workspaceMemberData);
    List<AccountWorkspaceMemberPair> leftJoinAccountWorkspaceMember(List<Account> accountData, List<WorkspaceMember> workspaceMemberData);
}
