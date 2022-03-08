package id.or.greenlabs.spring.starter.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * @author krissadewo
 * @date 3/5/22 10:08 AM
 */
public class JsonUtil {

    private static ObjectMapper mapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        return objectMapper;
    }

    public static String toJson(Object object) {
        try {
            return mapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] toJsonByte(Object object) {
        try {
            return mapper().writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String toJson(Object object, boolean pretty) {
        ObjectMapper mapper = mapper();

        if (pretty) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return mapper().readValue(json, classOfT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T fromJson(Object object, Class<T> classOfT) {
        return mapper().convertValue(object, classOfT);
    }

    public static <T> T fromJson(byte[] object, Class<T> classOfT) {
        return mapper().convertValue(object, classOfT);
    }

    public static <T> T fromJson(String json, TypeReference<T> valueTypeRef) {
        ObjectMapper mapper = mapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
