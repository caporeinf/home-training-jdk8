package home.training.jdk8.lambda.robocall;

/**
 * Created by renrodriguez on 5/10/2016.
 */
@FunctionalInterface
public interface CustomFunctionInterface<T, R>{

    R exchange(T t);
}
