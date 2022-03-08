package id.or.greenlabs.spring.starter.module.category.usecase.impl;

import id.or.greenlabs.spring.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.spring.starter.common.UseCase;
import id.or.greenlabs.spring.starter.module.category.port.CategoryAdapter;
import id.or.greenlabs.spring.starter.module.category.usecase.Save;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 2/24/22 3:04 PM
 */
@UseCase("categorySave")
@RequiredArgsConstructor
public class SaveImpl implements Save {

    private final CategoryAdapter adapter;

    @Override
    public Mono<CategoryDto> execute(CategoryDto dto) {
        return adapter.save(dto);
    }
}
