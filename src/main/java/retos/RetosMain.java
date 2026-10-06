package retos;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class RetosMain {

    public static void ejecutar() {
        System.out.println("--- Reto 1: TriFunction currificada ---");
        TriFunction<String, Integer, Double, String> factura =
            (cliente, unidades, precio) -> cliente + " debe " + (unidades * precio);
        var curried = factura.curry();
        String resCurry = curried.apply("Carlos").apply(5).apply(1200.0);
        System.out.println(resCurry);

        System.out.println("\n--- Reto 2: Regla o(...) y cuando(...) ---");
        ReglaAvanzada<String> reglaLongitud = ReglaAvanzada.exigir(s -> s.length() >= 5, "minimo 5 caracteres");
        ReglaAvanzada<String> reglaEmpiezaMayus = ReglaAvanzada.exigir(s -> !s.isEmpty() && Character.isUpperCase(s.charAt(0)), "debe iniciar en mayuscula");
        ReglaAvanzada<String> reglaDisyuntiva = reglaLongitud.o(reglaEmpiezaMayus);
        System.out.println("abcde (cumple longitud): " + reglaDisyuntiva.validar("abcde"));
        System.out.println("Abc (cumple mayuscula): " + reglaDisyuntiva.validar("Abc"));
        System.out.println("ab (no cumple ninguna): " + reglaDisyuntiva.validar("ab"));

        ReglaAvanzada<String> soloSiTieneTexto = reglaLongitud.cuando(s -> s != null && !s.isBlank());
        System.out.println("validar vacio con 'cuando': " + soloSiTieneTexto.validar("   "));

        System.out.println("\n--- Reto 3: Memoizacion concurrente ---");
        AtomicInteger conteo = new AtomicInteger(0);
        Function<Integer, Integer> cuadradoLento = MemoizacionConcurrente.memorizar(n -> {
            conteo.incrementAndGet();
            return n * n;
        });
        System.out.println("cuadrado(4): " + cuadradoLento.apply(4));
        System.out.println("cuadrado(4) cache: " + cuadradoLento.apply(4));
        System.out.println("Total evaluaciones reales: " + conteo.get());

        System.out.println("\n--- Reto 4: Reintento ---");
        AtomicInteger intentosFallidos = new AtomicInteger(0);
        String resultado = Reintentar.de(() -> {
            if (intentosFallidos.incrementAndGet() < 3) {
                throw new IllegalStateException("Fallo intento " + intentosFallidos.get());
            }
            return "Operacion exitosa en intento " + intentosFallidos.get();
        }, 5);
        System.out.println(resultado);
    }

    public static void main(String[] args) {
        ejecutar();
    }
}
