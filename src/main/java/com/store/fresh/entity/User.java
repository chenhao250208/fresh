package com.store.fresh.entity;

public class User {
    private Integer id;

    private String name;

    private String mob;

    private String email;

    private Integer valid;

    private String pticket;

    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob == null ? null : mob.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getPticket() {
        return pticket;
    }

    public void setPticket(String pticket) {
        this.pticket = pticket == null ? null : pticket.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}