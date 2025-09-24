# Ejemplo de procesos que usan el mismo recurso - Con sincronización usando FileChannel, que no funciona
Este ejemplo hace lo mismo que los anteriores ejemplo[06-08]sincronizacion, pero 
con FileChannel de Java NIO, que, bien usado, permite un bloqueo REAL de los procesos.

En este caso no funciona porque para escribir y para el bloqueo se usa el mismo objeto, y 
además se usa PrintWriter. Al usar PrintWriter el proceso de escritura no es "directo" al fichero,
por lo que no se pueden sincronizar bien las operaciones. 
