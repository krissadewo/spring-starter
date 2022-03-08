package id.or.greenlabs.spring.starter.module.kafka.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import id.or.greenlabs.spring.starter.module.stock.usecase.Save;
import id.or.greenlabs.spring.starter.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.List;

/**
 * @author krissadewo
 * @date 3/5/22 9:21 AM
 */
@Component
@RequiredArgsConstructor
public class OrderListener {

    private final ReceiverOptions<String, Object> receiverOptions;

    private final Save save;

    private ReceiverOptions<String, Object> receiverOptions() {
        return receiverOptions.subscription(Collections.singleton("order"));
    }

    @Bean
    void listenOrder() {
        KafkaReceiver.create(receiverOptions())
            .receive()
            .flatMap(record -> {
                ReceiverOffset offset = record.receiverOffset();
                List<OrderDto> dtos = JsonUtil.fromJson((String) record.value(), new TypeReference<>() {
                });

                return Mono.just(save.execute(dtos));
            })
            .subscribe();
    }

}
