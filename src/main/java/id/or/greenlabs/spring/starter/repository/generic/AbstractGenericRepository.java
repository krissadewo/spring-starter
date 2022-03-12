package id.or.greenlabs.spring.starter.repository.generic;

import id.or.greenlabs.spring.starter.common.Mode;
import id.or.greenlabs.spring.starter.document.DocumentAware;
import id.or.greenlabs.spring.starter.repository.function.FunctionBuildCriteria;
import id.or.greenlabs.spring.starter.repository.function.FunctionBuildQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author krissadewo
 * @date 1/28/21 7:08 PM
 */
public abstract class AbstractGenericRepository<T extends DocumentAware> extends ExecutableMongoRepository<T> {

    public AbstractGenericRepository(@Value("") Class<T> type) {
        this.type = type;
    }

    public Mono<T> save(T document) {
        return insert(document);
    }

    public abstract Flux<T> findBy(T param, Supplier<Pageable> pageable);

    public Mono<Long> count(T param) {
        return count(buildQuery().apply(param, Mode.FIND));
    }

    protected FunctionBuildQuery<T, Mode, Query> buildQuery() {
        return (param, mode) -> Query.query(buildCriteria().apply(param, mode));
    }

    protected abstract FunctionBuildCriteria<T, Mode, Criteria> buildCriteria();

}
