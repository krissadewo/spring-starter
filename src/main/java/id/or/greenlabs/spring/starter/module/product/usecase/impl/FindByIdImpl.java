package id.or.greenlabs.spring.starter.module.product.usecase.impl;

import id.or.greenlabs.spring.starter.assembler.dto.ProductDto;
import id.or.greenlabs.spring.starter.common.UseCase;
import id.or.greenlabs.spring.starter.module.product.port.ProductAdapter;
import id.or.greenlabs.spring.starter.module.product.usecase.FindById;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 3/3/22 10:02 AM
 */
@UseCase("productFindById")
@RequiredArgsConstructor
public class FindByIdImpl implements FindById {

    private final ProductAdapter adapter;

    @Override
    public Mono<ProductDto> execute(String id) {
        return adapter.findBy(id);
    }
}
