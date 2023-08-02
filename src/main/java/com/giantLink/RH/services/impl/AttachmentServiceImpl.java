package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.Attachment;
import com.giantLink.RH.exceptions.FileStorageException;
import com.giantLink.RH.repositories.AttachmentRepository;
import com.giantLink.RH.services.AttachmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepository attachmentRepository;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment saveFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            Attachment attachment = new Attachment(fileName,file.getContentType(),file.getBytes());
            return attachmentRepository.save(attachment);
        } catch (IOException e) {
            throw new FileStorageException("Failed to store file " + fileName, e);
        }
    }
    @Override
    public Attachment getFileById(String id) throws FileNotFoundException {
        return attachmentRepository.findById(id).orElseThrow(() -> new FileNotFoundException("File not found with id: " + id));
    }
}
