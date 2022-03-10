package id.or.greenlabs.spring.starter.module.product.repository;

import com.mongodb.client.result.UpdateResult;
import id.or.greenlabs.spring.starter.common.Mode;
import id.or.greenlabs.spring.starter.document.Product;
import id.or.greenlabs.spring.starter.repository.function.FunctionBuildCriteria;
import id.or.greenlabs.spring.starter.repository.generic.AbstractGenericRepository;
import id.or.greenlabs.spring.starter.repository.lookup.LookupOperationTemplate;
import org.bson.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * @author krissadewo
 * @date 3/1/22 9:42 AM
 */
@Repository
public class ProductRepository extends AbstractGenericRepository<Product> {

    public ProductRepository() {
        super(Product.class);
    }

    public Mono<UpdateResult> update(Product document) {
        Update update = new Update();
        update.set("code", document.getCode());
        update.set("name", document.getName());

        return update(buildQuery().apply(document, Mode.UPDATE), update);
    }

    public Mono<UpdateResult> delete(Product document) {
        Update update = new Update();
        update.set("delete", true);

        return update(buildQuery().apply(document, Mode.UPDATE), update);
    }

    public Mono<Product> findBy(Product param) {
        Aggregation aggregation = Aggregation.newAggregation(
            new MatchOperation(buildCriteria().apply(param, Mode.FIND)),
            LookupOperationTemplate.lookupCategory(),
            new AddFieldsOperation("category", new Document("$arrayElemAt", Arrays.asList("$" + "category", 0)))
        );

        return Mono.from(aggregate(aggregation, Product.class, Product.class));
    }

    @Override
    public Flux<Product> findBy(Product param, Supplier<Pageable> pageable) {
        Aggregation aggregation = Aggregation.newAggregation(
            new MatchOperation(buildCriteria().apply(param, Mode.FIND)),
            LookupOperationTemplate.lookupCategory(),
            new AddFieldsOperation("category", new Document("$arrayElemAt", Arrays.asList("$" + "category", 0))),
            new SortOperation(Sort.by(Sort.Direction.DESC, "name")),
            Aggregation.skip(pageable.get().getOffset()),
            Aggregation.limit(pageable.get().getPageSize())
        );

        return aggregate(aggregation, Product.class, Product.class);
    }

    @Override
    protected FunctionBuildCriteria<Product, Mode, Criteria> buildCriteria() {
        return (param, mode) -> {
            Criteria criteria = new Criteria();

            switch (mode) {
                case FIND:
                    if (param.getId() != null) {
                        criteria.and("id").is(param.getId());
                    }

                    if (param.getName() != null) {
                        criteria.and("name").is(param.getName());
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
