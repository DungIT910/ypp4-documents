package com.ttd.microsoftlistsunittest;

import com.ttd.microsoftlistsunittest.domain.Account;
import com.ttd.microsoftlistsunittest.domain.AccountWorkspacePair;
import com.ttd.microsoftlistsunittest.domain.Workspace;
import com.ttd.microsoftlistsunittest.service.AccountWorkspaceService;
import com.ttd.microsoftlistsunittest.service.impl.AccountWorkspaceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

class AccountWorkspaceServiceTest {

    @Test
    void testCrossJoin() {
        List<Account> accounts = List.of(
                new Account(1,  "Alice"),
                new Account(2, "Bob")
        );

        List<Workspace> workspaces = List.of(
                new Workspace(1, "Dev Team"),
                new Workspace(2, "Design Team")
        );

        AccountWorkspaceService service = new AccountWorkspaceServiceImpl();
        List<AccountWorkspacePair> results = service.crossJoin(accounts, workspaces);

        Assertions.assertEquals(4, results.size()); // 2 x 2 = 4

        Assertions.assertEquals("Alice", results.get(1).getAccount().getEmail());
        Assertions.assertEquals("Design Team", results.get(1).getWorkspace().getWorkspaceName());
    }
}
