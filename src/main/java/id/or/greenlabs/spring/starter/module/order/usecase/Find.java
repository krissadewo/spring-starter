package id.or.greenlabs.spring.starter.module.order.usecase;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import reactor.core.publisher.Flux;

/**
 * @author krissadewo
 * @date 3/5/22 9:14 AM
 */
public interface Find {

    Flux<OrderDto> execute(OrderDto dto, int limit, int offset);
}
