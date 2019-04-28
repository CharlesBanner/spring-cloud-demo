/**
 * BBD Service Inc
 * All Rights Reserved @2018
 */
package com.charles.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 基础表
 *
 * @author: GanZiB
 * Date: 2019-04-28
 * Time: 16:30
 */
@Data
@MappedSuperclass
public class BaseTable implements Serializable {

    /** 序列化id*/
    private static final long serialVersionUID = 5461030001811815808L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(updatable = false, insertable = false, columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date gmtCreate;

    @Column(updatable = false, insertable = false, columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date gmtUpdate;

    @Column(name = "createBy", columnDefinition = "bigint(20) not null DEFAULT 0 comment '创建人'")
    private Long createBy;

    @Column(name = "updateBy", columnDefinition = "bigint(20) not null DEFAULT 0 comment '更新人'")
    private Long updateBy;
}
