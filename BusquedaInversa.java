package BUSQUEDAINVERSA;

import java.io.*;
import java.util.*;

public class BusquedaInversa {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Leer matriz desde archivo
    public double[][] fileToMatrix(String fileName) {
        File file;
        FileReader reader;
        BufferedReader buffer;
        String linea;
        List<double[]> filas = new ArrayList<>();

        try {
            file = new File("C:\\archivos\\" + fileName);
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            while ((linea = buffer.readLine()) != null) {
                String[] partes = linea.trim().split("\\s+");
                double[] fila = new double[partes.length];

                for (int i = 0; i < partes.length; i++) {
                    fila[i] = Double.parseDouble(partes[i]);
                }
                filas.add(fila);
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e.toString());
        }

        return filas.toArray(new double[0][]);
    }

    // Guardar matriz en archivo
    public void writeMatrixToFile(String fileName, double[][] matriz) {
        FileWriter file;
        PrintWriter writer;

        try {
            file = new FileWriter("C:\\archivos\\" + fileName);
            writer = new PrintWriter(file);

            for (double[] fila : matriz) {
                for (double v : fila) {
                    writer.printf("%.4f ", v);
                }
                writer.println();
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error al crear archivo: " + e.toString());
        }
    }

    // Guardar texto en archivo
    public void writeTextToFile(String fileName, String texto) {
        FileWriter file;
        PrintWriter writer;

        try {
            file = new FileWriter("C:\\archivos\\" + fileName);
            writer = new PrintWriter(file);

            writer.println(texto);

            writer.close();

        } catch (Exception e) {
            System.out.println("Error al escribir archivo: " + e.toString());
        }
    }

    // Calcular inversa con Gauss-Jordan
    public double[][] calcularInversa(double[][] matriz) {
        int n = matriz.length;

        double[][] aug = new double[n][2 * n];

        // Construcción de matriz aumentada [A | I]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aug[i][j] = matriz[i][j];
            }
            aug[i][i + n] = 1;
        }

        for (int i = 0; i < n; i++) {
            double pivote = aug[i][i];
            if (pivote == 0)
                return null;

            // Normalizar fila pivote
            for (int j = 0; j < 2 * n; j++) {
                aug[i][j] /= pivote;
            }

            // Ceros en las otras filas
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = aug[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        aug[k][j] -= factor * aug[i][j];
                    }
                }
            }
        }

        // Extraer la inversa
        double[][] inversa = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(aug[i], n, inversa[i], 0, n);
        }

        return inversa;
    }

    // Método principal del proceso
    public void procesarArchivo() {
        try {
            System.out.print("Nombre del archivo de entrada: ");
            String entrada = br.readLine();

            System.out.print("Nombre del archivo de salida: ");
            String salida = br.readLine();

            // Leer matriz
            double[][] matriz = fileToMatrix(entrada);

            // Calcular inversa
            double[][] inversa = calcularInversa(matriz);

            // Guardar archivo de salida
            if (inversa == null) {
                writeTextToFile(salida, "La matriz NO tiene inversa (determinante = 0).");
            } else {
                writeMatrixToFile(salida, inversa);
            }

            System.out.println("Proceso completado. Archivo guardado como: " + salida);

        } catch (IOException e) {
            System.out.println("Error al leer datos del usuario: " + e.toString());
        }
    }

    public static void main(String[] args) {
        BusquedaInversa app = new BusquedaInversa();
        app.procesarArchivo();
    }
}
