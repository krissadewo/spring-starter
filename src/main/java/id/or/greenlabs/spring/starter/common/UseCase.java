package id.or.greenlabs.spring.starter.common;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author krissadewo
 * @date 2/24/22 3:13 PM
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {

    String value() default "";
}
