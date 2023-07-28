package com.giantLink.RH.services;

import com.giantLink.RH.entities.Attachment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@Service
public interface AttachmentService {

    public Attachment saveFile(MultipartFile file);

    Attachment getFileById(String id) throws FileNotFoundException;

}
