# Ejemplo de procesos que usan el mismo recurso - Con sincronización usando FileChannel
Este ejemplo hace lo mismo que los anteriores ejemplo[06-08]sincronizacion, pero 
con FileChannel de Java NIO, que permite un bloqueo REAL de los procesos.

En este caso se usa un fichero independiente para el bloqueo. 

Esta es la forma recomendada.