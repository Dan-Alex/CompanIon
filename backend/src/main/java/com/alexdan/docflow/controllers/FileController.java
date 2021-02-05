package com.alexdan.docflow.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping
    public @ResponseBody String addFile(@RequestParam("file") MultipartFile file) throws IOException {

        String filename = new String();
        if (file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + filename));
        }
        return filename;
    }
}
