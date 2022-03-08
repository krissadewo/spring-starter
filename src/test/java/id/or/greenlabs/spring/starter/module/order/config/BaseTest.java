package id.or.greenlabs.spring.starter.module.order.config;

import id.or.greenlabs.spring.starter.common.DummyData;
import id.or.greenlabs.spring.starter.config.MongoConfig;
import id.or.greenlabs.spring.starter.document.Order;
import id.or.greenlabs.spring.starter.document.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

/**
 * @author krissadewo
 * @date 3/4/22 9:38 AM
 */
@SpringBootTest(
    classes = {
        ApplicationTests.class
    }
)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import(MongoConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
public class BaseTest {

    @Autowired
    protected DummyData dummyData;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    @org.junit.jupiter.api.Order(-99)
    void init() {
        reactiveMongoTemplate.dropCollection(Product.class).block();
        reactiveMongoTemplate.dropCollection(Order.class).block();
    }
}
