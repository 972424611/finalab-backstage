package com.cslg.finalab.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Twilight
 * @date 19-2-2 下午7:38
 */
@Data
public class AchieveParam {

    public Integer id;

    @NotBlank(message = "学号不能为空")
    public String stuId;

    @NotBlank(message = "去向不能为空")
    public String whereabouts;

    public String catchphrase;


}
