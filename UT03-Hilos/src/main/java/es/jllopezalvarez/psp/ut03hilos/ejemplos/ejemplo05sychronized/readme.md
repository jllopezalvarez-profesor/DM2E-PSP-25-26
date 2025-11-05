# Ejemplos de sincronización de hilos.

En este paquete hay varios subpaquetes con ejemplos de sincronización de hilos. Cada paquete intenta ilustrar alguna
situación o algún problema relacionados con el acceso concurrente a recursos y la necesidad de sincronización,
y técnicas para minimizar (no siempre con éxito) esos problemas.

## Paquete [ejemplo0501sinsincronizar](ejemplo0501sinsincronizar)

Este paquete es el ejemplo de partida en el que no se usa sincronización, y que no tiene un comportamiento correcto
precisamente por el acceso concurrente a recursos compartidos.

## Paquete [ejemplo0502volatile](ejemplo0502volatile)

Este paquete es el mismo ejemplo que el 0501 (sin sincronizar) pero usa "volatile" en el atributo "contador" de la
clase "Contador".

## Paquete [ejemplo0503syncronizedmetodos](ejemplo0503syncronizedmetodos)

Este paquete es el mismo ejemplo que el 0501 (sin sincronizar) pero usa "synchronized" en el método "incrementar" de la
clase "Contador".

## Paquete [ejemplo0504syncronizedsentencias](ejemplo0504syncronizedsentencias)

Este paquete es básicamente igual que el ejemplo 0503, pero en lugar de sincronizarse todo el método, se sincroniza solo
la sentencia problemática, la que incrementa el contador. En este ejemplo simple no hay realmente diferencias, porque
los métodos solo tienen una sentencia. Pero en el caso de métodos más complejos, la idea es siempre sincronizar lo
mínimo posible. Cuanto menos código sincronizado haya en el programa, más paralelismo podrá alcanzar.

## Paquete [ejemplo0506atomic](ejemplo0506atomic)

Este paquete parte del ejemplo inicial (el 0501) y utiliza la clase "AtomicInteger" para eliminar el problema de acceso
concurrente. Esta clase encapsula un entero marcado como "volatile" y evita condiciones de carrera porque garantiza que
sus operaciones son atómicas, no se hacen en varios pasos. Estas clases "Atomic..." consiguen esto usando operaciones
atómicas a nivel de hardware. Son operaciones como 