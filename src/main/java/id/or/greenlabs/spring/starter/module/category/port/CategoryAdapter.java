package id.or.greenlabs.spring.starter.module.category.port;

import id.or.greenlabs.spring.starter.assembler.dto.CategoryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 2/23/22 3:54 PM
 */
public interface CategoryAdapter {

    Mono<CategoryDto> save(CategoryDto categoryDto);

    Mono<String> update(CategoryDto categoryDto);

    Mono<String> delete(String id);

    Flux<CategoryDto> findBy(CategoryDto param, int offset, int limit);
}
