package ru.voronezhtsev.uploadfileexample;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class FileController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            InputStream in = new ByteArrayInputStream(file.getBytes());
            String fileName = file.getOriginalFilename();
            if(fileName == null) {
                throw new RuntimeException("filename is null");
            }
            try(OutputStream outputStream = new FileOutputStream(fileName)) {
                outputStream.write(in.readAllBytes());
            }
            return "ok";
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }
}
