package id.or.greenlabs.spring.starter.module.product.service;

import id.or.greenlabs.spring.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.spring.starter.assembler.dto.ProductDto;
import id.or.greenlabs.spring.starter.common.StatusCode;
import id.or.greenlabs.spring.starter.module.category.repository.CategoryRepository;
import id.or.greenlabs.spring.starter.module.category.service.CategoryService;
import id.or.greenlabs.spring.starter.module.product.config.BaseTest;
import id.or.greenlabs.spring.starter.module.product.repository.ProductRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import reactor.test.StepVerifier;

/**
 * @author krissadewo
 * @date 3/1/22 12:07 PM
 */
@ContextConfiguration(
    loader = AnnotationConfigContextLoader.class,
    classes = {
        CategoryRepository.class,
        CategoryService.class,
        ProductRepository.class,
        ProductService.class
    })
class ProductServiceTest extends BaseTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private ProductDto product;

    private CategoryDto category;

    @Test
    @Order(1)
    void saveCategory() {
        StepVerifier
            .create(categoryService.save(dummyData.categoryDto()))
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
            .create(productService.save(dummyData.productDto(category)))
            .thenConsumeWhile(result -> {
                product = result;

                return product.getId() != null;
            })
            .verifyComplete();
    }

    @Test
    @Order(3)
    void update() {
        product.setName("CITY");

        StepVerifier
            .create(productService.update(product))
            .thenConsumeWhile(result -> {
                return result.equals(StatusCode.OPERATION_SUCCESS);
            })
            .verifyComplete();
    }

    @Test
    @Order(4)
    void findBy() {
        StepVerifier
            .create(productService.findBy(product, 10, 0))
            .thenConsumeWhile(result -> {
                return result.getId() != null && result.getCategory().getId() != null;
            })
            .verifyComplete();
    }

    @Test
    @Order(5)
    void delete() {
        StepVerifier
            .create(productService.delete(product.getId()))
            .thenConsumeWhile(result -> {
                return result.equals(StatusCode.OPERATION_SUCCESS);
            })
            .verifyComplete();
    }

}
