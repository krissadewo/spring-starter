package id.or.greenlabs.spring.starter.module.order.service;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import id.or.greenlabs.spring.starter.assembler.wrapper.OrderWrapper;
import id.or.greenlabs.spring.starter.module.order.port.OrderAdapter;
import id.or.greenlabs.spring.starter.module.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author krissadewo
 * @date 3/4/22 9:09 AM
 */
@Service
@RequiredArgsConstructor
public class OrderService implements OrderAdapter {

    public final OrderRepository repository;

    @Override
    public Mono<List<OrderDto>> save(List<OrderDto> dtos) {
        return repository.save(new ArrayList<>(new OrderWrapper().toDocument(dtos)))
            .map(result -> new ArrayList<>(new OrderWrapper().toDto(result)));
    }

    @Override
    public Flux<OrderDto> findBy(OrderDto param, int limit, int offset) {
        return repository.findBy(new OrderWrapper().toParam(param), () -> PageRequest.of(offset, limit))
            .map(result -> {
                return new OrderWrapper().toDto(result);
            });
    }
}
