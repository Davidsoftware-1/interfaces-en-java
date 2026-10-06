package lab2_validacion;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@FunctionalInterface
public interface Regla<T> {
    List<String> validar(T valor);

    default Regla<T> y(Regla<? super T> otra) {
        return valor -> {
            List<String> errores = new ArrayList<>(validar(valor));
            errores.addAll(otra.validar(valor));
            return errores;
        };
    }

    static <T> Regla<T> exigir(Predicate<? super T> condicion, String mensaje) {
        return valor -> condicion.test(valor) ? List.of() : List.of(mensaje);
    }

    static <T, C> Regla<T> campo(Function<? super T, ? extends C> extractor, Regla<? super C> regla) {
        return valor -> regla.validar(extractor.apply(valor));
    }
}
