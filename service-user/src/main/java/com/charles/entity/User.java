package com.charles.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-05-05
 * Time: 9:33
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "user")
public class User extends BaseTable{
    private static final long serialVersionUID = 5679668223593293172L;
    @Column(name = "username", columnDefinition = "varchar(50) not null comment '用户名'")
    private String username;

    @Column(name = "password", columnDefinition = "varchar(100) not null comment '密码'")
    private String password;

    @Column(name = "salt", columnDefinition = "varchar(10) not null comment '密码'")
    private String salt;

    @Column(name = "valid_date", columnDefinition = "datetime(0) default null comment '账户有效日期止'")
    private Date validDate;

    @Column(name = "tel", columnDefinition = "varchar(20) comment '手机号码'")
    private String tel;

    @Column(name = "email", columnDefinition = "varchar(50) comment '电子邮箱'")
    private String email;

    @Column(name = "province", columnDefinition = "varchar(50) comment '省份'")
    private String province;

    @Column(name = "type", columnDefinition = "int(11) not null comment '用户类型 1前台用户 2后台用户'")
    private Integer type;

    @Column(name = "userStatus", columnDefinition = "varchar(10) not null default '正常' comment '用户状态 正常，注销，冻结'")
    private String userStatus;

    @Column(name = "remark", columnDefinition = "varchar(100) comment '备注'")
    private String remark;

}
