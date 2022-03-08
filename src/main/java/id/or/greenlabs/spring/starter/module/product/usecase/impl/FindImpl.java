package id.or.greenlabs.spring.starter.module.product.usecase.impl;

import id.or.greenlabs.spring.starter.assembler.dto.ProductDto;
import id.or.greenlabs.spring.starter.common.UseCase;
import id.or.greenlabs.spring.starter.module.product.port.ProductAdapter;
import id.or.greenlabs.spring.starter.module.product.usecase.Find;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * @author krissadewo
 * @date 3/3/22 10:01 AM
 */
@UseCase("productFind")
@RequiredArgsConstructor
public class FindImpl implements Find {

    private final ProductAdapter adapter;

    @Override
    public Flux<ProductDto> execute(ProductDto param, int limit, int offset) {
        return adapter.findBy(param, limit, offset);
    }
}
