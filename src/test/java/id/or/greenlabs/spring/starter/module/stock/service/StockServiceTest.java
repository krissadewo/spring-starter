package id.or.greenlabs.spring.starter.module.stock.service;

import id.or.greenlabs.spring.starter.assembler.dto.StockDto;
import id.or.greenlabs.spring.starter.module.stock.config.BaseTest;
import id.or.greenlabs.spring.starter.module.stock.repository.StockRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import reactor.test.StepVerifier;

import java.util.List;

/**
 * @author krissadewo
 * @date 3/11/22 9:45 AM
 */
@ContextConfiguration(
    loader = AnnotationConfigContextLoader.class,
    classes = {
        StockRepository.class,
        StockService.class
    })
class StockServiceTest extends BaseTest {

    @Autowired
    private StockService service;

    private List<StockDto> stockDtos;

    @Test
    @Order(1)
    void save() {
        StepVerifier
            .create(service.save(dummyData.stockDtos(dummyData.orderDtos())))
            .thenConsumeWhile(result -> {
                stockDtos = result;

                return !stockDtos.isEmpty();
            })
            .verifyComplete();
    }

    @Test
    @Order(2)
    void findBy() {
        StepVerifier
            .create(service.findBy(new StockDto(), 10, 0))
            .thenConsumeWhile(result -> {
                return result.getId() != null;
            })
            .verifyComplete();
    }
}
