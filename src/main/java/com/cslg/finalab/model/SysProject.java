package com.cslg.finalab.model;

public class SysProject {
    private Integer id;

    private String name;

    private String version;

    private String category;

    private String chiefPlanner;

    private String chiefArtisan;

    private String members;

    private String coverImage;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getChiefPlanner() {
        return chiefPlanner;
    }

    public void setChiefPlanner(String chiefPlanner) {
        this.chiefPlanner = chiefPlanner == null ? null : chiefPlanner.trim();
    }

    public String getChiefArtisan() {
        return chiefArtisan;
    }

    public void setChiefArtisan(String chiefArtisan) {
        this.chiefArtisan = chiefArtisan == null ? null : chiefArtisan.trim();
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members == null ? null : members.trim();
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage == null ? null : coverImage.trim();
    }
}