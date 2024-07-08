package com.example.demo.fileupload;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {

    public Optional<FileEntity> findByFilename(String filename);

}
