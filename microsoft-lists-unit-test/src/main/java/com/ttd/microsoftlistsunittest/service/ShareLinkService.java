package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.ShareLink;

import java.util.List;
import java.util.Optional;

public interface ShareLinkService {
    List<ShareLink> findAll();
    Optional<ShareLink> findById(Integer id);
    int save(ShareLink shareLink);
    int update(ShareLink shareLink);
    int deleteById(Integer id);
}
