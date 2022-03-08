package id.or.greenlabs.spring.starter.module.order.port;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 3/4/22 9:12 AM
 */
public interface OrderAdapter {

    Mono<List<OrderDto>> save(List<OrderDto> dtos);

    Flux<OrderDto> findBy(OrderDto param, int limit, int offset);
}
