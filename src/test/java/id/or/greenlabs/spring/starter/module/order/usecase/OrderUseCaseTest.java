package id.or.greenlabs.spring.starter.module.order.usecase;

import id.or.greenlabs.spring.starter.module.kafka.config.KafkaConsumer;
import id.or.greenlabs.spring.starter.module.kafka.config.KafkaProducer;
import id.or.greenlabs.spring.starter.module.kafka.listener.OrderListener;
import id.or.greenlabs.spring.starter.module.kafka.service.KafkaService;
import id.or.greenlabs.spring.starter.module.order.config.BaseTest;
import id.or.greenlabs.spring.starter.module.order.repository.OrderRepository;
import id.or.greenlabs.spring.starter.module.order.service.OrderService;
import id.or.greenlabs.spring.starter.module.order.usecase.impl.OrderProductImpl;
import id.or.greenlabs.spring.starter.module.stock.repository.StockRepository;
import id.or.greenlabs.spring.starter.module.stock.service.StockService;
import id.or.greenlabs.spring.starter.module.stock.usecase.impl.SaveImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import reactor.test.StepVerifier;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author krissadewo
 * @date 3/5/22 9:32 AM
 */
@ContextConfiguration(
    loader = AnnotationConfigContextLoader.class,
    classes = {
        KafkaProducer.class,
        KafkaConsumer.class,
        KafkaService.class,
        SaveImpl.class,
        StockRepository.class,
        StockService.class,
        OrderListener.class,
        OrderRepository.class,
        OrderService.class,
        OrderProductImpl.class
    })
class OrderUseCaseTest extends BaseTest {

    @Autowired
    private OrderProduct orderProduct;

    private CountDownLatch latch = new CountDownLatch(1);

    @Test
    @Order(1)
    void order() {
        StepVerifier
            .create(orderProduct.execute(dummyData.orderDtos()))
            .thenConsumeWhile(Objects::nonNull)
            .verifyComplete();
    }

    @Test
    @Order(2)
    void listen() throws InterruptedException {
        latch.await(5, TimeUnit.SECONDS);
    }
}
