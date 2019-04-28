package com.store.fresh.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private String userId;

    private String userName;

    private String password;

    private String email;

    private String tel;

    private String salt;

    private Integer valid;

    private Date createTime;

    private Date loginDate;

    private String loginIp;

    private String shippingAddress;

    private List<String> roleList;
}