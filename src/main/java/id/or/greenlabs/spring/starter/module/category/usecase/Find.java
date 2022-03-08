package id.or.greenlabs.spring.starter.module.category.usecase;

import id.or.greenlabs.spring.starter.assembler.dto.CategoryDto;
import reactor.core.publisher.Flux;

/**
 * @author krissadewo
 * @date 2/24/22 3:03 PM
 */
public interface Find {

    Flux<CategoryDto> execute(CategoryDto param, int limit, int offset);
}
