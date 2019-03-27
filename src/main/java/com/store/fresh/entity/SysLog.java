package com.store.fresh.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysLog {
    private Long id;

    private Integer type;

    private String ip;

    private String userId;

    private String title;

    private String url;

    private String params;

    private String rtv;

    private Date createTime;

}