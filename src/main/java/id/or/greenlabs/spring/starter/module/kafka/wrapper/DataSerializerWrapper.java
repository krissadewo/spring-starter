package id.or.greenlabs.spring.starter.module.kafka.wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author krissadewo
 * @date 3/5/22 9:20 AM
 */
public class DataSerializerWrapper<T> implements Serializer<T> {

    @Override
    public byte[] serialize(String s, Object data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retVal;
    }
}
