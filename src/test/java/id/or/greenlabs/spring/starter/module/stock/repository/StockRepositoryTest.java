package id.or.greenlabs.spring.starter.module.stock.repository;

import id.or.greenlabs.spring.starter.document.Stock;
import id.or.greenlabs.spring.starter.module.category.config.BaseTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import reactor.test.StepVerifier;

import java.util.List;

/**
 * @author krissadewo
 * @date 3/4/22 8:53 AM
 */
class StockRepositoryTest extends BaseTest {

    @Autowired
    private StockRepository repository;

    private List<Stock> stocks;

    @Test
    @Order(1)
    void save() {
        StepVerifier
            .create(repository.save(
                dummyData.stocks(
                    dummyData.orders(
                        dummyData.product(new ObjectId().toHexString())
                    )
                )
            ))
            .thenConsumeWhile(result -> {
                stocks = result;

                return !stocks.isEmpty();
            })
            .verifyComplete();
    }

    @Test
    @Order(2)
    void findBy() {
        Stock param = new Stock();

        StepVerifier
            .create(repository.findBy(param, () -> PageRequest.of(0, 10)))
            .thenConsumeWhile(result -> {
                return result.getId() != null;
            })
            .verifyComplete();
    }
}
