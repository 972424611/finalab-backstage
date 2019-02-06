package com.cslg.finalab.exception;

import com.cslg.finalab.enums.ProjectEnum;

public class ProjectException extends CustomException {

    public ProjectException(Integer code, String message) {
        super(code, message);
    }

    public ProjectException(ProjectEnum projectEnum) {
        this(projectEnum.getCode(), projectEnum.getMessage());
    }
}
