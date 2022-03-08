package id.or.greenlabs.spring.starter.module.category.usecase;

import id.or.greenlabs.spring.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.spring.starter.module.category.config.BaseTest;
import id.or.greenlabs.spring.starter.module.category.repository.CategoryRepository;
import id.or.greenlabs.spring.starter.module.category.service.CategoryService;
import id.or.greenlabs.spring.starter.module.category.usecase.impl.FindImpl;
import id.or.greenlabs.spring.starter.module.category.usecase.impl.SaveImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import reactor.test.StepVerifier;

/**
 * @author krissadewo
 * @date 2/24/22 3:05 PM
 */
@ContextConfiguration(
    loader = AnnotationConfigContextLoader.class,
    classes = {
        CategoryRepository.class,
        CategoryService.class,
        FindImpl.class,
        SaveImpl.class,
    })
class CategoryUseCaseTest extends BaseTest {

    @Autowired
    private Find find;

    @Autowired
    private Save save;

    static CategoryDto category;

    @Order(1)
    @Test
    void save() {
        StepVerifier
            .create(save.execute(dummyData.categoryDto()))
            .thenConsumeWhile(result -> {
                category = result;

                return category.getId() != null;
            })
            .verifyComplete();
    }

    @Test
    @Order(2)
    void findBy() {
        StepVerifier
            .create(find.execute(category, 0, 10))
            .thenConsumeWhile(result -> {
                return category.getId() != null;
            })
            .verifyComplete();
    }
}
