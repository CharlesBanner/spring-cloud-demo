/**
 * BBD Service Inc
 * All Rights Reserved @2018
 */
package com.charles.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础表
 *
 * @author weizhang
 * @version $Id: BaseTable.java, v 0.1 2018年6月22日 下午3:59:38 weizhang@bbdservice.com Exp $
 */
@Data
@MappedSuperclass
public class BaseTable implements Serializable {

    private static final long serialVersionUID = 8874958500781316445L;
    /** 序列化id*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(updatable = false, insertable = false, columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date gmtCreate;


    @Column(updatable = false, insertable = false, columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date gmtUpdate;

    @Column(updatable = false, insertable = false, columnDefinition = "BIGINT(20) DEFAULT 0 comment '创建人ID'")
    private Long createBy;

    @Column(updatable = false, insertable = false, columnDefinition = "BIGINT(20) DEFAULT 0 comment '修改人ID'")
    private Long updateBy;
}
