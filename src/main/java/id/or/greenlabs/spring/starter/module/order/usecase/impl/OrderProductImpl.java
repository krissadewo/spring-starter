package id.or.greenlabs.spring.starter.module.order.usecase.impl;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import id.or.greenlabs.spring.starter.common.UseCase;
import id.or.greenlabs.spring.starter.module.kafka.port.KafkaAdapter;
import id.or.greenlabs.spring.starter.module.order.port.OrderAdapter;
import id.or.greenlabs.spring.starter.module.order.usecase.OrderProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 3/5/22 9:14 AM
 */
@UseCase
@RequiredArgsConstructor
public class OrderProductImpl implements OrderProduct {

    private final OrderAdapter orderAdapter;

    private final KafkaAdapter kafkaAdapter;

    @Override
    public Mono<Object> execute(List<OrderDto> dtos) {
        return orderAdapter.save(dtos)
            .flatMap(result -> {
                if (!result.isEmpty()) {
                    return kafkaAdapter.send(dtos);
                }

                return Mono.empty();
            });
    }
}
