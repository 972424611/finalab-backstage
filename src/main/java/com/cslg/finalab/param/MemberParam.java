package com.cslg.finalab.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Twilight
 * @date 19-1-16 下午1:10
 */
@Data
public class MemberParam {

    public Integer id;

    @NotBlank(message = "学号不能为空")
    public String stuId;

    @NotBlank(message = "姓名不能为空")
    public String name;

    @NotNull(message = "年级不能为空")
    @Min(value = 2000, message = "年龄不合法")
    public Integer grade;

    @NotBlank(message = "学院不能为空")
    public String college;

    @NotBlank(message = "专业不能为空")
    public String majors;

    public String className;

    @NotNull(message = "性别不能为空")
    @Min(value = 0, message = "性别不合法")
    @Max(value = 1, message = "性别不合法")
    public Integer gender;

    public String phone;

    @NotBlank(message = "qq不能为空")
    public String qq;

    public String email;

    @NotBlank(message = "部门不能为空")
    public String department;

    @NotBlank(message = "成员身份不能为空")
    public String level;

    @Length(max = 250, message = "remark过长")
    public String catchphrase;
}
