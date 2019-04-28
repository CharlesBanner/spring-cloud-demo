package com.charles.controller;

import com.charles.common.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-28
 * Time: 10:06
 */
@RestController
public class TestController {

    @Autowired
    private FastDFSClient fastDFSClient;

    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile file) throws IOException {
        return fastDFSClient.uploadFile(file);
    }


}
