package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.SystemColumn;

import java.util.List;

public interface SystemColumnService {
    List<SystemColumn> findAll();
    SystemColumn findById(Integer id);
    void create(SystemColumn column);
    void update(SystemColumn column);
    void delete(Integer id);
}
