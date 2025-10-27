# Ejemplos de sincronización de hilos.

## Paquete [ejemplo0503syncronizedmetodos](ejemplo0503syncronizedmetodos)

Este paquete es el mismo ejemplo que el 0501 (sin sincronizar) pero usa "synchronized" en el método "incrementar" de la
clase "Contador".

En este caso se usa "synchronized" en el método "incrementar" del objeto compartido.

La palabra reservada synchronized garantiza que solo un hilo a la vez ejecutará el método marcado. Esto evitará que la
operación no atómica (el ++) se realice a la vez por varios hilos.

Cuando un hilo está ejecutando el método synchronized "incrementar" de un objeto Contador compartido, si otro hilo
intenta entrar se queda bloqueado (estado BLOCKED) hasta que el primer hilo termina el método.

Importante tener en cuenta:

- La sincronización en métodos de instancia se hace entre todos los métodos sincronizados del objeto. En el ejemplo, hay
  dos métodos sincronizados (incrementar y decrementar). Si un hilo está dentro de CUALQUIERA de los dos métodos, otro
  hilo no podrá entrar en NINGUNO de los dos métodos.
- Cuando un hilo está en estado BLOCKED, si llega una llamada a interrupt(), no se lanza InterruptedException. Se tiene
  que controlar el fin del hilo con Thread.interrupted() o Thread.isInterrupted().
- Los métodos sincronizados de instancia no se sincronizan (excluyen) con los métodos sincronizados de clase (static).

El resultado de este programa será similar al del ejemplo 0501, pero ya sin pérdida de sumas:

```text
Thread Hilo 4 termina sin lanzamiento de InterruptedException.
Thread Hilo 2 termina sin lanzamiento de InterruptedException.
Thread Hilo 3 termina sin lanzamiento de InterruptedException.
Thread Hilo 5 termina sin lanzamiento de InterruptedException.
Thread Hilo 1 termina sin lanzamiento de InterruptedException.
Se esperaba 2500000 y se ha obtenido 2500000
Diferencia: 0
```

Ya no se solapan operaciones.