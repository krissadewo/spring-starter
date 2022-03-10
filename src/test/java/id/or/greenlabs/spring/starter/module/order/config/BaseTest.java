package id.or.greenlabs.spring.starter.module.order.config;

import id.or.greenlabs.spring.starter.common.DummyData;
import id.or.greenlabs.spring.starter.document.Order;
import id.or.greenlabs.spring.starter.document.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

/**
 * @author krissadewo
 * @date 3/4/22 9:38 AM
 */
@SpringBootTest
@EnableAutoConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
public class BaseTest {

    protected DummyData dummyData = new DummyData();

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    @org.junit.jupiter.api.Order(-99)
    void init() {
        reactiveMongoTemplate.dropCollection(Product.class).block();
        reactiveMongoTemplate.dropCollection(Order.class).block();
    }
}
