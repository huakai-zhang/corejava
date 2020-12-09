package com.spring.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 春阳
 * @date 2020-12-09 11:50
 */
@Entity
@Table(name="t_member")
@Data
public class Member implements Serializable {
    @Id
    private Long id;
    private String name;
    @Column(name = "addr")
    private String address;
    private Integer age;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

    /*
        CREATE TABLE `t_member`  (

                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
                `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
                `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
                `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
        PRIMARY KEY (`id`) USING BTREE
        ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
     */
}
