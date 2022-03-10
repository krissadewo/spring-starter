package id.or.greenlabs.spring.starter.module;

import id.or.greenlabs.spring.starter.common.DummyData;
import id.or.greenlabs.spring.starter.module.category.repository.CategoryRepository;
import io.netty.channel.ChannelOption;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.time.Duration;

/**
 * @author krissadewo
 * @date 2/24/22 2:06 PM
 */
/*@ExtendWith(SpringExtension.class)
@SpringBootApplication(exclude = {JmxAutoConfiguration.class})
@ContextConfiguration(
    loader = AnnotationConfigContextLoader.class,
    classes = {
        CategoryRepository.class,
    })*/
//@SpringBootTest
public class ApplicationTests {

  /*  public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApplicationTests.class);

        try {
            if (args.length > 0) { //activating profiles
                springApplication.setAdditionalProfiles(args);
            }

            springApplication.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger(Object o) {
        return Loggers.getLogger(o.getClass());
    }

    @Bean
    @LoadBalanced
    WebClient.Builder builder() {
        return WebClient.builder();
    }

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
            .responseTimeout(Duration.ofSeconds(5));

        return builder.clientConnector(new ReactorClientHttpConnector(httpClient))
            .exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                    .defaultCodecs()
                    .maxInMemorySize(16 * 1024 * 1024))
                .build())
            .build();
    }

    @Bean
    public DummyData dummyData() {
        return new DummyData();
    }*/
}
