# Ejemplos de sincronización de hilos.

## Paquete [ejemplo0502volatile](ejemplo0502volatile)

Este paquete es el mismo ejemplo que el 0501 (sin sincronizar) pero usa "volatile" en el atributo "contador" de la
clase "Contador".

En Java, para mejorar la eficiencia, cada hilo tiene su propia copia de las variables en memoria caché. Esto significa
que:

- Cuando un hilo lee una variable, puede estar leyendo una versión antigua que tiene almacenada en su caché local.
- Cuando un hilo escribe, el valor puede quedarse temporalmente ahí y no verse reflejado inmediatamente en la memoria
  principal (que comparten todos los hilos).

Conclusión: otro hilo podría no ver el cambio, aunque la variable haya sido modificada.

Cuando se declara una variable como volatile, se está diciendo al compilador y a la JVM:

- "Esta variable puede ser modificada por varios hilos"
- "No guardes copias locales y asegúrate de leer y escribir siempre desde la memoria principal”

Esto tiene dos efectos garantizados por el Java Memory Model (JMM):

- Visibilidad: Todos los hilos ven el valor más reciente de la variable. No hay lecturas obsoletas.
- Orden de operaciones (happens-before): Las operaciones que ocurren antes de escribir en una variable volatile son
  visibles para los hilos que leen esa variable después.

Aunque volatile garantiza visibilidad, no hace que las operaciones sean atómicas.
Por ejemplo, contador++ implica tres pasos internos:

- Leer el valor.
- Incrementarlo.
- Escribir el nuevo valor.

Si dos hilos hacen esto a la vez, pueden leer el mismo valor inicial y perder incrementos.
Por eso, volatile no sirve para proteger operaciones compuestas, solo para asegurar que los hilos vean valores
actualizados. De hecho, IntelliJ avisa de que se está realizando una operación no atómica en una variable volatile con
un warning.

El resultado de este programa será similar al del ejemplo 0501, con pérdida de sumas:
```text
Thread Hilo 2 termina sin lanzamiento de InterruptedException.
Thread Hilo 1 termina sin lanzamiento de InterruptedException.
Thread Hilo 3 termina sin lanzamiento de InterruptedException.
Thread Hilo 4 termina sin lanzamiento de InterruptedException.
Thread Hilo 5 termina sin lanzamiento de InterruptedException.
Se esperaba 2500000 y se ha obtenido 2479008
Diferencia: 20992
```

Se siguen solapando operaciones.