package lab2_validacion;

import java.util.List;

public class ValidadorMain {

    public static void ejecutar() {
        Regla<String> usuarioTexto = Regla.<String>exigir(s -> !s.isBlank(), "usuario: obligatorio")
            .y(Regla.exigir(s -> s.length() >= 4, "usuario: mínimo 4 caracteres"));

        Regla<Registro> usuarioValido = Regla.campo(Registro::usuario, usuarioTexto);
        Regla<Registro> correoValido = Regla.exigir(
            r -> r.correo().matches("[^@\\s]+@[^@\\s]+\\.[a-z]{2,}"),
            "correo: formato inválido"
        );
        Regla<Registro> edadValida = Regla.exigir(r -> r.edad() >= 14, "edad: mínimo 14 años");
        Regla<Registro> todas = usuarioValido.y(correoValido).y(edadValida);

        List<Registro> registros = List.of(
            new Registro("camila_r", "camila@correo.co", 19),
            new Registro("", "sin-arroba", 12),
            new Registro("ana", "ana@correo", 15)
        );

        for (Registro r : registros) {
            List<String> errores = todas.validar(r);
            String usuario = r.usuario().isEmpty() ? "(vacío)" : r.usuario();
            System.out.printf("%-9s %-10s %s%n", errores.isEmpty() ? "VÁLIDO" : "INVÁLIDO", usuario, errores);
        }
    }

    public static void main(String[] args) {
        ejecutar();
    }
}
