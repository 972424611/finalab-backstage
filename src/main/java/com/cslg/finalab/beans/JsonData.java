package com.cslg.finalab.beans;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回给前段的Json格式
 * @author twilight
 */
@Data
public class JsonData {

    private boolean ret;

    private int code;

    private String msg;

    private Object data;

    public JsonData(boolean ret, int code) {
        // 默认成功的请求code为200，失败则为自定义code
        this.code = code;
        this.ret = ret;
    }

    public JsonData() {

    }

    public static JsonData success(Object object, String msg) {
        JsonData jsonData = new JsonData(true, 200);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    public static JsonData success(Object object) {
        JsonData jsonData = new JsonData(true, 200);
        jsonData.data = object;
        return jsonData;
    }

    public static JsonData success() {
        return new JsonData(true, 200);
    }

    public static JsonData fail(String msg, int code) {
        JsonData jsonData = new JsonData(false, code);
        jsonData.msg = msg;
        return jsonData;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ret", ret);
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }
}
