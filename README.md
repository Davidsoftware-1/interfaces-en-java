# Interfaces Funcionales en Java

Taller practico sobre programacion funcional en Java utilizando lambdas, referencias a metodos y las interfaces del paquete `java.util.function`.

El proyecto implementa el Laboratorio 4 (Proyecto Integrador: Motor de Promociones del Catalogo) como componente principal, junto con los laboratorios complementarios de la guia tecnica.

## Estructura del Proyecto

```
interfaces-en-java/
├── pom.xml
├── README.md
├── ejecutar.bat
└── src/
    └── main/
        └── java/
            ├── Main.java
            ├── modelo/
            │   ├── Producto.java
            │   └── Catalogo.java
            ├── lab1_slugs/
            │   └── GeneradorSlugs.java
            ├── lab2_validacion/
            │   ├── Regla.java
            │   ├── Registro.java
            │   └── ValidadorMain.java
            ├── lab3_calculadora/
            │   └── CalculadoraEstrategias.java
            ├── lab4_promociones/
            │   ├── Promocion.java
            │   └── MotorPromociones.java
            └── retos/
                ├── TriFunction.java
                ├── ReglaAvanzada.java
                ├── MemoizacionConcurrente.java
                ├── Reintentar.java
                └── RetosMain.java
```

## Descripcion de Componentes

### 1. Laboratorio 4 (Proyecto Integrador: Motor de Promociones)
Implementado en `lab4_promociones`:
- `Promocion`: Record que modela cada promocion mediante un nombre, un predicado de aplicacion (`Predicate<Producto>`) y un ajuste de precio (`DoubleUnaryOperator`).
- `porcentaje(double pct)` y `montoFijo(double monto)`: Fabricas de operadores unarios para aplicar descuentos porcentuales o valores fijos sin reducir el precio por debajo de cero.
- Promociones configuradas:
  - 10 % en articulos de tecnologia.
  - Bono de $50.000 en compras de $200.000 o mas.
  - Liquidacion del 30 % para productos con stock mayor a 100 unidades.
- Seleccion automatica de la mejor opcion para el cliente usando `BinaryOperator.minBy(...)` y reduccion en bucle.
- Impresion formateada mediante `BiConsumer<Producto, Promocion>` y calculo del ahorro acumulado con `ToDoubleBiFunction`.

### 2. Laboratorio 1 (Generador de Slugs por Composicion)
Transformacion funcional de texto mediante una tuberia compuesta con `andThen`:
- Recorte de espacios (`String::strip`).
- Conversion a minusculas (`String::toLowerCase`).
- Normalizacion y eliminacion de marcas diacriticas (tildes).
- Filtrado de caracteres alfanumericos.
- Sustitucion de secuencias de separadores por guiones simples.

### 3. Laboratorio 2 (Motor de Validacion Componible)
Diseno de una interfaz funcional generica `@FunctionalInterface interface Regla<T>`:
- Composicion logica mediante el metodo default `y(Regla<? super T> otra)`.
- Fabricas estaticas `exigir` (a partir de predicados) y `campo` (adaptador para extraer atributos de objetos).
- Validacion de registros de usuarios con acumulacion de errores.

### 4. Laboratorio 3 (Calculadora con Estrategias)
Reemplazo de bifurcaciones condicionales mediante un mapa asociativo de operadores (`DoubleBinaryOperator`):
- Soporte para operaciones basicas (`+`, `-`, `*`, `/`), potencia (`^`) y maximo (`max`).
- Validacion de division por cero arrojando `ArithmeticException`.

### 5. Retos para Profundizar
- Reto 1: Metodo `curry()` en `TriFunction` para transformacion a cadena de funciones unarias.
- Reto 2: Metodos `o(...)` y `cuando(...)` en reglas de validacion para soporte de disyuncion y aplicacion condicional.
- Reto 3: Memoizacion concurrente basada en `ConcurrentHashMap` con proteccion ante calculos redundantes.
- Reto 4: Utilidad generica `Reintentar.de(Supplier<T>, int)` para reintentar operaciones ante fallos.

## Compilacion y Ejecucion

### Opcion 1: Script en Windows
Ejecutar el archivo batch:
```cmd
ejecutar.bat
```

### Opcion 2: Manual desde linea de comandos
Compilar los archivos fuente:
```bash
javac -encoding UTF-8 -d bin src/main/java/**/*.java
```

Ejecutar el proyecto principal (Laboratorio 4 por defecto):
```bash
java -Dfile.encoding=UTF-8 -cp bin Main
```

Para ejecutar todos los laboratorios secuencialmente:
```bash
java -Dfile.encoding=UTF-8 -cp bin Main todos
```
