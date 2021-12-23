package com.tian;

import com.tian.service.OSSService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ projectName:    Springboot
 * @ package:        com.tian
 * @ className:      OSSTest
 * @ author:     tian
 * @ description:  TODO
 * @ date:    2021/12/23 13:38
 * @ version:    1.0
 */
@SpringBootTest
public class OSSTest {
    @Autowired
    private OSSService ossService;
    @Test
    public void uploadTest() throws IOException {
        Path path = Paths.get("D:\\Desktop\\20211223134931.png");
        File file = new File(path.toUri());
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), fileInputStream);
        String tiantiantest = ossService.uploadFile(multipartFile, "tiantiantest");
    }
}
