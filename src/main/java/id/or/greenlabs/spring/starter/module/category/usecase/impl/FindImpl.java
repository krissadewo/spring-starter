package id.or.greenlabs.spring.starter.module.category.usecase.impl;

import id.or.greenlabs.spring.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.spring.starter.common.UseCase;
import id.or.greenlabs.spring.starter.module.category.port.CategoryAdapter;
import id.or.greenlabs.spring.starter.module.category.usecase.Find;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

/**
 * @author krissadewo
 * @date 2/24/22 3:05 PM
 */
@UseCase("categoryFind")
@RequiredArgsConstructor
public class FindImpl implements Find {

    public final CategoryAdapter adapter;

    @Override
    public Flux<CategoryDto> execute(CategoryDto param, int limit, int offset) {
        return adapter.findBy(param, limit, offset);
    }
}
