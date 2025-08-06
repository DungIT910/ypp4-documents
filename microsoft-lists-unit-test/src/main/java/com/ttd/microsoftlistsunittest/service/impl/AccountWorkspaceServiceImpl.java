package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.*;
import com.ttd.microsoftlistsunittest.service.AccountWorkspaceService;

import java.util.ArrayList;
import java.util.List;

public class AccountWorkspaceServiceImpl implements AccountWorkspaceService {

    @Override
    public List<AccountWorkspacePair> crossJoinAccountWorkspace(List<Account> accountData, List<Workspace> workspaceData) {
        List<AccountWorkspacePair> resultSet = new ArrayList<>();
        for (Account account : accountData ) {
            for (Workspace workspace : workspaceData) {
                resultSet.add(new AccountWorkspacePair(account, workspace));
            }
        }
        return resultSet;
    }

    @Override
    public List<AccountWorkspaceMemberPair> innerJoinAccountWorkspaceMember(List<Account> accountData, List<WorkspaceMember> workspaceMemberData) {
        List<AccountWorkspaceMemberPair> resultSet = new ArrayList<>();
        for (Account account : accountData ) {
            for (WorkspaceMember workspaceMember : workspaceMemberData) {
                if (account.getId().equals(workspaceMember.getAccountId())) {
                    resultSet.add(new AccountWorkspaceMemberPair(account, workspaceMember));
                }
            }
        }
        return resultSet;
    }

    @Override
    public List<AccountWorkspaceMemberPair> leftJoinAccountWorkspaceMember(List<Account> accountData, List<WorkspaceMember> workspaceMemberData) {
        List<AccountWorkspaceMemberPair> resultSet = new ArrayList<>();
        for (Account account : accountData ) {
            boolean hasAccountInWorkspace = false;
            for (WorkspaceMember workspaceMember : workspaceMemberData) {
                if (account.getId().equals(workspaceMember.getAccountId())) {
                    resultSet.add(new AccountWorkspaceMemberPair(account, workspaceMember));
                    hasAccountInWorkspace = true;
                }
            }
            if (!hasAccountInWorkspace) {
                resultSet.add(new AccountWorkspaceMemberPair(account, null));
            }
        }
        return resultSet;
    }
}
