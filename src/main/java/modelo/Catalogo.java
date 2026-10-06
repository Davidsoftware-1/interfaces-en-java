package modelo;

import java.util.List;

public final class Catalogo {
    private Catalogo() { }

    public static List<Producto> muestra() {
        return List.of(
            new Producto("Portátil", "tecnología", 3_200_000, 4),
            new Producto("Mouse", "tecnología", 85_000, 25),
            new Producto("Cuaderno", "papelería", 12_000, 0),
            new Producto("Audífonos", "tecnología", 240_000, 8),
            new Producto("Lápiz", "papelería", 2_500, 120),
            new Producto("Silla ergonómica", "muebles", 890_000, 2)
        );
    }
}
