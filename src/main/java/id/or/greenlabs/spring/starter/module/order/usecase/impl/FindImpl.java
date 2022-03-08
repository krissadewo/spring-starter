package id.or.greenlabs.spring.starter.module.order.usecase.impl;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import id.or.greenlabs.spring.starter.common.UseCase;
import id.or.greenlabs.spring.starter.module.order.usecase.Find;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

/**
 * @author krissadewo
 * @date 3/5/22 9:14 AM
 */
@UseCase("orderFind")
@RequiredArgsConstructor
public class FindImpl implements Find {

    @Override
    public Flux<OrderDto> execute(OrderDto dto, int limit, int offset) {
        return null;
    }
}
