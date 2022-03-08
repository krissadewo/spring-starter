package id.or.greenlabs.spring.starter.module.stock.repository;

import id.or.greenlabs.spring.starter.common.Mode;
import id.or.greenlabs.spring.starter.document.Stock;
import id.or.greenlabs.spring.starter.repository.function.FunctionBuildCriteria;
import id.or.greenlabs.spring.starter.repository.generic.AbstractGenericRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author krissadewo
 * @date 3/3/22 10:14 AM
 */
@Repository
public class StockRepository extends AbstractGenericRepository<Stock> {

    public StockRepository() {
        super(Stock.class);
    }

    public Mono<List<Stock>> save(Collection<Stock> document) {
        return insert(document).collectList();
    }

    @Override
    public Flux<Stock> findBy(Stock param, Supplier<Pageable> pageable) {
        return findBy(buildQuery().apply(param, Mode.FIND), pageable, Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    protected FunctionBuildCriteria<Stock, Mode, Criteria> buildCriteria() {
        return (param, mode) -> {
            Criteria criteria = new Criteria();

            switch (mode) {
                case FIND:
                    if (param.getId() != null) {
                        criteria.and("id").is(param.getId());
                    }

                    break;
                case UPDATE:
                    criteria.and("id").is(param.getId());

                    break;
            }

            return criteria;
        };
    }
}
