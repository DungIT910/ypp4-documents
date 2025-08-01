USE MsList;
GO

-- ACCOUNT
INSERT INTO Account (FirstName, LastName, Email, AccountPassword, Avatar, Company, AccountStatus, DateBirth, CreatedAt, UpdatedAt)
SELECT TOP 1000
    'FirstName' + CAST(n AS NVARCHAR),
    'LastName' + CAST(n AS NVARCHAR),
    'user' + CAST(n AS NVARCHAR) + '@example.com', -- Unique email
    'password' + CAST(n AS NVARCHAR),
    'https://avatar.example.com/avatar' + CAST(n % 100 + 1 AS NVARCHAR) + '.png',
    'Company' + CAST(n % 10 + 1 AS NVARCHAR),
    CASE WHEN n <= 900 THEN 'Active' ELSE 'Inactive' END, -- 90% Active, 10% Inactive
    DATEADD(YEAR, -18 - (n % 43), '2025-07-29'), -- Birthdates from 18 to 60 years old
    DATEADD(DAY, n % 208, '2025-01-01'), -- CreatedAt from 2025-01-01 to ~2025-07-26
    GETDATE() AS UpdatedAt
FROM (
    SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
    FROM sys.objects a CROSS JOIN sys.objects b
) AS numbers
WHERE n BETWEEN 1 AND 1000;


-- WORKSPACE
INSERT INTO Workspace (WorkspaceName, CreatedAt, UpdatedAt)
SELECT TOP 1000
    'Workspace' + CAST(n AS NVARCHAR),
    DATEADD(DAY, n % 208, '2025-01-01'), -- CreatedAt from 2025-01-01 to ~2025-07-26
    GETDATE() AS UpdatedAt
FROM (
    SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
    FROM sys.objects a CROSS JOIN sys.objects b
) AS numbers
WHERE n BETWEEN 1 AND 1000;


-- WORKSPACEMEMBER
INSERT INTO WorkspaceMember (WorkspaceId, AccountId, JoinedAt, MemberStatus, UpdatedAt)
SELECT 
    WorkspaceId,
    AccountId,
    DATEADD(DAY, (WorkspaceId + AccountId) % 208, '2025-01-01') AS JoinedAt, -- Dates from 2025-01-01 to ~2025-07-26
    CASE 
        WHEN (WorkspaceId + AccountId) % 20 < 18 THEN 'Active' -- 90% Active
        ELSE 'Inactive' -- 10% Inactive
    END AS MemberStatus,
    GETDATE() AS UpdatedAt
FROM (
    SELECT 
        w.Id AS WorkspaceId,
        a.Id AS AccountId
    FROM 
        (SELECT Id FROM Workspace WHERE Id BETWEEN 1 AND 200) w
        CROSS JOIN (SELECT Id FROM Account WHERE Id BETWEEN 1 AND 600) a
    WHERE 
        -- Distribute 5 Accounts per Workspace, ~1.67 Workspaces per Account
        ((a.Id - 1) / 120 + 1) = ((w.Id - 1) / 40 + 1) -- Divide into 5 groups
        AND (a.Id - 1) % 40 = (w.Id - 1) % 40 -- Round-robin assignment
) AS pairs
ORDER BY WorkspaceId, AccountId;


-- Insert 3 Permission records
INSERT INTO Permission (PermissionName, PermissionCode, PermissionDescription, Icon)
VALUES 
    ('Administrator', 'Admin', 'Full access to manage and edit all resources', 'admin-icon.png'),
    ('Contributor', 'Contributor', 'Can edit and contribute to resources', 'contributor-icon.png'),
    ('Reader', 'Reader', 'Read-only access to view resources', 'reader-icon.png');


INSERT INTO ViewType (Title, HeaderImage, Icon, ViewTypeDescription)
VALUES 
    ('List', 'https://example.com/images/list.png', 'list-icon.png', 'Displays data in a tabular list format'),
    ('Gallery', 'https://example.com/images/gallery.png', 'gallery-icon.png', 'Shows data as a visual gallery'),
    ('Calendar', 'https://example.com/images/calendar.png', 'calendar-icon.png', 'Organizes data in a calendar view'),
    ('Board', 'https://example.com/images/board.png', 'board-icon.png', 'Presents data in a kanban board style');


