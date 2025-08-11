package com.ttd.microsoftlistsunittest.service.impl;

import com.ttd.microsoftlistsunittest.dto.AccountProfileDto;
import com.ttd.microsoftlistsunittest.dto.DashboardDto;
import com.ttd.microsoftlistsunittest.service.AccountService;
import com.ttd.microsoftlistsunittest.service.DashboardService;
import com.ttd.microsoftlistsunittest.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final AccountService accountService;
    private final ListService listService;

    @Override
    public DashboardDto getDashboardData(Integer accountId) {
        DashboardDto dashboardDto = new DashboardDto();

        AccountProfileDto profile = accountService.findAccountProfileById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountId));
        dashboardDto.setAccountProfileDto(profile);


        dashboardDto.setMyLists(listService.findAllByAccountId(accountId));
        dashboardDto.setFavoriteLists(listService.findAllFavoriteListsByAccountId(accountId));
        dashboardDto.setRecentLists(listService.findAllRecentListsByAccountId(accountId));

        return dashboardDto;
    }
}
