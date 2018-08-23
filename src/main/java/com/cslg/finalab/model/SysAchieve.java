package com.cslg.finalab.model;

public class SysAchieve {

    private Integer id;

    private String stuId;

    private String whereabouts;

    private String catchphrase;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public String getWhereabouts() {
        return whereabouts;
    }

    public void setWhereabouts(String whereabouts) {
        this.whereabouts = whereabouts == null ? null : whereabouts.trim();
    }

    public String getCatchphrase() {
        return catchphrase;
    }

    public void setCatchphrase(String catchphrase) {
        this.catchphrase = catchphrase == null ? null : catchphrase.trim();
    }
}