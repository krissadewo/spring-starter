package id.or.greenlabs.spring.starter.module.kafka.service;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import id.or.greenlabs.spring.starter.module.kafka.port.KafkaAdapter;
import id.or.greenlabs.spring.starter.module.kafka.wrapper.DataSerializerWrapper;
import id.or.greenlabs.spring.starter.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 3/5/22 9:37 AM
 */
@Service
@RequiredArgsConstructor
public class KafkaService implements KafkaAdapter {

    private final ReactiveKafkaProducerTemplate<String, Object> reactiveKafkaProducerTemplate;

    @Override
    public Mono<Object> send(List<OrderDto> dtos) {
        return reactiveKafkaProducerTemplate.send("order", JsonUtil.toJson(dtos))
            .flatMap(result -> {
                if (result.exception() == null) {
                    return Mono.just(result.recordMetadata().offset());
                }

                return Mono.error(result.exception());
            });
    }
}
