package id.or.greenlabs.spring.starter.module.product.usecase;

import id.or.greenlabs.spring.starter.assembler.dto.ProductDto;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 3/3/22 10:00 AM
 */
public interface Save {

    Mono<ProductDto> execute(ProductDto dto);
}
