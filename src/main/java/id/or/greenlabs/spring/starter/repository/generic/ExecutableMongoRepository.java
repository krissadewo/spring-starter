package id.or.greenlabs.spring.starter.repository.generic;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClient;
import id.or.greenlabs.spring.starter.document.DocumentAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author krissadewo
 * @date 1/28/21 7:08 PM
 */
public class ExecutableMongoRepository<T extends DocumentAware> {

    @Autowired
    protected ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    protected MongoClient mongoClient;

    protected Class<T> type;

    protected Mono<T> insert(T document) {
        return reactiveMongoTemplate.insert(document);
    }

    protected Flux<T> insert(Collection<T> document) {
        return reactiveMongoTemplate.insertAll(document);
    }

    protected Mono<DeleteResult> remove(T document) {
        return reactiveMongoTemplate.remove(document);
    }

    protected Mono<UpdateResult> update(Query query, Update update) {
        return reactiveMongoTemplate.updateFirst(query, update, type);
    }

    public Mono<T> findBy(String id) {
        return reactiveMongoTemplate.findById(id, type);
    }

    protected Flux<T> findBy(Query query, Supplier<Pageable> pageable, Sort sort) {
        if (sort != null) {
            query.with(sort);
        }

        if (pageable != null) {
            query.with(pageable.get());
        }

        return reactiveMongoTemplate.find(query, type);
    }

    protected Mono<T> findBy(Query query) {
        return reactiveMongoTemplate.findOne(query, type);
    }

    protected Mono<Long> count(Query query) {
        return reactiveMongoTemplate.count(query, type);
    }

    protected Flux<T> aggregate(Aggregation aggregation, Class<T> inputType, Class<T> outputType) {
        return reactiveMongoTemplate.aggregate(aggregation, inputType, outputType);
    }
}
