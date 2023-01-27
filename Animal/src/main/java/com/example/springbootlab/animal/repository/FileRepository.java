package com.example.springbootlab.animal.repository;

import com.example.springbootlab.animal.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2023/1/9 5:40
 * Description:
 */

@Repository
public interface FileRepository extends JpaRepository<FileInfo, Long> {

}
