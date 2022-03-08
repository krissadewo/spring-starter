package id.or.greenlabs.spring.starter.module.order.usecase;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 3/5/22 9:13 AM
 */
public interface OrderProduct {

    Mono<Object> execute(List<OrderDto> dtos);
}
