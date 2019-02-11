package com.cslg.finalab.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Twilight
 * @date 2019-02-11 09:08
 */
@Data
public class WinningParam {

    private int id;

    @NotBlank(message = "项目名称不能为空")
    private String name;

    @NotBlank(message = "获奖名称不能为空")
    private String awardName;

    @NotBlank(message = "获奖等级不能为空")
    private String awardLevel;

    private String awardTime;

    @NotBlank(message = "获奖成员不能为空")
    private String members;

    private String tutor;

    private String profile;
}
