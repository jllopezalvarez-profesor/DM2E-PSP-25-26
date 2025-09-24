# Ejemplo de procesos que usan el mismo recurso - Con sincronización usando File, que no funciona como se espera.
Este ejemplo es igual que el ejemplo07sincronizacion, pero se ha añadido mejorado para hacer en una sola operación la 
comprobación del lock (si se puede acceder al recurso) y la creación del fichero que sirve para bloqueo.

Esto permite eliminar el problema de la versión 07, que permite el acceso concurrente. 
