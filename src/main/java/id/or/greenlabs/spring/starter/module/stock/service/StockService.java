package id.or.greenlabs.spring.starter.module.stock.service;

import id.or.greenlabs.spring.starter.assembler.dto.StockDto;
import id.or.greenlabs.spring.starter.assembler.wrapper.StockWrapper;
import id.or.greenlabs.spring.starter.module.stock.port.StockAdapter;
import id.or.greenlabs.spring.starter.module.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author krissadewo
 * @date 3/3/22 10:25 AM
 */
@Service
@RequiredArgsConstructor
public class StockService implements StockAdapter {

    private final StockRepository repository;

    @Override
    public Mono<List<StockDto>> save(List<StockDto> dtos) {
        return repository.save(new ArrayList<>(new StockWrapper().toDocument(dtos)))
            .map(result -> new ArrayList<>(new StockWrapper().toDto(result)));
    }

    @Override
    public Flux<StockDto> findBy(StockDto param, int limit, int offset) {
        return repository.findBy(new StockWrapper().toParam(param), () -> PageRequest.of(offset, limit))
            .map(result -> {
                return new StockWrapper().toDto(result);
            });
    }
}
