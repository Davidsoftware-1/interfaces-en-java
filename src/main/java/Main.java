import lab1_slugs.GeneradorSlugs;
import lab2_validacion.ValidadorMain;
import lab3_calculadora.CalculadoraEstrategias;
import lab4_promociones.MotorPromociones;
import retos.RetosMain;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("lab4") || args[0].equalsIgnoreCase("promociones")) {
            MotorPromociones.ejecutar();
            return;
        }

        switch (args[0].toLowerCase()) {
            case "lab1", "slugs" -> GeneradorSlugs.ejecutar();
            case "lab2", "validacion" -> ValidadorMain.ejecutar();
            case "lab3", "calculadora" -> CalculadoraEstrategias.ejecutar();
            case "retos" -> RetosMain.ejecutar();
            case "todos", "all" -> {
                System.out.println("========================================");
                System.out.println("LABORATORIO 1: GENERADOR DE SLUGS");
                System.out.println("========================================");
                GeneradorSlugs.ejecutar();

                System.out.println("\n========================================");
                System.out.println("LABORATORIO 2: MOTOR DE VALIDACION");
                System.out.println("========================================");
                ValidadorMain.ejecutar();

                System.out.println("\n========================================");
                System.out.println("LABORATORIO 3: CALCULADORA CON ESTRATEGIAS");
                System.out.println("========================================");
                CalculadoraEstrategias.ejecutar();

                System.out.println("\n========================================");
                System.out.println("LABORATORIO 4: MOTOR DE PROMOCIONES");
                System.out.println("========================================");
                MotorPromociones.ejecutar();

                System.out.println("\n========================================");
                System.out.println("RETOS PARA PROFUNDIZAR");
                System.out.println("========================================");
                RetosMain.ejecutar();
            }
            default -> {
                System.out.println("Opcion no reconocida. Ejecutando Laboratorio 4 (Motor de promociones):");
                MotorPromociones.ejecutar();
            }
        }
    }
}
