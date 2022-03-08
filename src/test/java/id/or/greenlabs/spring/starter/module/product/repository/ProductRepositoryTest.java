package id.or.greenlabs.spring.starter.module.product.repository;

import id.or.greenlabs.spring.starter.document.Category;
import id.or.greenlabs.spring.starter.document.Product;
import id.or.greenlabs.spring.starter.module.category.repository.CategoryRepository;
import id.or.greenlabs.spring.starter.module.product.config.BaseTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import reactor.test.StepVerifier;

/**
 * @author krissadewo
 * @date 3/1/22 9:45 AM
 */
@ContextConfiguration(
    loader = AnnotationConfigContextLoader.class,
    classes = {
        CategoryRepository.class,
        ProductRepository.class
    })
class ProductRepositoryTest extends BaseTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    static Category category;

    static Product product;

    @Test
    @Order(1)
    void saveCategory() {
        StepVerifier
            .create(categoryRepository.save(dummyData.category()))
            .thenConsumeWhile(result -> {
                category = result;

                return category.getId() != null;
            })
            .verifyComplete();
    }

    @Test
    @Order(2)
    void saveProduct() {
        StepVerifier
            .create(productRepository.save(dummyData.product(category.getId())))
            .thenConsumeWhile(result -> {
                product = result;

                return product.getId() != null;
            })
            .verifyComplete();
    }

    @Test
    @Order(3)
    void update() {
        product.setName("TESLA");

        StepVerifier
            .create(productRepository.update(product))
            .thenConsumeWhile(result -> {
                return result.getModifiedCount() > 0;
            })
            .verifyComplete();
    }

    @Test
    @Order(4)
    void findById() {
        StepVerifier
            .create(productRepository.findBy(product))
            .thenConsumeWhile(result -> {
                return result.getCategory().getId() != null;
            })
            .verifyComplete();
    }

    @Test
    @Order(4)
    void findBy() {
        StepVerifier
            .create(productRepository.findBy(product, () -> PageRequest.of(0, 10)))
            .thenConsumeWhile(result -> {
                return result.getCategory().getId() != null;
            })
            .verifyComplete();
    }

    @Test
    @Order(5)
    void delete() {
        StepVerifier
            .create(productRepository.delete(product))
            .thenConsumeWhile(result -> {
                return result.getModifiedCount() > 0;
            })
            .verifyComplete();
    }

}
