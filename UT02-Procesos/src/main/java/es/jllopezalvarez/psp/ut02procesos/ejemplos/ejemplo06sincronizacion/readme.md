# Ejemplo de procesos que usan el mismo recurso - Sin sincronización
En este ejemplo el proceso padre (MainProcess) lanza varios procesos hijos (ChildProcess), 
y cada uno de estos:
- Abre un fichero de texto en una ubicación concreta, para añadir texto en el fichero.
- Escribe una serie de líneas, sin cerrar el fichero entre líneas.
- Cierra el fichero.

El fichero está compartido entre todos los procesos, y se mantiene abierto desde la primera 
a la última escritura. No se cierra entre una línea y la siguiente.

Se puede observar que las líneas "se mezclan". Es decir, los procesos van escribiendo en 
el fichero al ritmo que quieren / pueden, y el SO deja escribir en este fichero.
