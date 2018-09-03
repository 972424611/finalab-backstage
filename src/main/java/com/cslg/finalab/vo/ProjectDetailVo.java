package com.cslg.finalab.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDetailVo {

    private List<String> memberList;

    private String category;

    private String introduction;

    private List<String> imageList;

}
