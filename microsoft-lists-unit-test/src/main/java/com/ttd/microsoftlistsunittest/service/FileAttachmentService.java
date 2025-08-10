package com.ttd.microsoftlistsunittest.service;

import com.ttd.microsoftlistsunittest.domain.FileAttachment;

import java.util.List;
import java.util.Optional;

public interface FileAttachmentService {
    List<FileAttachment> findAll();
    Optional<FileAttachment> findById(Integer id);
    int save(FileAttachment fileAttachment);
    int update(FileAttachment fileAttachment);
    int deleteById(Integer id);
}
