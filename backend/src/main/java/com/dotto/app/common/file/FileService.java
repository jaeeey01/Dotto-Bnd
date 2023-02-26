package com.dotto.app.common.file;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    void upload(MultipartFile file, String fileName);
    void deleted(String fileName);
}
