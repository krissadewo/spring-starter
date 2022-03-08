package id.or.greenlabs.spring.starter.repository.function;

/**
 * @author krissadewo
 * @date 2/11/21 1:29 PM
 */
@FunctionalInterface
public interface FunctionQuery<P1, P2, P3, R> {

    R apply(P1 param, P2 pageable, P3 userAuth);

}
