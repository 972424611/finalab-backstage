package com.cslg.finalab.model;

public class SysWinning {
    private Integer id;

    private String name;

    private String awardName;

    private String awardLevel;

    private String awardImage;

    private String awardTime;

    private String members;

    private String tutor;

    private String profile;

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

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName == null ? null : awardName.trim();
    }

    public String getAwardLevel() {
        return awardLevel;
    }

    public void setAwardLevel(String awardLevel) {
        this.awardLevel = awardLevel == null ? null : awardLevel.trim();
    }

    public String getAwardImage() {
        return awardImage;
    }

    public void setAwardImage(String awardImage) {
        this.awardImage = awardImage == null ? null : awardImage.trim();
    }

    public String getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(String awardTime) {
        this.awardTime = awardTime == null ? null : awardTime.trim();
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members == null ? null : members.trim();
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor == null ? null : tutor.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }
}