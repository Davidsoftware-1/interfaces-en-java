package lab1_slugs;

import java.text.Normalizer;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class GeneradorSlugs {

    public static final UnaryOperator<String> RECORTAR = String::strip;
    public static final UnaryOperator<String> MINUSCULAS = String::toLowerCase;
    public static final UnaryOperator<String> SIN_TILDES =
        s -> Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    public static final UnaryOperator<String> SOLO_VALIDOS = s -> s.replaceAll("[^a-z0-9\\s-]", "");
    public static final UnaryOperator<String> GUIONES = s -> s.strip().replaceAll("[\\s-]+", "-");

    public static Function<String, String> tuberia(List<? extends Function<String, String>> pasos) {
        Function<String, String> resultado = Function.identity();
        for (Function<String, String> paso : pasos) {
            resultado = resultado.andThen(paso);
        }
        return resultado;
    }

    public static void ejecutar() {
        Function<String, String> slug =
            tuberia(List.of(RECORTAR, MINUSCULAS, SIN_TILDES, SOLO_VALIDOS, GUIONES));

        for (String nombre : List.of(
            " Silla Ergonómica ",
            "Audífonos Bluetooth 5.3",
            "¡Oferta! Portátil i7 -- 16GB"
        )) {
            System.out.printf("%-32s → %s%n", "\"" + nombre + "\"", slug.apply(nombre));
        }
    }

    public static void main(String[] args) {
        ejecutar();
    }
}
