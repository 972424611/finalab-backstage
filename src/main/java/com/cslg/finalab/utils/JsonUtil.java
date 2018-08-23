package com.cslg.finalab.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 工具类, 处理json和pojo和List之间的转换
 * @author Twilight
 */
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 将对象转换成json字符串
     * @param data 对象类
     * @return String
     */
    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            logger.warn("parse object to Json exception, error: {}", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json转换为对象
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @param <T> 任意类型
     * @return pojo
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            logger.warn("parse Json to Object exception, Json:{}, TypeReference<T>:{}, error:{}", jsonData, beanType, e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换为pojo对象List
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @param <T> 任意类型
     * @return list
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            logger.warn("parse Json to List exception, Json:{}, TypeReference<T>:{}, error:{}", jsonData, beanType, e);
            e.printStackTrace();
        }
        return null;
    }
}
