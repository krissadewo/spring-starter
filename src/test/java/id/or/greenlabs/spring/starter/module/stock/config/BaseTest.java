package id.or.greenlabs.spring.starter.module.stock.config;

import id.or.greenlabs.spring.starter.common.DummyData;
import id.or.greenlabs.spring.starter.config.MongoConfig;
import id.or.greenlabs.spring.starter.document.Category;
import id.or.greenlabs.spring.starter.document.Product;
import id.or.greenlabs.spring.starter.document.Stock;
import id.or.greenlabs.spring.starter.module.stock.config.ApplicationTests;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
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
 * @date 3/4/22 8:54 AM
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

    protected DummyData dummyData;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    @Order(-99)
    void init() {
        reactiveMongoTemplate.dropCollection(Stock.class).block();
        reactiveMongoTemplate.dropCollection(Product.class).block();
    }
}
