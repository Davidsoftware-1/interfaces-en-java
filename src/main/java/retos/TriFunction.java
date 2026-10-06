package retos;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<A, B, C, R> {
    R aplicar(A a, B b, C c);

    default <V> TriFunction<A, B, C, V> luego(Function<? super R, ? extends V> despues) {
        Objects.requireNonNull(despues);
        return (a, b, c) -> despues.apply(aplicar(a, b, c));
    }

    default Function<A, Function<B, Function<C, R>>> curry() {
        return a -> b -> c -> aplicar(a, b, c);
    }
}
