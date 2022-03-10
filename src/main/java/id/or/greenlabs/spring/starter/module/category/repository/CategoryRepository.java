package id.or.greenlabs.spring.starter.module.category.repository;

import com.mongodb.client.result.UpdateResult;
import id.or.greenlabs.spring.starter.common.DefaultException;
import id.or.greenlabs.spring.starter.common.Mode;
import id.or.greenlabs.spring.starter.common.StatusCode;
import id.or.greenlabs.spring.starter.document.Category;
import id.or.greenlabs.spring.starter.repository.function.FunctionBuildCriteria;
import id.or.greenlabs.spring.starter.repository.generic.AbstractGenericRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

/**
 * @author krissadewo
 * @date 2/23/22 3:48 PM
 */
@Repository
public class CategoryRepository extends AbstractGenericRepository<Category> {

    public CategoryRepository() {
        super(Category.class);
    }

    public Mono<UpdateResult> update(Category document) {
        Update update = new Update();
        update.set("name", document.getName());

        return update(buildQuery().apply(document, Mode.UPDATE), update);
    }

    public Mono<UpdateResult> delete(Category document) {
        Update update = new Update();
        update.set("delete", true);

        return update(buildQuery().apply(document, Mode.UPDATE), update);
    }

    @Override
    public Flux<Category> findBy(Category param, Supplier<Pageable> pageable) {
        return findBy(buildQuery().apply(param, Mode.FIND), pageable, Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    protected FunctionBuildCriteria<Category, Mode, Criteria> buildCriteria() {
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
                default:
                    throw new DefaultException(StatusCode.INPUT_NOT_VALID);
            }

            return criteria;
        };
    }
}
