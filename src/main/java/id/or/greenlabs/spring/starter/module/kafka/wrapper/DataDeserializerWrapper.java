package id.or.greenlabs.spring.starter.module.kafka.wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author krissadewo
 * @date 3/5/22 9:19 AM
 */
public class DataDeserializerWrapper implements Deserializer<DataDeserializerWrapper> {

    @Override
    public DataDeserializerWrapper deserialize(String s, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        DataDeserializerWrapper wrapper = null;

        try {
            wrapper = mapper.readValue(data, DataDeserializerWrapper.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wrapper;
    }
}