INSERT INTO ListType (Title, Icon, ListTypeDescription, HeaderImage)
VALUES 
    ('List', 'list-icon.png', 'A tabular list for structured data display', 'https://example.com/images/list-header.png'),
    ('Form', 'form-icon.png', 'A form-based interface for data entry', 'https://example.com/images/form-header.png'),
    ('Gallery', 'gallery-icon.png', 'A visual gallery for image or card-based data', 'https://example.com/images/gallery-header.png'),
    ('Calendar', 'calendar-icon.png', 'A calendar view for date-based data', 'https://example.com/images/calendar-header.png'),
    ('Board', 'board-icon.png', 'A kanban board for task and workflow management', 'https://example.com/images/board-header.png');


INSERT INTO ViewSettingKey (SettingKey, ValueType)
VALUES 
    ('Set this as public view', 'BOOLEAN'),
    ('Start date on calendar', 'COLUMN'),
    ('End date on calendar', 'COLUMN'),
    ('Default layout', 'TEXT'),
    ('Title of items on calendar', 'COLUMN'),
    ('Subtitle', 'COLUMN'),
    ('Sort table by', 'COLUMN');


INSERT INTO ViewTypeSettingKey (ViewTypeId, ViewSettingKeyId)
VALUES 
    (1, 1), -- List view with "Set this as public view"
    (2, 1), -- Gallery view with "Set this as public view"
    (3, 1), -- Calendar view with "Set this as public view"
    (4, 1), -- Board view with "Set this as public view"
    (3, 2), -- Calendar view with "Start date on calendar"
    (3, 3), -- Calendar view with "End date on calendar"
    (3, 4), -- Calendar view with "Default layout"
    (3, 5), -- Calendar view with "Title of items on calendar"
    (3, 6), -- Calendar view with "Subtitle"
    (2, 7); -- Gallery view with "Sort table by"


INSERT INTO SystemDataType (DisplayName, DataTypeValue, Icon, DataTypeDescription, CoverImg)
VALUES 
    ('Single line of text', 'Text', 'text-icon.png', 'A single line of text for short entries', 'https://example.com/images/text-cover.png'),
    ('Multiple lines of text', 'Text', 'multiline-text-icon.png', 'Multiple lines of text for longer entries', 'https://example.com/images/multiline-text-cover.png'),
    ('Number', 'Number', 'number-icon.png', 'Numeric values for calculations or counts', 'https://example.com/images/number-cover.png'),
    ('Currency', 'Currency', 'currency-icon.png', 'Monetary values with currency format', 'https://example.com/images/currency-cover.png'),
    ('Date and time', 'DateTime', 'datetime-icon.png', 'Date and time values', 'https://example.com/images/datetime-cover.png'),
    ('Choice', 'Choice', 'choice-icon.png', 'Predefined options for selection', 'https://example.com/images/choice-cover.png'),
    ('Yes/No', 'Boolean', 'yesno-icon.png', 'Boolean values for yes or no choices', 'https://example.com/images/yesno-cover.png'),
    ('Lookup', 'Lookup', 'lookup-icon.png', 'Reference to data in another list', 'https://example.com/images/lookup-cover.png'),
    ('Person or Group', 'Person', 'person-icon.png', 'Select a person or group', 'https://example.com/images/person-cover.png'),
    ('Hyperlink', 'Text', 'hyperlink-icon.png', 'URL or web address', 'https://example.com/images/hyperlink-cover.png'),
    ('Picture', 'Text', 'picture-icon.png', 'Image or picture URL', 'https://example.com/images/picture-cover.png'),
    ('Location', 'Text', 'location-icon.png', 'Geographic location data', 'https://example.com/images/location-cover.png'),
    ('Rate', 'Number', 'rate-icon.png', 'Rating or scoring value', 'https://example.com/images/rate-cover.png');


