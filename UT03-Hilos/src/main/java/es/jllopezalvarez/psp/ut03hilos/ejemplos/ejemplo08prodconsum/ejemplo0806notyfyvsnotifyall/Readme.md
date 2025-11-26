# Ejemplo productor-consumidor con sincronización, sin wait / notify, pero con espera activa en los hilos.

En este ejemplo hay tres hilos:

- Productor: genera números que añade a una cola.
- Consumidor: obtiene números de la cola y los procesa.
- Principal: crea la cola, lanza productor y consumidor, espera un tiempo, y finaliza los hilos.

Hay sincronización en la cola (métodos synchronized), y se usa wait / notify:
- El consumidor espera cuando no hay números usando wait
- El productor notifica a los consumidores que hay nuevos datos con notify

Es más eficiente, usa menos procesador que una espera activa

