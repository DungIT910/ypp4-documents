package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.dto.DashboardDto;

public interface DashboardService {
    DashboardDto getDashboardData(Integer accountId);
}
