package id.or.greenlabs.spring.starter.module.product.port;

import id.or.greenlabs.spring.starter.assembler.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 3/1/22 11:22 AM
 */
public interface ProductAdapter {

    Mono<ProductDto> save(ProductDto dto);

    Mono<String> update(ProductDto dto);

    Mono<ProductDto> findBy(String id);

    Mono<String> delete(String id);

    Flux<ProductDto> findBy(ProductDto param, int limit, int offset);
}
