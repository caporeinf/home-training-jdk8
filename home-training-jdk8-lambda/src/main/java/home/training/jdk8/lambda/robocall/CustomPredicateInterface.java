package home.training.jdk8.lambda.robocall;

/**
 * Created by renrodriguez on 5/10/2016.
 */
@FunctionalInterface
public interface CustomPredicateInterface<T> {

    boolean test(T t);
}
