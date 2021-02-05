package com.alexdan.docflow.controllers;


import com.alexdan.docflow.models.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping
    public @ResponseBody Document addFile(@RequestParam("file") MultipartFile file) throws IOException {

        String shortName = file.getOriginalFilename();
        String filename = new String();
        if (file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            filename = UUID.randomUUID().toString() + "." + shortName;
            file.transferTo(new File(uploadPath + "/" + filename));
        }

        return new Document(filename, shortName);
    }
}
