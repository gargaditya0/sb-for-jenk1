package com.example.demo.fileupload;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/file")
public class FileUploadController {



    private final FileUploadService uploadService;

    public FileUploadController(FileUploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty())
            return "File is empty...";
        log.info("File size : {}", file.getSize() / 1024);
        log.info("Filename :  {}", file.getOriginalFilename());
        uploadService.upload(file);
        return "Success in uploading file " + file.getOriginalFilename();
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFileById(@PathVariable Long id) throws IOException {
        Optional<FileEntity> fileEntityOptional = uploadService.get(id);
        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();
            Resource resource = new ClassPathResource(fileEntity.getFilepath());
            InputStream inputStream = resource.getInputStream();
            byte[] data = FileCopyUtils.copyToByteArray(inputStream);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFilename() + "\"")
                    .body(data);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
