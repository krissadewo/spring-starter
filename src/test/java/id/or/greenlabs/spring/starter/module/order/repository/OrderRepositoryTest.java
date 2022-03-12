package id.or.greenlabs.spring.starter.module.order.repository;

import id.or.greenlabs.spring.starter.document.Order;
import id.or.greenlabs.spring.starter.document.Product;
import id.or.greenlabs.spring.starter.module.order.config.BaseTest;
import id.or.greenlabs.spring.starter.module.product.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import reactor.test.StepVerifier;

/**
 * @author krissadewo
 * @date 3/4/22 9:33 AM
 */
@ContextConfiguration(
    loader = AnnotationConfigContextLoader.class,
    classes = {
        ProductRepository.class,
        OrderRepository.class,
    })
class OrderRepositoryTest extends BaseTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @Test
    @org.junit.jupiter.api.Order(1)
    void saveProduct() {
        StepVerifier
            .create(productRepository.save(dummyData.product(new ObjectId().toHexString())))
            .thenConsumeWhile(result -> {
                product = result;

                return product.getId() != null;
            })
            .verifyComplete();
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void doOrder() {
        StepVerifier
            .create(orderRepository.save(dummyData.orders(product)))
            .thenConsumeWhile(result -> {
                return !result.isEmpty();
            })
            .verifyComplete();
    }

    @Test
    void findBy() {
        StepVerifier
            .create(orderRepository.findBy(new Order(), () -> PageRequest.of(0, 10)))
            .thenConsumeWhile(result -> {
                return result.getId() != null;
            })
            .verifyComplete();
    }
}
