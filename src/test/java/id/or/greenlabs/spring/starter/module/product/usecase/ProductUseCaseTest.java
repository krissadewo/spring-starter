package id.or.greenlabs.spring.starter.module.product.usecase;

import id.or.greenlabs.spring.starter.assembler.dto.ProductDto;
import id.or.greenlabs.spring.starter.module.product.config.BaseTest;
import id.or.greenlabs.spring.starter.module.product.repository.ProductRepository;
import id.or.greenlabs.spring.starter.module.product.service.ProductService;
import id.or.greenlabs.spring.starter.module.product.usecase.impl.FindByIdImpl;
import id.or.greenlabs.spring.starter.module.product.usecase.impl.FindImpl;
import id.or.greenlabs.spring.starter.module.product.usecase.impl.SaveImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import reactor.test.StepVerifier;

/**
 * @author krissadewo
 * @date 3/3/22 10:03 AM
 */
@ContextConfiguration(
    loader = AnnotationConfigContextLoader.class,
    classes = {
        ProductRepository.class,
        ProductService.class,
        FindImpl.class,
        FindByIdImpl.class,
        SaveImpl.class
    })
class ProductUseCaseTest extends BaseTest {

    @Autowired
    private Find find;

    @Autowired
    private FindById findById;

    @Autowired
    private Save save;

    private static ProductDto product;

    @Order(1)
    @Test
    void save() {
        StepVerifier
            .create(save.execute(dummyData.productDto(new ObjectId().toHexString())))
            .thenConsumeWhile(result -> {
                product = result;

                return product.getId() != null;
            })
            .verifyComplete();
    }

    @Order(2)
    @Test
    void find() {
        StepVerifier
            .create(find.execute(product, 10, 0))
            .thenConsumeWhile(result -> {
                return result.getId() != null;
            })
            .verifyComplete();
    }

    @Order(2)
    @Test
    void findById() {
        StepVerifier
            .create(findById.execute(product.getId()))
            .thenConsumeWhile(result -> {
                return result.getId() != null;
            })
            .verifyComplete();
    }
}
