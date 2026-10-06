package retos;

import java.util.function.Supplier;

public class Reintentar {

    public static <T> T de(Supplier<T> proveedor, int intentos) {
        if (intentos <= 0) {
            throw new IllegalArgumentException("El número de intentos debe ser mayor a 0");
        }
        RuntimeException ultimoError = null;
        for (int i = 1; i <= intentos; i++) {
            try {
                return proveedor.get();
            } catch (RuntimeException e) {
                ultimoError = e;
            } catch (Exception e) {
                ultimoError = new RuntimeException(e);
            }
        }
        throw ultimoError;
    }
}
