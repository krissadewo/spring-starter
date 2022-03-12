package id.or.greenlabs.spring.starter.module.product.config;

import id.or.greenlabs.spring.starter.common.DummyData;
import id.or.greenlabs.spring.starter.config.MongoConfig;
import id.or.greenlabs.spring.starter.document.Category;
import id.or.greenlabs.spring.starter.document.Product;
import id.or.greenlabs.spring.starter.module.ApplicationTests;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author krissadewo
 * @date 3/1/22 9:46 AM
 */
@SpringBootTest
@EnableAutoConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class BaseTest {

    protected DummyData dummyData = new DummyData();

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    @Order(-99)
    void init() {
        reactiveMongoTemplate.dropCollection(Category.class).block();
        reactiveMongoTemplate.dropCollection(Product.class).block();
    }
}
