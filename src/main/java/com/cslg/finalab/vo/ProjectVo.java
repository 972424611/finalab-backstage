package com.cslg.finalab.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProjectVo {

    private String name;

    private String category;

    private List<String> memberList;

    private String introduction;

    private String coverImage;

    private List<String> imageList;
}
