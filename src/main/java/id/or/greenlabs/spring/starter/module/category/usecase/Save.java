package id.or.greenlabs.spring.starter.module.category.usecase;

import id.or.greenlabs.spring.starter.assembler.dto.CategoryDto;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 2/24/22 3:03 PM
 */
public interface Save {

    Mono<CategoryDto> execute(CategoryDto dto);
}
