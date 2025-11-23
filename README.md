Busqueda inversa

El programa BusquedaInversa lee una matriz desde un archivo, calcula su inversa mediante el método de Gauss-Jordan y guarda el resultado en un nuevo archivo. Si la matriz no es invertible, escribe un mensaje indicando que no tiene inversa.

Metodo
    Se obtiene la matriz aumentada [A | I]
    El objetivo es transformar A en la matriz identidad:
        -Se normaliza el pivote
        -Se hacenceros arriba y abajo del pivote
    Cuando el lado izquierdo sea I, el lado derecho sera A^-1
    Si algun pivote es cero y no se puede intercambiar fila, la matriz no tiene inversa (determinante = 0)

Explicacion del programa
    Paso 1: Se ejecuta el // Método principal del proceso // en el cual se pedirá al usuario que ingrese el nombre del 
    archivo de entrada que contiene la matriz. Al dar enter, también solicitará el nombre del archivo de salida donde se 
    almacenarán los resultados.
    Paso 2: El archivo de entrada se dirige al método // Leer matriz desde archivo //. En este paso, el programa abre el 
    archivo ubicado en C:\archivos\, lee cada línea y convierte los valores en números decimales. Cada fila se guarda en 
    una lista y finalmente se transforma en una matriz bidimensional (double[][]). Si ocurre algún error al leer el 
    archivo, se muestra un mensaje de error.
    Paso 3:La matriz obtenida se envía al método // Calcular inversa con Gauss-Jordan //. Aquí el programa construye una 
    matriz aumentada [A | I], donde A es la matriz original e I es la matriz identidad.
        - Se selecciona un pivote en cada fila.
        - Se normaliza la fila pivote dividiendo todos sus elementos por el valor del pivote.
        - Se realizan operaciones de eliminación para convertir en cero los elementos de la misma columna en las demás filas.
        - Al finalizar, la parte derecha de la matriz aumentada corresponde a la matriz inversa.
    Si en algún paso el pivote resulta ser cero, significa que la matriz no tiene inversa (su determinante es 0), y el 
    método devuelve null.
    Paso 4: Los resultados se envían al método // Guardar archivo de salida //.
        - Si la matriz no tiene inversa, se escribe un mensaje de que la matriz no tiene inversa.
        - Si la matriz sí tiene inversa, se guarda en el archivo de salida.
    Paso 5:Finalmente, el programa regresa al // Método principal del proceso // y muestra un mensaje en consola 
    indicando que el proceso se completó y el archivo fue guardado con éxito. En caso de error en cualquier parte del 
    proceso, se muestra un mensaje de error correspondiente.