package id.or.greenlabs.spring.starter.module.category.service;

import id.or.greenlabs.spring.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.spring.starter.common.StatusCode;
import id.or.greenlabs.spring.starter.module.category.config.BaseTest;
import id.or.greenlabs.spring.starter.module.category.repository.CategoryRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import reactor.test.StepVerifier;

/**
 * @author krissadewo
 * @date 2/24/22 2:51 PM
 */
@ContextConfiguration(
    loader = AnnotationConfigContextLoader.class,
    classes = {
        CategoryRepository.class,
        CategoryService.class
    })
class CategoryServiceTest extends BaseTest {

    @Autowired
    private CategoryService service;

    private CategoryDto category;

    @Test
    @Order(1)
    void save() {
        StepVerifier
            .create(service.save(dummyData.categoryDto()))
            .thenConsumeWhile(result -> {
                category = result;

                return category.getId() != null;
            })
            .verifyComplete();
    }

    @Test
    @Order(2)
    void update() {
        category.setName("SEDAN");

        StepVerifier
            .create(service.update(category))
            .thenConsumeWhile(result -> {
                return result.equals(StatusCode.OPERATION_SUCCESS);
            })
            .verifyComplete();
    }

    @Test
    @Order(3)
    void delete() {
        StepVerifier
            .create(service.delete(category.getId()))
            .thenConsumeWhile(result -> {
                return result.equals(StatusCode.OPERATION_SUCCESS);
            })
            .verifyComplete();
    }

    @Test
    @Order(4)
    void findBy() {
        StepVerifier
            .create(service.findBy(category, 0, 10))
            .thenConsumeWhile(result -> {
                return result.getId() != null;
            })
            .verifyComplete();
    }
}
