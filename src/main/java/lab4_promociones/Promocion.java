package lab4_promociones;

import modelo.Producto;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Predicate;

public record Promocion(String nombre, Predicate<Producto> aplicaA, DoubleUnaryOperator ajuste) {
    public double precioCon(Producto p) {
        return aplicaA.test(p) ? ajuste.applyAsDouble(p.precio()) : p.precio();
    }
}
