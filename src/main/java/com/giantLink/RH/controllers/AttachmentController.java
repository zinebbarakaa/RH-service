package com.giantLink.RH.controllers;

import com.giantLink.RH.entities.Attachment;
import com.giantLink.RH.models.response.ResponseData;
import com.giantLink.RH.services.AttachmentService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Collections.copy;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;


@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    public final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";
    @Autowired
    private  AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }


    @PostMapping("/upload")
    public ResponseEntity<ResponseData> uploadFile(@RequestParam("file") MultipartFile file) {
        Attachment uploadedFile = attachmentService.saveFile(file);

        String downloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/download/")
                .path(uploadedFile.getId())
                .toUriString();

        ResponseData responseFile = new ResponseData(
                uploadedFile.getFileName(),
                downloadUri,
                uploadedFile.getFileType(),
                uploadedFile.getData().length
        );

        return ResponseEntity.status(HttpStatus.OK).body(responseFile);
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) throws IOException {
        Attachment file = attachmentService.getFileById(id);
        String filePath = DIRECTORY + file.getFileName();

        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(file.getData());
        fileOutputStream.close();

        Path path = Paths.get(filePath);
        byte[] fileContent = java.nio.file.Files.readAllBytes(path);

        File localFile = new File(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, file.getFileType());
        headers.add("Content-Length", String.valueOf(localFile.length()));

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }
}



