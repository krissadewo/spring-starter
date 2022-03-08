package id.or.greenlabs.spring.starter.module.stock.port;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import id.or.greenlabs.spring.starter.assembler.dto.StockDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 3/3/22 10:25 AM
 */
public interface StockAdapter {

    Mono<List<StockDto>> save(List<StockDto> dtos);

    Flux<StockDto> findBy(StockDto param, int limit, int offset);
}
