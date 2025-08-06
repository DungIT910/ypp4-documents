package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.Account;
import com.ttd.microsoftlistsunittest.domain.AccountWorkspacePair;
import com.ttd.microsoftlistsunittest.domain.Workspace;

import java.util.List;

public interface AccountWorkspaceService {
    List<AccountWorkspacePair> crossJoin(List<Account> accountData, List<Workspace> workspaceData);
}
