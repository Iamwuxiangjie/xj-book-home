package com.xj.book.home.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperUtils {

    private static ObjectMapper mapper = new ObjectMapper(new JsonFactory());

    static {
        //mapper.registerModule(new Hibernate5Module());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public static Object readObject(String json, Class model) throws Exception {
        return mapper.readValue(json, model);
    }

    public static List readObjects(String json, Class model) throws Exception {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(model, model);
        return mapper.readValue(json, javaType);
    }

    public static Boolean isValidJson(String json) {
        try {
            mapper.readTree(json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String writeMap(Map<String, Object> map) {
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String writeObject(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String originalSuccess(Object... body) {
        return writeMap(returnModel(200, body));
    }

    public static String originalFail(Object... body) {
        return writeMap(returnModel(406, body));
    }

    public static String originalForward(Object... body) {
        return writeMap(returnModel(302, body));
    }

    public static String originalDenied(Object... body) {
        return writeMap(returnModel(403, body));
    }

    public static String originalError(Object... body) {
        return writeMap(returnModel(500, body));
    }

    public static String originalNotFound(Object... body) {
        return writeMap(returnModel(404, body));
    }

    public static Map<String, Object> success(Object... body) {
        return returnModel(200, body);
    }

    public static Map<String, Object> fail(Object... body) {
        return returnModel(406, body);
    }

    public static Map<String, Object> forward(Object... body) {
        return returnModel(302, body);
    }

    public static Map<String, Object> denied(Object... body) {
        return returnModel(403, body);
    }

    public static Map<String, Object> error(Object... body) {
        return returnModel(500, body);
    }

    private static Map<String, Object> returnModel(Integer status, Object[] body) {
        Map<String, Object> map = new HashMap();
        map.put("status", status);
        map.put("bodyText", "");
        if (body.length == 1) {
            map.put("body", body[0]);
            return map;
        }
        for (int index = 0; index < body.length; index += 2) {
            map.put(body[index].toString(), body[index + 1]);
        }
        return map;
    }
}
