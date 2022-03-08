package id.or.greenlabs.spring.starter.module.kafka.port;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 3/5/22 9:22 AM
 */
public interface KafkaAdapter {

    Mono<Object> send(List<OrderDto> dtos);
}
