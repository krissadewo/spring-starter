package id.or.greenlabs.spring.starter.module.product.usecase;

import id.or.greenlabs.spring.starter.assembler.dto.ProductDto;
import reactor.core.publisher.Flux;

/**
 * @author krissadewo
 * @date 3/3/22 9:59 AM
 */
public interface Find {

    Flux<ProductDto> execute(ProductDto param, int limit, int offset);
}
