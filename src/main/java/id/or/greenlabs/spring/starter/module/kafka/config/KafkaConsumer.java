package id.or.greenlabs.spring.starter.module.kafka.config;

import id.or.greenlabs.spring.starter.module.kafka.wrapper.DataDeserializerWrapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.IsolationLevel;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author krissadewo
 * @date 3/5/22 9:18 AM
 */
@Configuration
public class KafkaConsumer {

    @Value("${kafka.servers}")
    private String kafkaServers;

    @Value("${kafka.group.order}")
    private String groupOrder;

    private final Environment environment;

    public KafkaConsumer(Environment environment) {
        this.environment = environment;
    }

    @Bean
    ReceiverOptions<String, Object> receiverOptions() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, environment.getProperty("spring.application.name"));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupOrder);
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, IsolationLevel.READ_COMMITTED.toString().toLowerCase(Locale.ROOT));
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        return ReceiverOptions.create(props);
    }
}
