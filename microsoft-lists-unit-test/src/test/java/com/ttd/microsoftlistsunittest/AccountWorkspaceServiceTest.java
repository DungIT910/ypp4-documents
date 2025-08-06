package com.ttd.microsoftlistsunittest;

import com.ttd.microsoftlistsunittest.domain.*;
import com.ttd.microsoftlistsunittest.service.AccountWorkspaceService;
import com.ttd.microsoftlistsunittest.service.impl.AccountWorkspaceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AccountWorkspaceServiceTest {

    @Test
    void testCrossJoinAccountWorkspace_PairingCorrectness() {
        List<Account> accounts = List.of(
                new Account(1, "Alice"),
                new Account(2, "Bob")
        );

        List<Workspace> workspaces = List.of(
                new Workspace(1, "Dev Team"),
                new Workspace(2, "Design Team")
        );

        AccountWorkspaceService service = new AccountWorkspaceServiceImpl();
        List<AccountWorkspacePair> results = service.crossJoinAccountWorkspace(accounts, workspaces);

        Assertions.assertEquals(4, results.size()); // 2 accounts x 2 workspaces = 4 pairs

        int aliceCount = 0;
        int bobCount = 0;

        for (AccountWorkspacePair pair : results) {
            String email = pair.getAccount().getEmail();
            if (email.equals("Alice")) {
                aliceCount++;
            } else if (email.equals("Bob")) {
                bobCount++;
            }
        }

        Assertions.assertEquals(2, aliceCount, "Alice should pair with 2 workspaces");
        Assertions.assertEquals(2, bobCount, "Bob should pair with 2 workspaces");
    }


    @Test
    void testInnerJoinAccountWorkspaceMember_OldStyle() {
        List<Account> accounts = new ArrayList<Account>();
        accounts.add(new Account(1, "Alice"));
        accounts.add(new Account(2, "Bob"));
        accounts.add(new Account(3, "Charlie"));

        List<WorkspaceMember> workspaceMembers = new ArrayList<WorkspaceMember>();
        workspaceMembers.add(new WorkspaceMember(101, 10, 1)); // Alice in Workspace 10
        workspaceMembers.add(new WorkspaceMember(102, 20, 2)); // Bob in Workspace 20

        AccountWorkspaceService service = new AccountWorkspaceServiceImpl();
        List<AccountWorkspaceMemberPair> results = service.innerJoinAccountWorkspaceMember(accounts, workspaceMembers);

        Assertions.assertEquals(2, results.size());

        for (AccountWorkspaceMemberPair pair : results) {
            int accountId = pair.getAccount().getId();
            int workspaceId = pair.getWorkspaceMember().getWorkspaceId();

            if (accountId == 1) {
                Assertions.assertEquals(10, workspaceId);
            } else if (accountId == 2) {
                Assertions.assertEquals(20, workspaceId);
            } else {
                Assertions.fail("Unexpected accountId: " + accountId);
            }
        }
    }

    @Test
    void testLeftJoinAccountWorkspaceMember() {
        List<Account> accounts = List.of(
                new Account(1, "alice@example.com"),
                new Account(2, "bob@example.com"),
                new Account(3, "charlie@example.com")
        );

        List<WorkspaceMember> members = List.of(
                new WorkspaceMember(1, 101, 1),
                new WorkspaceMember(2, 102, 2)
        );

        AccountWorkspaceServiceImpl service = new AccountWorkspaceServiceImpl();
        List<AccountWorkspaceMemberPair> results = service.leftJoinAccountWorkspaceMember(accounts, members);

        Assertions.assertEquals(3, results.size());

        for (AccountWorkspaceMemberPair pair : results) {
            int accId = pair.getAccount().getId();
            if (accId == 1) {
                Assertions.assertEquals(101, pair.getWorkspaceMember().getWorkspaceId());
            } else if (accId == 2) {
                Assertions.assertEquals(102, pair.getWorkspaceMember().getWorkspaceId());
            } else if (accId == 3) {
                Assertions.assertNull(pair.getWorkspaceMember());
            } else {
                Assertions.fail("Unexpected account id: " + accId);
            }
        }
    }


}
