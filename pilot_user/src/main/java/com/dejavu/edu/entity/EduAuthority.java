package com.dejavu.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author dejavu
 * @since 2021-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("edu_authority")
public class EduAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    private Integer id;

    private Integer userId;

    private String username;

    private String role;

    private String roleDesc;

    private String authority;

    private String authorityDesc;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer status;


}