INSERT INTO KeySetting (KeyName, ValueType, IsShareLinkSetting, IsDefaultValue, ValueOfDefault)
VALUES 
    ('Max length', 'number', 0, 1, '255'),
    ('Is required', 'boolean', 0, 1, 'FALSE'),
    ('Enforce unique values', 'boolean', 0, 1, 'FALSE'),
    ('Add to all content types', 'boolean', 0, 1, 'TRUE'),
    ('Choice setting', 'choice', 0, 0, NULL),
    ('Allow users to add custom values', 'boolean', 0, 1, 'FALSE'),
    ('Display type', 'text', 0, 0, NULL),
    ('Allow multiple choice', 'boolean', 0, 1, 'FALSE'),
    ('Default value', 'text', 0, 0, NULL),
    ('Default value', 'Choice', 0, 0, NULL),
    ('Include time', 'boolean', 0, 1, 'FALSE'),
    ('Default value', 'dateTime', 0, 0, NULL),
    ('Easy format to use', 'boolean', 0, 1, 'FALSE'),
    ('Use enhanced rich text', 'boolean', 0, 1, 'FALSE'),
    ('Append changes to existing text', 'boolean', 0, 1, 'FALSE'),
    ('Show avatar', 'boolean', 0, 1, 'FALSE'),
    ('Allow choose group', 'boolean', 0, 1, 'FALSE'),
    ('Number symbol', 'text', 0, 0, NULL),
    ('Number of decimal places', 'number', 0, 0, NULL),
    ('Default value', 'number', 0, 0, NULL),
    ('Minvalue allowed', 'number', 0, 0, NULL),
    ('Maxvalue allowed', 'number', 0, 0, NULL),
    ('Default value', 'boolean', 0, 1, 'FALSE'),
    ('Expiration date', 'dateTime', 1, 0, NULL),
    ('Password', 'text', 1, 0, NULL),
    ('Is login required', 'boolean', 1, 1, 'TRUE');


INSERT INTO DataTypeSettingKey (SystemDataTypeId, KeySettingId)
VALUES 
    (1, 1),  -- Single line of text - Max length
    (1, 2),  -- Single line of text - Is required
    (1, 3),  -- Single line of text - Enforce unique values
    (1, 4),  -- Single line of text - Add to all content types
    (1, 9),  -- Single line of text - Default value (text)
    (6, 5),  -- Choice - Choice setting
    (6, 6),  -- Choice - Allow users to add custom values
    (6, 7),  -- Choice - Display type
    (6, 8),  -- Choice - Allow multiple choice
    (6, 2),  -- Choice - Is required
    (6, 3),  -- Choice - Enforce unique values
    (6, 4),  -- Choice - Add to all content types
    (6, 10), -- Choice - Default value (Choice)
    (5, 11), -- Date and time - Include time
    (5, 12), -- Date and time - Default value (dateTime)
    (5, 13), -- Date and time - Easy format to use
    (5, 2),  -- Date and time - Is required
    (5, 3),  -- Date and time - Enforce unique values
    (5, 4),  -- Date and time - Add to all content types
    (2, 14), -- Multiple lines of text - Use enhanced rich text
    (2, 15), -- Multiple lines of text - Append changes to existing text
    (2, 2),  -- Multiple lines of text - Is required
    (2, 4),  -- Multiple lines of text - Add to all content types
    (9, 16), -- Person or Group - Show avatar
    (9, 17), -- Person or Group - Allow choose group
    (9, 2),  -- Person or Group - Is required
    (9, 3),  -- Person or Group - Enforce unique values
    (9, 4),  -- Person or Group - Add to all content types
    (3, 18), -- Number - Number symbol
    (3, 19), -- Number - Number of decimal places
    (3, 20), -- Number - Default value (number)
    (3, 21), -- Number - Minvalue allowed
    (3, 22), -- Number - Maxvalue allowed
    (3, 2),  -- Number - Is required
    (3, 3),  -- Number - Enforce unique values
    (3, 4),  -- Number - Add to all content types
    (7, 2),  -- Yes/No - Is required
    (7, 4),  -- Yes/No - Add to all content types
    (7, 23), -- Yes/No - Default value (boolean)
    (10, 2), -- Hyperlink - Is required
    (10, 4), -- Hyperlink - Add to all content types
    (11, 2), -- Picture - Is required
    (11, 4); -- Picture - Add to all content types


