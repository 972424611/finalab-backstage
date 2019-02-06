package com.cslg.finalab.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class ProjectParam {

    private Integer id;

    @NotBlank(message = "项目名称不能为空")
    private String name;

    private String version;

    @NotBlank(message = "项目类别不能为空")
    private String category;

    @NotBlank(message = "策划负责人不能为空")
    private String chiefPlanner;

    @NotBlank(message = "技术负责人不能为空")
    private String chiefArtisan;

    private String members;

    @NotBlank(message = "项目介绍不能为空")
    @Length(max = 500, message = "项目介绍过长")
    private String introduction;

}
