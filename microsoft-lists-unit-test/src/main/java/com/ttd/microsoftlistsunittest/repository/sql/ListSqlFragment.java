package com.ttd.microsoftlistsunittest.repository.sql;

public final class ListSqlFragment {
    public static final String SELECT_LIST_SUMMARY = """
            l.Id AS listId,
            l.Color AS color,
            l.Icon AS icon,
            l.ListName AS listName,
            w.WorkspaceName AS workspaceName,
            CASE
                WHEN fl.Id IS NOT NULL THEN TRUE
                ELSE FALSE
            END AS isFavorite
            """;

    private ListSqlFragment() {
    }
}