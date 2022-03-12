package id.or.greenlabs.spring.starter.module.category.config;

import id.or.greenlabs.spring.starter.common.DummyData;
import id.or.greenlabs.spring.starter.document.Category;
import id.or.greenlabs.spring.starter.module.ApplicationTests;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

/**
 * @author krissadewo
 * @date 2/24/22 2:06 PM
 */
@SpringBootTest
@ContextConfiguration
@EnableAutoConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class BaseTest extends ApplicationTests {

    protected DummyData dummyData = new DummyData();

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    @Order(-99)
    void init() {
        reactiveMongoTemplate.dropCollection(Category.class).block();
    }
}
