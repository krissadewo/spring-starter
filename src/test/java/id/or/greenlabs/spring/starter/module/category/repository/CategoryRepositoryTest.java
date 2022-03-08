package id.or.greenlabs.spring.starter.module.category.repository;

import id.or.greenlabs.spring.starter.document.Category;
import id.or.greenlabs.spring.starter.module.category.config.BaseTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import reactor.test.StepVerifier;

/**
 * @author krissadewo
 * @date 2/24/22 2:05 PM
 */
@ContextConfiguration(
    loader = AnnotationConfigContextLoader.class,
    classes = {
        CategoryRepository.class,
    })
class CategoryRepositoryTest extends BaseTest {

    @Autowired
    private CategoryRepository repository;

    static Category category;

    @Test
    @Order(1)
    void save() {
        StepVerifier
            .create(repository.save(dummyData.category()))
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
            .create(repository.update(category))
            .thenConsumeWhile(result -> {
                return result.getModifiedCount() > 0;
            })
            .verifyComplete();
    }

    @Test
    @Order(3)
    void delete() {
        StepVerifier
            .create(repository.delete(category))
            .thenConsumeWhile(result -> {
                return result.getModifiedCount() > 0;
            })
            .verifyComplete();
    }

    @Test
    @Order(4)
    void find() {
        StepVerifier
            .create(repository.findBy(category, () -> PageRequest.of(0, 10)))
            .thenConsumeWhile(result -> {
                return category.getId() != null;
            })
            .verifyComplete();
    }
}
