package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.domain.Account;
import com.ttd.microsoftlistsunittest.domain.AccountWorkspacePair;
import com.ttd.microsoftlistsunittest.domain.Workspace;
import com.ttd.microsoftlistsunittest.service.AccountWorkspaceService;

import java.util.ArrayList;
import java.util.List;

public class AccountWorkspaceServiceImpl implements AccountWorkspaceService {

    @Override
    public List<AccountWorkspacePair> crossJoin(List<Account> accountData, List<Workspace> workspaceData) {
        List<AccountWorkspacePair> resultSet = new ArrayList<>();
        for (Account account : accountData ) {
            for (Workspace workspace : workspaceData) {
                resultSet.add(new AccountWorkspacePair(account, workspace));
            }
        }
        return resultSet;
    }
}
