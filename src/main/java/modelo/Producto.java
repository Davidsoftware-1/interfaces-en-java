package modelo;

public record Producto(String nombre, String categoria, double precio, int stock) {
    public boolean disponible() {
        return stock > 0;
    }
}
