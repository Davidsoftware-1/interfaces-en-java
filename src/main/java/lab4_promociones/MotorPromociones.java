package lab4_promociones;

import modelo.Catalogo;
import modelo.Producto;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;

public class MotorPromociones {

    public static DoubleUnaryOperator porcentaje(double pct) {
        return precio -> precio * (1 - pct / 100);
    }

    public static DoubleUnaryOperator montoFijo(double monto) {
        return precio -> Math.max(0, precio - monto);
    }

    public static void ejecutar() {
        Locale.setDefault(Locale.forLanguageTag("es-CO"));

        List<Promocion> promociones = List.of(
            new Promocion("Tecno 10 %", p -> p.categoria().equalsIgnoreCase("tecnología"), porcentaje(10)),
            new Promocion("Bono $50.000", p -> p.precio() >= 200_000, montoFijo(50_000)),
            new Promocion("Liquidación", p -> p.stock() > 100, porcentaje(30))
        );

        Supplier<Promocion> sinPromocion =
            () -> new Promocion("Sin promoción", p -> true, DoubleUnaryOperator.identity());

        BiConsumer<Producto, Promocion> imprimir = (p, promo) -> System.out.printf(
            "%-17s %-14s $%,11.0f → $%,11.0f%n",
            p.nombre(),
            promo.nombre(),
            p.precio(),
            promo.precioCon(p)
        );

        ToDoubleBiFunction<Producto, Promocion> ahorro = (p, promo) -> p.precio() - promo.precioCon(p);

        double ahorroTotal = 0;
        for (Producto p : Catalogo.muestra()) {
            if (!p.disponible()) {
                continue;
            }

            BinaryOperator<Promocion> masConveniente =
                BinaryOperator.minBy(Comparator.comparingDouble(promo -> promo.precioCon(p)));

            Promocion mejor = sinPromocion.get();
            for (Promocion promo : promociones) {
                mejor = masConveniente.apply(mejor, promo);
            }

            imprimir.accept(p, mejor);
            ahorroTotal += ahorro.applyAsDouble(p, mejor);
        }

        System.out.printf("Ahorro total para el cliente: $%,.0f%n", ahorroTotal);
    }

    public static void main(String[] args) {
        ejecutar();
    }
}
