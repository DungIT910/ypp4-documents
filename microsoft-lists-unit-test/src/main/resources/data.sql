-- Insert 3 Accounts
INSERT INTO Account (Avatar, FirstName, LastName, DateBirth, Email, Company, AccountStatus, AccountPassword)
VALUES ('avatar1.png', 'John', 'Doe', '1990-01-01', 'john@example.com', 'Acme Inc.', 'active', 'pass1'),
       ('avatar2.png', 'Jane', 'Smith', '1992-02-02', 'jane@example.com', 'Beta Corp.', 'active', 'pass2'),
       ('avatar3.png', 'Alice', 'Brown', '1988-03-03', 'alice@example.com', 'Gamma Ltd.', 'inactive', 'pass3');

-- Insert 2 Workspaces (1 by John, 1 by Jane)
INSERT INTO Workspace (WorkspaceName, CreatedBy, isPresonal)
VALUES ('Marketing Team', 1, FALSE),
       ('Dev Team', 2, FALSE);

-- Insert 5 Lists
INSERT INTO List (ListName, Icon, Color, WorkspaceId, CreatedBy, ListStatus)
VALUES ('Marketing Tasks', 'list', '#FF0000', 1, 1, 'active'), -- Id 1
       ('Campaign Ideas', 'idea', '#00FF00', 1, 1, 'active'),  -- Id 2
       ('Dev Sprint 1', 'code', '#0000FF', 2, 2, 'active'),    -- Id 3
       ('Dev Sprint 2', 'code', '#3333FF', 2, 2, 'archived'),  -- Id 4
       ('Bug List', 'bug', '#FF00FF', 2, 2, 'active');
-- Id 5

-- Insert 4 FavoriteList
INSERT INTO FavoriteList (ListId, AccountId)
VALUES (1, 1), -- John favorites "Marketing Tasks"
       (2, 1), -- John favorites "Campaign Ideas"
       (3, 2), -- Jane favorites "Dev Sprint 1"
       (5, 1); -- John favorites "Bug List"