INSERT INTO TemplateProvider (ProviderName)
VALUES ('Microsoft');
-- Insert the remaining 999 records with repeating pattern
INSERT INTO TemplateProvider (ProviderName)
SELECT 
    'templateprovider_' + CAST((n % 3 + 2) AS NVARCHAR) -- Cycles through 2, 3, 4
FROM (
    SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) - 1 AS n
    FROM sys.objects a CROSS JOIN sys.objects b
) AS numbers
WHERE n BETWEEN 0 AND 998; -- Generate 999 records to reach total 1000

INSERT INTO ListTemplate (Title, HeaderImage, TemplateDescription, Icon, Color, Sumary, Feature, ProviderId)
VALUES 
    ('Asset Manager', 'https://example.com/templates/asset-manager-header.png', 'The Asset Manager template helps track physical assets used by your team, knowing who holds which asset, which assets are under repair, and the check-in/check-out dates.', 'asset-manager-icon.png', 'blue', 'Track physical assets', 'Manage check-in/check-out, repair status, asset allocation', 1),
    ('Content Scheduler', 'https://example.com/templates/content-scheduler-header.png', 'The Content Scheduler template helps plan and manage content strategy. Filter upcoming due items or get notifications when authors submit drafts.', 'content-scheduler-icon.png', 'green', 'Plan content', 'Manage content strategy, filter due items, notifications', 1),
    ('Employee Onboarding', 'https://example.com/templates/employee-onboarding-header.png', 'The Employee Onboarding template helps manage the onboarding process for new employees and guide them through relevant contacts and resources.', 'employee-onboarding-icon.png', 'orange', 'Onboard new employees', 'Manage onboarding process, contacts, resources', 1),
    ('Event Itinerary', 'https://example.com/templates/event-itinerary-header.png', 'The Event Itinerary template organizes all important event details in one place, ensuring everything runs smoothly. Switch to calendar view for a clear view of event activities by time (day, week, month).', 'event-itinerary-icon.png', 'purple', 'Organize events', 'Manage event details, calendar view', 1),
    ('Issue Tracker', 'https://example.com/templates/issue-tracker-header.png', 'The Issue Tracker template helps track, manage, and resolve issues by easily setting priority levels in the status column and notifying team members when issues arise.', 'issue-tracker-icon.png', 'red', 'Track issues', 'Manage issues, set priorities, notifications', 1),
    ('Recruitment Tracker', 'https://example.com/templates/recruitment-tracker-header.png', 'The Recruitment Tracker template helps track and manage the hiring process within your organization or team, capturing feedback for all candidates.', 'recruitment-tracker-icon.png', 'yellow', 'Manage hiring', 'Track hiring process, candidate feedback', 1),
    ('Travel Requests', 'https://example.com/templates/travel-requests-header.png', 'The Travel Requests template helps manage all travel requests and track budgets.', 'travel-requests-icon.png', 'teal', 'Manage travel requests', 'Track travel requests, budgets', 1),
    ('Work Tracker', 'https://example.com/templates/work-tracker-header.png', 'The Work Tracker template helps track priorities and progress as you work to deliver products and services.', 'work-tracker-icon.png', 'gray', 'Track work progress', 'Manage work priorities, progress', 1),
    ('Incidents', 'https://example.com/templates/incidents-header.png', 'The Incidents template helps track and manage incidents, such as IT issues or security breaches, with status updates and notifications.', 'incidents-icon.png', 'pink', 'Track incidents', 'Manage incidents, status updates, notifications', 1),
    ('Patients', 'https://example.com/templates/patients-header.png', 'The Patients template helps healthcare staff track patient information, including medical history, appointments, and treatment plans.', 'patients-icon.png', 'brown', 'Track patient information', 'Manage medical history, appointments, treatment plans', 1),
    ('Loans', 'https://example.com/templates/loans-header.png', 'The Loans template helps manage loan applications, approvals, and repayments, tracking all loan-related information.', 'loans-icon.png', 'black', 'Manage loans', 'Track loan applications, approvals, repayments', 1),
    ('Task Planner', 'https://example.com/templates/task-planner-header.png', 'The Task Planner template helps organize and prioritize tasks for team projects, with options to assign tasks and track completion.', 'task-planner-icon.png', 'cyan', 'Plan tasks', 'Organize tasks, assign team members, track completion', 1),
    ('Inventory Management', 'https://example.com/templates/inventory-management-header.png', 'The Inventory Management template helps track stock levels, reorder points, and supplier details for efficient inventory control.', 'inventory-management-icon.png', 'lime', 'Manage inventory', 'Track stock levels, reorder points, supplier details', 1),
    ('Feedback Collector', 'https://example.com/templates/feedback-collector-header.png', 'The Feedback Collector template helps gather and organize feedback from customers or team members, with options to categorize and prioritize responses.', 'feedback-collector-icon.png', 'magenta', 'Collect feedback', 'Gather feedback, categorize responses, prioritize actions', 1);


