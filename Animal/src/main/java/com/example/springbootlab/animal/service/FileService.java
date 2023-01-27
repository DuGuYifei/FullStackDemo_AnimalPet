package com.example.springbootlab.animal.service;

import com.example.springbootlab.animal.entity.FileInfo;
import com.example.springbootlab.animal.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2023/1/9 5:41
 * Description: file service
 */

@Service
public class FileService {
    private final FileRepository repository;

    @Autowired
    public FileService(FileRepository repository) {
        this.repository = repository;
    }

    public Optional<FileInfo> find(Long id) {
        return repository.findById(id);
    }

    public List<FileInfo> findAll() {
        return repository.findAll();
    }

    @Transactional
    public FileInfo create(FileInfo fileInfo) {
        return repository.save(fileInfo);
    }
}
