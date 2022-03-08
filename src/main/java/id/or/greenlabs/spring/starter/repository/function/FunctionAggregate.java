package id.or.greenlabs.spring.starter.repository.function;

/**
 * @author krissadewo
 * @date 2/11/21 1:04 PM
 */
@FunctionalInterface
public interface FunctionAggregate<P1, P2, P3, R> {

    R apply(P1 aggregation, P2 inputType, P3 outputType);
}
