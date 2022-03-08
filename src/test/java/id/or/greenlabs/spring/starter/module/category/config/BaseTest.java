package id.or.greenlabs.spring.starter.module.category.config;

import id.or.greenlabs.spring.starter.common.DummyData;
import id.or.greenlabs.spring.starter.config.MongoConfig;
import id.or.greenlabs.spring.starter.document.Category;
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
 * @date 2/24/22 2:06 PM
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
    @Order(-99)
    void init() {
        reactiveMongoTemplate.dropCollection(Category.class).block();
    }
}
