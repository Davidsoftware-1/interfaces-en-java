package retos;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class MemoizacionConcurrente {

    public static <T, R> Function<T, R> memorizar(Function<T, R> funcion) {
        ConcurrentMap<T, R> cache = new ConcurrentHashMap<>();
        return entrada -> cache.computeIfAbsent(entrada, funcion);
    }
}
