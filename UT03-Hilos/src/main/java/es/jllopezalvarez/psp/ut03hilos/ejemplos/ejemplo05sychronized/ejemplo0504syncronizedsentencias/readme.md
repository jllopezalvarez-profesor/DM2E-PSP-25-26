# Ejemplos de sincronización de hilos.

## Paquete [ejemplo0504syncronizedsentencias](ejemplo0504syncronizedsentencias)

Este paquete es básicamente igual que el ejemplo 0503, pero en lugar de sincronizarse todo el método, se sincroniza solo
la sentencia problemática, la que incrementa el contador. En este ejemplo simple no hay realmente diferencias, porque
los métodos solo tienen una sentencia. Pero en el caso de métodos más complejos, la idea es siempre sincronizar lo
mínimo posible. Cuanto menos código sincronizado haya en el programa, más paralelismo podrá alcanzar.

La sincronización se puede hacer sobre cualquier objeto. Cuando se usa synchonized en un método, por ejemplo:

```Java
public synchronized void incrementar() {
    contador++;
}
```

Es equivalente a usar `this` como objeto de bloqueo al principio del método:

```Java
public void incrementar() {
    synchronized (this) {
        contador++;
    }
}
```

En el ejemplo 0504 se usa un objeto específico para hacer el bloqueo.
Si se usan distintos objetos en distintas sentencias synchronized, se puede hacer que ciertos métodos se bloqueen por
grupos, no todos los métodos de instancia de la clase.
Esta forma de sincronizar puede ser más eficiente (depende de lo que haga el método sincronizado), pero hay más riesgo
de equivocarse al usar un objeto equivocado.
Una forma de evitar errores y mantener el rendimiento es hacer una extracción de método (refactorización) para que todo
lo que se desea sincronizar esté aislado en métodos, que pueden ser privados.

El resultado de este programa será igual al del ejemplo 0503, sin pérdida de sumas:

```text
Thread Hilo 3 termina sin lanzamiento de InterruptedException.
Thread Hilo 1 termina sin lanzamiento de InterruptedException.
Thread Hilo 5 termina sin lanzamiento de InterruptedException.
Thread Hilo 4 termina sin lanzamiento de InterruptedException.
Thread Hilo 2 termina sin lanzamiento de InterruptedException.
Se esperaba 2500000 y se ha obtenido 2500000
Diferencia: 0
```

Ya no se solapan operaciones.