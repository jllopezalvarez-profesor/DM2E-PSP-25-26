# Ejemplo productor-consumidor con sincronización, sin wait / notify, pero con espera activa en los hilos.

En este ejemplo hay tres hilos:

- Productor: genera números que añade a una cola.
- Consumidor: obtiene números de la cola y los procesa.
- Principal: crea la cola, lanza productor y consumidor, espera un tiempo, y finaliza los hilos.

Hay sincronización en la cola (métodos synchronized), y el consumidor espera cuando no hay números, pero lo hace con
espera activa (bucle con sleep).

No falla, pero no es una forma eficiente, porque se ocupa tiempo haciendo la espera activa y comprobando cada cierto
tiempo si hay datos que consumir. Sería mejor que el consumidor se parara hasta que hubiera realmente datos.

