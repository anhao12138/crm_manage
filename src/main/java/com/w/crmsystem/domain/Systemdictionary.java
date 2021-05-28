package com.w.crmsystem.domain;

public class Systemdictionary {
    private Integer sdId;

    private String sn;

    private String name;

    private String intro;

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
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