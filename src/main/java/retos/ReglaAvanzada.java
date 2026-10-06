package retos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@FunctionalInterface
public interface ReglaAvanzada<T> {
    List<String> validar(T valor);

    default ReglaAvanzada<T> y(ReglaAvanzada<? super T> otra) {
        return valor -> {
            List<String> errores = new ArrayList<>(validar(valor));
            errores.addAll(otra.validar(valor));
            return errores;
        };
    }

    default ReglaAvanzada<T> o(ReglaAvanzada<? super T> otra) {
        return valor -> {
            List<String> misErrores = validar(valor);
            if (misErrores.isEmpty()) {
                return List.of();
            }
            List<String> otrosErrores = otra.validar(valor);
            if (otrosErrores.isEmpty()) {
                return List.of();
            }
            List<String> todos = new ArrayList<>(misErrores);
            todos.addAll(otrosErrores);
            return todos;
        };
    }

    default ReglaAvanzada<T> cuando(Predicate<? super T> condicion) {
        return valor -> condicion.test(valor) ? validar(valor) : List.of();
    }

    static <T> ReglaAvanzada<T> exigir(Predicate<? super T> condicion, String mensaje) {
        return valor -> condicion.test(valor) ? List.of() : List.of(mensaje);
    }

    static <T, C> ReglaAvanzada<T> campo(Function<? super T, ? extends C> extractor, ReglaAvanzada<? super C> regla) {
        return valor -> regla.validar(extractor.apply(valor));
    }
}
