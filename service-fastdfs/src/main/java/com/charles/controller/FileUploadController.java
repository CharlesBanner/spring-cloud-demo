package com.charles.controller;

import com.charles.dto.FastDFSFile;
import com.charles.util.FastDFSClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-26
 * Time: 13:44
 */
@Log4j2
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FastDFSClient fastDFSClient;

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            String path=saveFile(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path",
                    "file path url '" + path + "'");
        } catch (Exception e) {
            log.error("upload file failed",e);
        }
        return "redirect:/uploadStatus";
    }

    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] fileBuff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            fileBuff = new byte[len1];
            inputStream.read(fileBuff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, fileBuff, ext);
        try {
            //upload to fastdfs
            fileAbsolutePath = fastDFSClient.upload(file);
        } catch (Exception e) {
            log.error("upload file Exception!",e);
        }
        if (fileAbsolutePath==null) {
            log.error("upload file failed,please upload again!");
        }
        return fastDFSClient.getTrackerUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
    }


}
