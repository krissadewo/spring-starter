package id.or.greenlabs.spring.starter.module.category.service;

import id.or.greenlabs.spring.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.spring.starter.assembler.wrapper.CategoryWrapper;
import id.or.greenlabs.spring.starter.common.StatusCode;
import id.or.greenlabs.spring.starter.module.category.port.CategoryAdapter;
import id.or.greenlabs.spring.starter.module.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author krissadewo
 * @date 2/23/22 3:59 PM
 */
@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryAdapter {

    private final CategoryRepository repository;

    @Override
    public Mono<CategoryDto> save(CategoryDto dto) {
        return repository.save(new CategoryWrapper().toDocument(dto))
            .map(result -> new CategoryWrapper().toDto(result));
    }

    @Override
    public Mono<String> update(CategoryDto dto) {
        return repository.update(new CategoryWrapper().toDocument(dto))
            .map(result -> {
                if (result.getModifiedCount() > 0) {
                    return StatusCode.OPERATION_SUCCESS;
                }

                return StatusCode.OPERATION_FAILED;
            });
    }

    @Override
    public Mono<String> delete(String id) {
        return repository.update(new CategoryWrapper().toDocument(new CategoryDto(id)))
            .map(result -> {
                if (result.getModifiedCount() > 0) {
                    return StatusCode.OPERATION_SUCCESS;
                }

                return StatusCode.OPERATION_FAILED;
            });
    }

    @Override
    public Flux<CategoryDto> findBy(CategoryDto param, int offset, int limit) {
        return repository.findBy(new CategoryWrapper().toParam(param), () -> PageRequest.of(offset, limit))
            .map(result -> {
                return new CategoryWrapper().toDto(result);
            });
    }
}
