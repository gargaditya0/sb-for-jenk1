package com.example.demo.fileupload;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Log4j2
@Service
public class FileUploadService {

    private static final String UPLOAD_DIR = "uploaded-files";


    private final FileEntityRepository repository;

    public FileUploadService(FileEntityRepository repository) {
        this.repository = repository;
    }

    public void upload(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();

        Optional<FileEntity> existingFileCheck = repository.findByFilename(originalFilename);
        if (existingFileCheck.isPresent()) {
            log.info("File {} already exists.", originalFilename);
            return;
        }

        File uploadDir = new File(UPLOAD_DIR);
        // create dir if not existed
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // save file to location
        File newFile = new File(uploadDir.getAbsolutePath() + File.separator + originalFilename);

        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            fos.write(file.getBytes());
        } catch (IOException ex) {
            log.log(Level.ERROR, ex.getMessage(), ex);
        }
        FileEntity fileEntity = FileEntity.builder()
                .filename(originalFilename)
                .filepath(newFile.getAbsolutePath()).build();
        repository.save(fileEntity);
    }

    public Optional<FileEntity> get(long id) {
        return repository.findById(id);
    }

}
