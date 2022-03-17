package id.or.greenlabs.spring.starter.module.product.service;

import id.or.greenlabs.spring.starter.assembler.dto.ProductDto;
import id.or.greenlabs.spring.starter.assembler.wrapper.ProductWrapper;
import id.or.greenlabs.spring.starter.common.StatusCode;
import id.or.greenlabs.spring.starter.module.product.port.ProductAdapter;
import id.or.greenlabs.spring.starter.module.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 3/1/22 11:21 AM
 */
@Service
@RequiredArgsConstructor
public class ProductService implements ProductAdapter {

    private final ProductRepository repository;

    @Override
    public Mono<ProductDto> save(ProductDto dto) {
        return repository.save(new ProductWrapper().toDocument(dto))
            .map(result -> new ProductWrapper().toDto(result));
    }

    @Override
    public Mono<String> update(ProductDto dto) {
        return repository.update(new ProductWrapper().toDocument(dto))
            .map(result -> {
                if (result.getModifiedCount() > 0) {
                    return StatusCode.OPERATION_SUCCESS;
                }

                return StatusCode.OPERATION_FAILED;
            });
    }

    @Override
    public Mono<ProductDto> findBy(String id) {
        return repository.findBy(new ProductWrapper().toParam(new ProductDto(id)))
            .map(result -> {
                return new ProductWrapper().toDto(result);
            });
    }

    @Override
    public Mono<String> delete(String id) {
        return repository.delete(new ProductWrapper().toDocument(new ProductDto(id)))
            .map(updateResult -> {
                if (updateResult.getModifiedCount() > 0) {
                    return StatusCode.OPERATION_SUCCESS;
                }

                return StatusCode.OPERATION_FAILED;
            });
    }

    @Override
    public Flux<ProductDto> findBy(ProductDto param, int limit, int offset) {
        return repository.findBy(new ProductWrapper().toParam(param), () -> PageRequest.of(offset, limit))
            .map(result -> {
                return new ProductWrapper().toDto(result);
            });
    }
}
