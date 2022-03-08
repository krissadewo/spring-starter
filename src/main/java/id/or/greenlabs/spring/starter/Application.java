package id.or.greenlabs.spring.starter;

import io.netty.channel.ChannelOption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.netty.http.client.HttpClient;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.time.Duration;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);

        try {
            if (args.length > 0) { //activating profiles
                springApplication.setAdditionalProfiles(args);
            }

            springApplication.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    @Order(0)
    public CodecCustomizer loggingCodecCustomizer() {
        return (configurer) -> configurer.defaultCodecs()
            .enableLoggingRequestDetails(true);
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

    public static Logger getLogger(Object o) {
        return Loggers.getLogger(o.getClass());
    }

    private static void logStackTrace(StackTraceElement[] stackTraceElements, Class<?> clazz) {
        StringBuilder stackTraces = new StringBuilder();

        for (StackTraceElement result : stackTraceElements) {
            stackTraces.append("\t").append(result).append("\n");
        }

        getLogger(clazz).error(stackTraces.toString());
    }


    public static void logThisException(Throwable exception) {
        logStackTrace(exception.getStackTrace(), exception.getClass());
    }

    public static boolean is5xxServerError(Throwable throwable) {
        return throwable instanceof WebClientResponseException &&
            ((WebClientResponseException) throwable).getStatusCode().is5xxServerError();
    }

}
