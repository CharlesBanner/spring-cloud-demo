package com.charles.controller;

import com.charles.common.FastDFSClient;
import com.charles.model.EsCompCache;
import com.charles.repository.EsCompApiRepository;
import com.charles.repository.EsCompCacheRepository;
import com.charles.service.FastDfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

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
    @Autowired
    private EsCompCacheRepository esCompCacheRepository;
    @Autowired
    private FastDfsService fastDfsService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile file) throws IOException {
        return fastDFSClient.uploadFile(file);
    }

    @PostMapping("/test")
    public String test(@RequestParam("file")MultipartFile file) throws IOException {
        return fastDfsService.saveFile(file);
    }

    @GetMapping("/save")
    public String save(){
        EsCompCache esCompCache = new EsCompCache();
        esCompCache.setGmtCreate(new Date());
        esCompCache.setId(getStr());
        esCompCache.setGmtUpdate(new Date());
        esCompCache.setVal(getStr());
        esCompCache.setValidDate(new Date());
        esCompCacheRepository.save(esCompCache);
        return esCompCache.toString();
    }

    private String  getStr(){
        return UUID.randomUUID().toString();
    }
}
