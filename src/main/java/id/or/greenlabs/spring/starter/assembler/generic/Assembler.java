package id.or.greenlabs.spring.starter.assembler.generic;

import java.util.Collection;

/**
 * @author krissadewo
 * @date 1/21/22 1:03 PM
 */
public interface Assembler<D, E> {

    D toDto(E entity);

    Collection<D> toDto(Collection<E> documents);

    E toParam(D dto);

    E toDocument(D dto);

    Collection<E> toDocument(Collection<D> dto);
}
