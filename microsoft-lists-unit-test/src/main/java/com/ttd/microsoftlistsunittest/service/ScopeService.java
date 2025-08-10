package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.Scope;

import java.util.List;
import java.util.Optional;

public interface ScopeService {
    Scope create(Scope scope);
    Optional<Scope> findById(int id);
    List<Scope> findAll();
    boolean update(Scope scope);
    boolean delete(int id);
}