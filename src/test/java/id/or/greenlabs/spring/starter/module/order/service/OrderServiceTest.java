package id.or.greenlabs.spring.starter.module.order.service;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import id.or.greenlabs.spring.starter.module.order.config.BaseTest;
import id.or.greenlabs.spring.starter.module.order.repository.OrderRepository;
import id.or.greenlabs.spring.starter.module.product.repository.ProductRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import reactor.test.StepVerifier;

/**
 * @author krissadewo
 * @date 3/4/22 10:10 AM
 */
@ContextConfiguration(
    loader = AnnotationConfigContextLoader.class,
    classes = {
        ProductRepository.class,
        OrderRepository.class,
        OrderService.class
    })
class OrderServiceTest extends BaseTest {

    @Autowired
    private OrderService service;

    @Test
    @Order(1)
    void order() {
        StepVerifier
            .create(service.save(dummyData.orders()))
            .thenConsumeWhile(result -> {
                return result.size() > 0;
            })
            .verifyComplete();
    }

    @Test
    void findBy() {
        StepVerifier
            .create(service.findBy(new OrderDto(), 10, 0))
            .thenConsumeWhile(result -> {
                return result.getId() != null;
            })
            .verifyComplete();
    }
}
