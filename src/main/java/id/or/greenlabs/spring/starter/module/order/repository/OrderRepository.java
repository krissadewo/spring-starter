package id.or.greenlabs.spring.starter.module.order.repository;

import id.or.greenlabs.spring.starter.common.Mode;
import id.or.greenlabs.spring.starter.document.Order;
import id.or.greenlabs.spring.starter.repository.function.FunctionBuildCriteria;
import id.or.greenlabs.spring.starter.repository.generic.AbstractGenericRepository;
import id.or.greenlabs.spring.starter.repository.lookup.LookupOperationTemplate;
import org.bson.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author krissadewo
 * @date 3/4/22 8:56 AM
 */
@Repository
public class OrderRepository extends AbstractGenericRepository<Order> {

    public OrderRepository() {
        super(Order.class);
    }

    public Mono<List<Order>> save(List<Order> document) {
        return insert(document).collectList();
    }

    @Override
    public Flux<Order> findBy(Order param, Supplier<Pageable> pageable) {
        Aggregation aggregation = Aggregation.newAggregation(
            new MatchOperation(buildCriteria().apply(param, Mode.FIND)),
            LookupOperationTemplate.lookupProduct(),
            new AddFieldsOperation("product", new Document("$arrayElemAt", Arrays.asList("$" + "product", 0))),
            Aggregation.skip(pageable.get().getOffset()),
            Aggregation.limit(pageable.get().getPageSize())
        );

        return aggregate(aggregation, Order.class, Order.class);
    }

    @Override
    protected FunctionBuildCriteria<Order, Mode, Criteria> buildCriteria() {
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
