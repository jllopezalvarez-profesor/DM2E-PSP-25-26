# Ejercicio de sincronización con synchronized

Simula un juego para adivinar un número, en el que participan varios jugadores.

Un árbitro, compartido por todos los jugadores, se encarga de:

- Calcular el número que hay que adivinar.
- Controlar el turno de jugadores.
- Procesar las jugadas de los jugadores, y determinar cuando se gana el juego.

## Versión sin sincronización en la que es dificil que aparezcan las condiciones de carrera.

En esta versión no se hace ninguna clase de sincronización, y, en teoría, se deberían producir condiciones de carrera
como:

- Que un jugador compita varias veces seguidas
- Que varios jugadores compitan a la vez:

```
Inicio del programa.
El número que hay que adivinar es el 17.
El jugador 1 dice 20. No es correcto. Siguiente jugador: 2.
El jugador 2 dice 11. No es correcto. Siguiente jugador: 3.
El jugador 3 dice 6. No es correcto. Siguiente jugador: 4.
El jugador 4 dice 8. No es correcto. Siguiente jugador: 5.
El jugador 5 dice 10. No es correcto. Siguiente jugador: 6.
El jugador 6 dice 6. No es correcto. Siguiente jugador: 7.
El jugador 7 dice 10. No es correcto. Siguiente jugador: 8.
El jugador 8 dice 15. No es correcto. Siguiente jugador: 9.
El jugador 9 dice 14. No es correcto. Siguiente jugador: 10.
El jugador 10 dice 18. No es correcto. Siguiente jugador: 11.
El jugador 11 dice 16. No es correcto. Siguiente jugador: 12.
El jugador 12 dice 6. No es correcto. Siguiente jugador: 13.
El jugador 13 dice 10. No es correcto. Siguiente jugador: 14.
El jugador 14 dice 1. No es correcto. Siguiente jugador: 15.
El jugador 15 dice 19. No es correcto. Siguiente jugador: 16.
El jugador 16 dice 16. No es correcto. Siguiente jugador: 17.
El jugador 17 dice 17. El jugador 17 ha ganado el juego.
Fin del programa.
```

Sin embargo, no aparecen fallos de sincronización, condiciones de carrera.
Esto pasa en gran parte porque:

- El árbitro trabaja muy rápido. Cuando evalúa la jugada del usuario no hace procesos largos o complejos, ni hace
  llamadas a métodos que puedan "dormir" el hilo.
- Se usan mucho los métodos de System.out. Todos estos métodos están sincronizados internamente, así que, en la
  práctica, actuan como un sistema de micro-sincronización.
- Las operaciones que se realizan con tipos enteros, como es el turno, son atómicas en lectura, y es dificil que se
  produzcan condiciones de carrera en este caso.

Pero esto no significa que el código sea correcto. Sigue existiendo posibilidad de lectura de valor incorrecto, o de
sobrescritura de valores por condición de carrera.

En la versión 2 se modificará el código para hacer que sí aparezca alguna condición de carrera más facilmente. 