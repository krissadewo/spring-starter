package id.or.greenlabs.spring.starter.module.stock.usecase;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import id.or.greenlabs.spring.starter.assembler.dto.StockDto;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author krissadewo
 * @date 3/8/22 7:12 AM
 */
public interface Save {

    Mono<List<StockDto>> execute(List<OrderDto> dtos);
}
