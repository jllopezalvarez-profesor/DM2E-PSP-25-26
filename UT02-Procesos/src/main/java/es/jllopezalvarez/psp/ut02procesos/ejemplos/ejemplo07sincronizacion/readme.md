# Ejemplo de procesos que usan el mismo recurso - Con sincronización usando File, que no funciona como se espera.
Este ejemplo es igual que el ejemplo06sincronizacion, pero se ha añadido en los procesos hijos un mecanismo de 
sincronización basado en File.

Este mecanismo no funciona correctamente, porque se puede producir una condición de carrera si dos o más procesos
que compiten por el recurso llegan a la vez a la llamada a exists().

Puede ser necesario ejecutar este ejemplo muchas veces o subir el número de concurrentes para conseguir producir 
la condición de carrera, porque aunque no del todo correcta, se añade cierta sincronización. 