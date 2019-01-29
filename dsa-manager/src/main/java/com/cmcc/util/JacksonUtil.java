package com.cmcc.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class JacksonUtil 
 * 
 * json字符与对像转换 
 *
 */  
public final class JacksonUtil {

    private static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
  
    public static ObjectMapper objectMapper;

    public static <T> T readValue(String jsonStr, Class<T> valueType) {  
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
  
        try {  
            return objectMapper.readValue(jsonStr, valueType);  
        } catch (Exception e) {
            logger.error("jackson read value error: ", e);
        }  
  
        return null;  
    }  

    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef){
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
  
        try {  
            return objectMapper.readValue(jsonStr, valueTypeRef);  
        } catch (Exception e) {
            logger.error("jackson read value error: ", e);
        }  
  
        return null;  
    }

    public static <T> T readValueBackslash(String jsonStr, TypeReference<T> valueTypeRef){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            logger.error("jackson read value error: ", e);
        }

        return null;
    }

    public static String toJson(Object object) {
        if (objectMapper == null) {  
            objectMapper = new ObjectMapper();  
        }  
  
        try {  
            return objectMapper.writeValueAsString(object);  
        } catch (Exception e) {
            logger.error("jackson to json error: ", e);
        }  
  
        return null;  
    }  
  
}