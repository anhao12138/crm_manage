package com.w.crmsystem.domain;

public class Systemdictionaryitem {
    private Integer sdiId;

    private String parent;

    private String name;

    private String intro;

    public Integer getSdiId() {
        return sdiId;
    }

    public void setSdiId(Integer sdiId) {
        this.sdiId = sdiId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}