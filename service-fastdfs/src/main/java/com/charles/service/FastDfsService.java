package com.charles.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-28
 * Time: 9:48
 */
public interface FastDfsService {

    String saveFile(MultipartFile file);
}
