package com.charles.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-26
 * Time: 13:42
 */
@Data
public class FastDFSFile {

    private String name;

    private byte[] content;

    private String ext;

    private String md5;

    private String author;


    public FastDFSFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }
}
