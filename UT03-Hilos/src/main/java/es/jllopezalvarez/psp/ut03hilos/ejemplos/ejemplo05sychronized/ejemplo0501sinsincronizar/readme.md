# Ejemplos de sincronización de hilos.

## Paquete  [ejemplo0501sinsincronizar](ejemplo0501sinsincronizar)

Este paquete es el ejemplo de partida en el que no se usa sincronización, y que no tiene un comportamiento correcto
precisamente por el acceso concurrente a recursos compartidos.

Se usa una clase "Contador", que guarda en un atributo privado la cuenta, y se incrementa con un método incrementar().
Una instancia del contador se comparte entre muchos hilos que lo usan para sumar 1 repetidas veces al contador.

Como las llamadas a incrementar no están sincronizadas, no hay exclusión mutua (que solo un hilo incremente a la vez).
Esto provoca que varios hilos, en repetidas ocasiones, puedan intentar hacer la operación a la vez.

Como la suma ++ no es atómica, sino que se divide en:

- Lectura de valor del contador
- Incremento del valor
- Actualización del valor con el incrementado

Resulta que al final se incrementa menos veces de las esperadas.

Ejemplo de salida del programa:

```text
Thread Hilo 3 termina sin lanzamiento de InterruptedException.
Thread Hilo 1 termina sin lanzamiento de InterruptedException.
Thread Hilo 2 termina sin lanzamiento de InterruptedException.
Thread Hilo 4 termina sin lanzamiento de InterruptedException.
Thread Hilo 5 termina sin lanzamiento de InterruptedException.
Se esperaba 2500000 y se ha obtenido 2476015
Diferencia: 23985
```

La suma final no coincide porque se han perdido sumas que se han "solapado" al permitir a más de un hilo entrar en una
región crítica.