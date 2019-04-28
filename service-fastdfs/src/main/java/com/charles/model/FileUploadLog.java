package com.charles.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * Description: 文件上传记录表
 *
 * @author: GanZiB
 * Date: 2019-04-28
 * Time: 16:30
 */
@Data
@Entity
@Table(name = "file_upload_log")
public class FileUploadLog extends BaseTable{

    private String fileName;

    private String fileType;

    private String userName;

}