INSERT INTO ListTemplate (Title, HeaderImage, TemplateDescription, Icon, Color, Sumary, Feature, ProviderId)
SELECT 
    'template' + CAST(p.Id AS NVARCHAR) + '_' + CAST(n AS NVARCHAR) AS Title,
    'https://example.com/templates/template' + CAST(p.Id AS NVARCHAR) + '_' + CAST(n AS NVARCHAR) + '-header.png' AS HeaderImage,
    'Description for template ' + CAST(p.Id AS NVARCHAR) + '_' + CAST(n AS NVARCHAR) + ' provided by provider ' + CAST(p.Id AS NVARCHAR) AS TemplateDescription,
    'template' + CAST(p.Id AS NVARCHAR) + '_' + CAST(n AS NVARCHAR) + '-icon.png' AS Icon,
    CASE 
        WHEN n = 1 THEN 'blue'
        WHEN n = 2 THEN 'green'
        WHEN n = 3 THEN 'orange'
    END AS Color,
    'Summary for template ' + CAST(p.Id AS NVARCHAR) + '_' + CAST(n AS NVARCHAR) AS Sumary,
    'Feature for template ' + CAST(p.Id AS NVARCHAR) + '_' + CAST(n AS NVARCHAR) AS Feature,
    p.Id AS ProviderId
FROM 
    TemplateProvider p
CROSS JOIN 
    (SELECT 1 AS n UNION ALL SELECT 2 UNION ALL SELECT 3) AS numbers
WHERE 
    p.Id BETWEEN 2 AND 200;


INSERT INTO TemplateView (ListTemplateId, ViewTypeId, ViewName, DisplayOrder)
SELECT 
    lt.Id AS ListTemplateId,
    vt.Id AS ViewTypeId,
    lt.Title + ' ' + vt.Title AS ViewName,
    vt.Id AS DisplayOrder
FROM 
    ListTemplate lt
CROSS JOIN 
    ViewType vt
WHERE 
    lt.Id BETWEEN 1 AND 100
    AND vt.Id IN (1, 2, 3, 4)
ORDER BY 
    lt.Id, vt.Id;


