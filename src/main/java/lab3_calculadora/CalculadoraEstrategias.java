package lab3_calculadora;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;

public class CalculadoraEstrategias {

    public static final Map<String, DoubleBinaryOperator> OPERACIONES = new LinkedHashMap<>();

    static {
        OPERACIONES.put("+", Double::sum);
        OPERACIONES.put("-", (a, b) -> a - b);
        OPERACIONES.put("*", (a, b) -> a * b);
        OPERACIONES.put("/", (a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("división por cero");
            }
            return a / b;
        });
        OPERACIONES.put("^", Math::pow);
        OPERACIONES.put("max", Math::max);
    }

    public static double evaluar(String expresion) {
        String[] partes = expresion.strip().split("\\s+");
        if (partes.length != 3) {
            throw new IllegalArgumentException("formato esperado: a op b");
        }
        DoubleBinaryOperator operacion = OPERACIONES.get(partes[1]);
        if (operacion == null) {
            throw new IllegalArgumentException("operador desconocido: " + partes[1]);
        }
        return operacion.applyAsDouble(
            Double.parseDouble(partes[0]),
            Double.parseDouble(partes[2])
        );
    }

    public static void ejecutar() {
        for (String e : List.of("12 + 30", "2 ^ 10", "7 / 2", "9 max 4", "5 / 0", "3 % 2")) {
            try {
                System.out.printf("%-8s = %s%n", e, evaluar(e));
            } catch (RuntimeException ex) {
                System.out.printf("%-8s → error: %s%n", e, ex.getMessage());
            }
        }
        System.out.println("Operadores disponibles: " + OPERACIONES.keySet());
    }

    public static void main(String[] args) {
        ejecutar();
    }
}
