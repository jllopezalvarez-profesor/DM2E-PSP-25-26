# Ejercicio de sincronización con synchronized

Simula un juego para adivinar un número, en el que participan varios jugadores.

Un árbitro, compartido por todos los jugadores, se encarga de:

- Calcular el número que hay que adivinar.
- Controlar el turno de jugadores.
- Procesar las jugadas de los jugadores, y determinar cuando se gana el juego.

## Versión con sincronización

En esta versión se ha añadido synchronized a todos los métodos de Arbitro que usan o modifican atributos relacionados
con el proceso, y que pueden tener valores incorrectos si se deja que los hilos accedan simultáneamente.
