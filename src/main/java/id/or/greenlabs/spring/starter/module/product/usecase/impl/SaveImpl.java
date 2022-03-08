package id.or.greenlabs.spring.starter.module.product.usecase.impl;

import id.or.greenlabs.spring.starter.assembler.dto.ProductDto;
import id.or.greenlabs.spring.starter.common.UseCase;
import id.or.greenlabs.spring.starter.module.product.port.ProductAdapter;
import id.or.greenlabs.spring.starter.module.product.usecase.Save;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 3/3/22 10:01 AM
 */
@UseCase("productSave")
@RequiredArgsConstructor
public class SaveImpl implements Save {

    private final ProductAdapter adapter;

    @Override
    public Mono<ProductDto> execute(ProductDto dto) {
        return adapter.save(dto);
    }
}
