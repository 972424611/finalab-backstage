package com.cslg.finalab.common;

import com.alibaba.fastjson.JSON;
import com.cslg.finalab.exception.CustomException;
import com.cslg.finalab.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SpringExceptionResolver implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception exception) {
        String url = httpServletRequest.getRequestURL().toString();
        String defaultMsg = "System error";
        JsonData jsonData;
        if(exception instanceof CustomException) {
            jsonData = JsonData.fail(exception.getMessage(), ((CustomException) exception).getCode());
            LOGGER.error("自定义异常, url: {}", url, exception.getMessage());
        } else {
            // 系统异常自定义返回code为500
            jsonData = JsonData.fail(defaultMsg, 500);
            LOGGER.error("系统异常, url: {}", url, exception.getMessage());
        }
        PrintWriter writer = null;
        try {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            writer = httpServletResponse.getWriter();
            String msg = JSON.toJSONString(jsonData);
            if(StringUtils.isNotBlank(msg)) {
                writer.write(msg);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("系统异常, PrintWriter写入异常");
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
        return null;
    }


}
