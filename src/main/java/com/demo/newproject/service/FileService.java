package com.demo.newproject.service;

import com.demo.newproject.util.newProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.UUID;

@Service
public class FileService {

    @Value("${file.path-centos}")
    String filePath;

    @Value("${file.url-web}")
    String fileUrl;

    @Value("${file.port}")
    String filePort;

    @Value("${file.suffix-centos}")
    String fileSuffix;


    @Autowired
    UserService userService;

    @Transactional(rollbackFor = Exception.class)
    public int uploadFile(MultipartFile multipartFile, Integer userId) throws IOException {
        if(multipartFile == null || multipartFile.isEmpty()) {
            throw new FileNotFoundException();
        }
        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if(!newProjectUtil.isPic(suffixName)) {
            throw new FileSystemException("not correct type");
        }
        fileName = UUID.randomUUID() + suffixName;
        int res = userService.updateHead_url("http://" + fileUrl + ":" + filePort + fileSuffix
                + fileName, userId);
        File dest = new File(filePath + fileName);
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        multipartFile.transferTo(dest);
        return res;
    }
}
