# Ejercicio de sincronización con synchronized

Simula un juego para adivinar un número, en el que participan varios jugadores.

Un árbitro, compartido por todos los jugadores, se encarga de:

- Calcular el número que hay que adivinar.
- Controlar el turno de jugadores.
- Procesar las jugadas de los jugadores, y determinar cuando se gana el juego.

## Versión sin sincronización, en la que aparecen las condiciones de carrera

En esta versión se ha hecho un cambio en el método `comprobarJugada` de la clase `Arbitro`.

En concreto, se ha cambiado la línea

```Java
turnoSiguienteJugador =(turnoSiguienteJugador +1)%numJugadores;
```

por el siguiente código:

```Java
int turnoSiguiente = (turnoSiguienteJugador + 1) % numJugadores;
Thread.

sleep(TIEMPO_PENSAR);

turnoSiguienteJugador =turnoSiguiente;
```

Con esto se hace que la operación de incrementar turno se hace más problemática, porque no se hace de forma atómica,
permitiendo a más hilos "colarse" entre las dos partes de la operación.

```
Inicio del programa.
El número que hay que adivinar es el 14.
El jugador 1 dice 12. No es correcto. Siguiente jugador: 2.
El jugador 2 dice 9. No es correcto. Siguiente jugador: 3.
El jugador 3 dice 2. No es correcto. Siguiente jugador: 4.
El jugador 4 dice 18. No es correcto. Siguiente jugador: 5.
El jugador 5 dice 18. No es correcto. Siguiente jugador: 6.
El jugador 6 dice 17. No es correcto. Siguiente jugador: 7.
El jugador 7 dice 24. No es correcto. El jugador 8 dice 19. No es correcto. Siguiente jugador: 8.
Siguiente jugador: 9.
El jugador 9 dice 20. No es correcto. Siguiente jugador: 10.
El jugador 10 dice 23. No es correcto. Siguiente jugador: 11.
El jugador 11 dice 23. No es correcto. Siguiente jugador: 12.
El jugador 12 dice 21. No es correcto. Siguiente jugador: 13.
El jugador 13 dice 16. No es correcto. Siguiente jugador: 14.
El jugador 14 dice 9. No es correcto. Siguiente jugador: 15.
El jugador 15 dice 10. No es correcto. Siguiente jugador: 16.
El jugador 16 dice 17. No es correcto. Siguiente jugador: 17.
El jugador 17 dice 10. No es correcto. Siguiente jugador: 18.
El jugador 18 dice 25. No es correcto. Siguiente jugador: 19.
El jugador 19 dice 17. No es correcto. Siguiente jugador: 20.
El jugador 20 dice 24. No es correcto. Siguiente jugador: 21.
El jugador 21 dice 8. No es correcto. Siguiente jugador: 22.
El jugador 22 dice 23. No es correcto. Siguiente jugador: 23.
El jugador 23 dice 16. No es correcto. Siguiente jugador: 24.
El jugador 24 dice 23. No es correcto. El jugador 25 dice 17. No es correcto. Siguiente jugador: 25.
Siguiente jugador: 26.
El jugador 26 dice 12. No es correcto. Siguiente jugador: 27.
El jugador 27 dice 4. No es correcto. Siguiente jugador: 28.
El jugador 28 dice 6. No es correcto. Siguiente jugador: 29.
El jugador 29 dice 9. No es correcto. Siguiente jugador: 30.
El jugador 30 dice 19. No es correcto. Siguiente jugador: 31.
El jugador 31 dice 5. No es correcto. Siguiente jugador: 32.
El jugador 32 dice 24. No es correcto. Siguiente jugador: 33.
El jugador 33 dice 17. No es correcto. Siguiente jugador: 34.
El jugador 34 dice 1. No es correcto. Siguiente jugador: 35.
El jugador 35 dice 23. No es correcto. Siguiente jugador: 36.
El jugador 36 dice 20. No es correcto. Siguiente jugador: 37.
El jugador 37 dice 3. No es correcto. Siguiente jugador: 38.
El jugador 38 dice 17. No es correcto. Siguiente jugador: 39.
El jugador 39 dice 3. No es correcto. Siguiente jugador: 40.
El jugador 40 dice 20. No es correcto. Siguiente jugador: 41.
El jugador 41 dice 19. No es correcto. Siguiente jugador: 42.
El jugador 42 dice 15. No es correcto. Siguiente jugador: 43.
El jugador 43 dice 7. No es correcto. Siguiente jugador: 44.
El jugador 44 dice 10. No es correcto. Siguiente jugador: 45.
El jugador 45 dice 12. No es correcto. Siguiente jugador: 46.
El jugador 46 dice 10. No es correcto. Siguiente jugador: 47.
El jugador 47 dice 6. No es correcto. Siguiente jugador: 48.
El jugador 48 dice 20. No es correcto. Siguiente jugador: 49.
El jugador 49 dice 6. No es correcto. Siguiente jugador: 50.
El jugador 50 dice 18. No es correcto. Siguiente jugador: 51.
El jugador 51 dice 15. No es correcto. Siguiente jugador: 52.
El jugador 52 dice 8. No es correcto. Siguiente jugador: 53.
El jugador 53 dice 19. No es correcto. Siguiente jugador: 54.
El jugador 54 dice 1. No es correcto. Siguiente jugador: 55.
El jugador 55 dice 13. No es correcto. Siguiente jugador: 56.
El jugador 56 dice 5. No es correcto. Siguiente jugador: 57.
El jugador 57 dice 21. No es correcto. Siguiente jugador: 58.
El jugador 58 dice 14. El jugador 58 ha ganado el juego.
Fin del programa.
```

Se aprecia que los jugadores 8 y 25 se adelantan a la hora de mostrar los mensajes del árbitro, mostrando la jugada
antes de que termine de indicar que es su turno.  