INSERT INTO TemplateColumn (SystemDataTypeId, ListTemplateId, ColumnName, ColumnDescription, DisplayOrder, IsVisible)
SELECT 
    CASE n
        WHEN 1 THEN 1  -- Text
        WHEN 2 THEN 3  -- Number
        WHEN 3 THEN 5  -- DateTime
        WHEN 4 THEN 6  -- Choice
        WHEN 5 THEN 11 -- Picture
    END AS SystemDataTypeId,
    lt.Id AS ListTemplateId,
    lt.Title + ' col ' + CAST(n AS NVARCHAR) AS ColumnName,
    CASE 
        WHEN n = 1 THEN 'Text column for ' + lt.Title + ' col ' + CAST(n AS NVARCHAR)
        WHEN n = 2 THEN 'Number column for ' + lt.Title + ' col ' + CAST(n AS NVARCHAR)
        WHEN n = 3 THEN 'DateTime column for ' + lt.Title + ' col ' + CAST(n AS NVARCHAR)
        WHEN n = 4 THEN 'Choice column for ' + lt.Title + ' col ' + CAST(n AS NVARCHAR)
        WHEN n = 5 THEN 'Picture column for ' + lt.Title + ' col ' + CAST(n AS NVARCHAR)
    END AS ColumnDescription,
    n AS DisplayOrder,
    1 AS IsVisible
FROM 
    ListTemplate lt
CROSS JOIN 
    (SELECT 1 AS n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5) AS numbers
WHERE 
    lt.Id BETWEEN 1 AND 100
ORDER BY 
    lt.Id, n;


INSERT INTO TemplateColumnSettingValue (TemplateColumnId, DataTypeSettingKeyId, KeyValue)
SELECT 
    tc.Id AS TemplateColumnId,
    dtsk.Id AS DataTypeSettingKeyId,
    CASE 
        -- Single line of text (SystemDataTypeId = 1)
        WHEN dtsk.KeySettingId = 1 THEN CAST(tc.Id AS NVARCHAR) -- Max length = TemplateColumn.Id
        WHEN dtsk.KeySettingId = 9 THEN LEFT(tc.ColumnName, 4) -- Default value = first 4 chars of ColumnName
        -- Number (SystemDataTypeId = 3)
        WHEN dtsk.KeySettingId = 19 THEN 
            CASE 
                WHEN tc.Id % 2 = 1 THEN '1' -- Odd Id: 1 decimal place
                ELSE '2' -- Even Id: 2 decimal places
            END
        -- DateTime (SystemDataTypeId = 5)
        WHEN dtsk.KeySettingId = 12 THEN 
            CASE 
                WHEN tc.Id % 2 = 1 THEN '01/08/2025' -- Odd Id: 01/08/2025
                ELSE '02/08/2025' -- Even Id: 02/08/2025
            END
        -- Other settings: NULL
        ELSE NULL
    END AS KeyValue
FROM 
    TemplateColumn tc
JOIN 
    DataTypeSettingKey dtsk ON tc.SystemDataTypeId = dtsk.SystemDataTypeId
WHERE 
    tc.Id BETWEEN 1 AND 500
    AND dtsk.Id IN (
        1, 2, 3, 4, 5,  -- Text (Single line of text)
        6, 7, 8, 9, 10, 11, 12, 13, -- Choice
        14, 15, 16, 17, 18, 19, -- DateTime
        29, 30, 31, 32, 33, 34, 35, 36, -- Number
        42, 43 -- Picture
    )
ORDER BY 
    tc.Id, dtsk.Id;


INSERT INTO ColumnSettingObject (ColumnId, DisplayName, DisplayColor, DisplayOrder, Context, CreatedAt, UpdatedAt)
SELECT 
    tc.Id AS ColumnId,
    tc.ColumnName + ' choice ' + CAST(n AS NVARCHAR) AS DisplayName,
    CASE 
        WHEN n = 1 THEN 'blue'
        WHEN n = 2 THEN 'green'
        WHEN n = 3 THEN 'orange'
    END AS DisplayColor,
    n AS DisplayOrder,
    'TEMPLATE' AS Context,
    GETDATE() AS CreatedAt,
    GETDATE() AS UpdatedAt
FROM 
    TemplateColumn tc
CROSS JOIN 
    (SELECT 1 AS n UNION ALL SELECT 2 UNION ALL SELECT 3) AS numbers
WHERE 
    tc.ListTemplateId BETWEEN 1 AND 14
    AND tc.SystemDataTypeId = 6
ORDER BY 
    tc.Id, n;