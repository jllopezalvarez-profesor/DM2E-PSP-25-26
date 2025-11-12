# Ejemplo productor-consumidor con sincronización, sin wait / notify.

En este ejemplo hay tres hilos:

- Productor: genera números que añade a una cola.
- Consumidor: obtiene números de la cola y los procesa.
- Principal: crea la cola, lanza productor y consumidor, espera un tiempo, y finaliza los hilos.

Hay sincronización en la cola (métodos synchronized), pero el consumidor no espera cuando no hay números.

Fallará o no en las mismas condiciones que la versión sin sincronización:

- Si el consumidor se inicia antes que el productor es fallará con casi toda probabilidad nada más arrancar, porque no
  hay números en la cola.
- Aunque el productor empiece antes, si produce números más lentamente que el consumidor los procesa, también fallará.
- Aunque el productor empiece antes, si trabaja al mismo ritmo (o similar) que el consumidor, a veces fallará y a veces
  no.
- Si el productor empieza antes y produce más rápido de lo que el consumidor consume, no fallará.


