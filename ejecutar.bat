@echo off
chcp 65001 > nul
setlocal

echo Compilando archivos...
if not exist "bin" mkdir bin

javac -encoding UTF-8 -d bin src/main/java/modelo/*.java src/main/java/lab1_slugs/*.java src/main/java/lab2_validacion/*.java src/main/java/lab3_calculadora/*.java src/main/java/lab4_promociones/*.java src/main/java/retos/*.java src/main/java/Main.java

if %ERRORLEVEL% neq 0 (
    echo Error durante la compilacion.
    pause
    exit /b %ERRORLEVEL%
)

echo Ejecutando proyecto principal...
echo.
java -Dfile.encoding=UTF-8 -cp bin Main %*
echo.

endlocal
