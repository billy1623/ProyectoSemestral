import java.util.*;

/**
 * Clase principal que resuelve los enunciados del Proyecto Semestral.
 */
public class proyectoSemestral {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menú principal
        int opcion;
        do {
            System.out.println("\n--- PROYECTO SEMESTRAL ---");
            System.out.println("1. Listado de números y sumatoria (Enunciado 1155)");
            System.out.println("2. Calcular Domingo de Pascua (Enunciado 1885)");
            System.out.println("3. Calcular moda de un conjunto (Enunciado 3245)");
            System.out.println("4. Operaciones con matriz (Enunciado 7888)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    resolverEnunciado1155(scanner);
                    break;
                case 2:
                    resolverEnunciado1885(scanner);
                    break;
                case 3:
                    resolverEnunciado3245(scanner);
                    break;
                case 4:
                    resolverEnunciado7888(scanner);
                    break;
                case 0:
                    System.out.println("¡Gracias por usar el programa!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    /**
     * Resuelve el Enunciado 1155.
     */
    private static void resolverEnunciado1155(Scanner scanner) {
        System.out.print("Ingrese un valor entero (M) mayor a 11: ");
        int M = scanner.nextInt();
        while (M <= 11) {
            System.out.print("El valor debe ser mayor a 11. Intente nuevamente: ");
            M = scanner.nextInt();
        }

        int sumatoriaTotal = 0;
        for (int i = 1; i <= M; i += 5) {
            int sumatoriaLinea = 0;
            for (int j = 0; j < 5 && (i + j) <= M; j++) {
                System.out.print((i + j) + "\t");
                sumatoriaLinea += (i + j);
            }
            System.out.println("= " + sumatoriaLinea);
            sumatoriaTotal += sumatoriaLinea;
        }
        System.out.println("Sumatoria total: " + sumatoriaTotal);
    }

    /**
     * Resuelve el Enunciado 1885.
     */
    private static void resolverEnunciado1885(Scanner scanner) {
        while (true) {
            System.out.print("Ingrese un año de 4 dígitos (o 0000/9999 para salir): ");
            int year = scanner.nextInt();
            if (year == 0 || year == 9999) break;

            int A = year % 19;
            int B = year % 4;
            int C = year % 7;
            int D = (19 * A + 24) % 30;
            int E = (2 * B + 4 * C + 6 * D + 5) % 7;
            int N = 22 + D + E;

            if (N <= 31) {
                System.out.println("Domingo de Pascua: " + N + " de marzo.");
            } else {
                System.out.println("Domingo de Pascua: " + (N - 31) + " de abril.");
            }
        }
    }

    /**
     * Resuelve el Enunciado 3245.
     */
    private static void resolverEnunciado3245(Scanner scanner) {
        System.out.print("Ingrese el tamaño del conjunto de datos (n): ");
        int n = scanner.nextInt();
        System.out.print("Ingrese el límite inferior (w): ");
        int w = scanner.nextInt();
        System.out.print("Ingrese el límite superior (x): ");
        int x = scanner.nextInt();

        int[] muestra = new int[n];
        Random random = new Random();
        Map<Integer, Integer> frecuencias = new HashMap<>();
        for (int i = 0; i < n; i++) {
            muestra[i] = random.nextInt(x - w + 1) + w;
            frecuencias.put(muestra[i], frecuencias.getOrDefault(muestra[i], 0) + 1);
        }

        System.out.println("Datos generados: " + Arrays.toString(muestra));

        int maxFrecuencia = Collections.max(frecuencias.values());
        System.out.println("Moda(s):");
        for (Map.Entry<Integer, Integer> entry : frecuencias.entrySet()) {
            if (entry.getValue() == maxFrecuencia) {
                System.out.println(entry.getKey() + " (frecuencia: " + entry.getValue() + ")");
            }
        }
    }

    /**
     * Resuelve el Enunciado 7888.
     */
    private static void resolverEnunciado7888(Scanner scanner) {
        System.out.print("Ingrese el número de filas (m, mínimo 3): ");
        int m = scanner.nextInt();
        System.out.print("Ingrese el número de columnas (n, mínimo 3): ");
        int n = scanner.nextInt();

        while (m < 3 || n < 3 || m > n) {
            System.out.println("m y n deben ser al menos 3 y m no puede ser mayor que n.");
            System.out.print("Ingrese m nuevamente: ");
            m = scanner.nextInt();
            System.out.print("Ingrese n nuevamente: ");
            n = scanner.nextInt();
        }

        int[][] matriz = new int[m][n];
        Random random = new Random();

        int sumatoriaTotal = 0;
        int sumaColumnasImpares = 0;
        List<Integer> mayoresDe25 = new ArrayList<>();
        List<String> coordenadasMayoresDe25 = new ArrayList<>();
        List<Integer> menoresDe10 = new ArrayList<>();
        List<String> coordenadasMenoresDe10 = new ArrayList<>();
        int sumaFilasPares = 0;
        int cuentaFilasPares = 0;

        // Llenar matriz y realizar cálculos básicos
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = random.nextInt(91) + 5; // Valores entre 5 y 95
                sumatoriaTotal += matriz[i][j];

                // Identificar valores > 25 y < 10
                if (matriz[i][j] > 25) {
                    mayoresDe25.add(matriz[i][j]);
                    coordenadasMayoresDe25.add("(" + i + "," + j + ")");
                }
                if (matriz[i][j] < 10) {
                    menoresDe10.add(matriz[i][j]);
                    coordenadasMenoresDe10.add("(" + i + "," + j + ")");
                }

                // Sumar valores de columnas impares
                if (j % 2 == 0) {
                    sumaColumnasImpares += matriz[i][j];
                }
            }

            // Promedio de filas pares
            if (i % 2 == 1) {
                for (int valor : matriz[i]) {
                    sumaFilasPares += valor;
                }
                cuentaFilasPares++;
            }
        }

        // Calcular menor y mayor de la diagonal principal
        int menorDiagonal = Integer.MAX_VALUE;
        int mayorDiagonal = Integer.MIN_VALUE;
        for (int i = 0; i < Math.min(m, n); i++) {
            int valorDiagonal = matriz[i][i];
            if (valorDiagonal < menorDiagonal) menorDiagonal = valorDiagonal;
            if (valorDiagonal > mayorDiagonal) mayorDiagonal = valorDiagonal;
        }

        // Llenar vectores de menores y mayores
        int[] menores = new int[m];
        int[] mayores = new int[n];
        for (int i = 0; i < m; i++) {
            menores[i] = Arrays.stream(matriz[i]).min().orElse(Integer.MAX_VALUE);
        }
        for (int j = 0; j < n; j++) {
            int mayorColumna = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                if (matriz[i][j] > mayorColumna) mayorColumna = matriz[i][j];
            }
            mayores[j] = mayorColumna;
        }

        // Imprimir matriz
        System.out.println("Contenido de la matriz:");
        for (int[] fila : matriz) {
            System.out.println(Arrays.toString(fila));
        }

        // Resultados
        System.out.println("Sumatoria total: " + sumatoriaTotal);
        System.out.println("Promedio: " + (sumatoriaTotal / (double) (m * n)));
        System.out.println("Elementos > 25: " + mayoresDe25 + " en coordenadas: " + coordenadasMayoresDe25);
        System.out.println("Elementos < 10: " + menoresDe10 + " en coordenadas: " + coordenadasMenoresDe10);
        System.out.println("Sumatoria de columnas impares: " + sumaColumnasImpares);
        System.out.println("Promedio de filas pares: " + (sumaFilasPares / (double) cuentaFilasPares));
        System.out.println("Mayor de la diagonal principal: " + mayorDiagonal);
        System.out.println("Menor de la diagonal principal: " + menorDiagonal);

        // Imprimir vectores
        System.out.println("Vector de menores por fila: " + Arrays.toString(menores));
        System.out.println("Vector de mayores por columna: " + Arrays.toString(mayores));

        // Buscar valores repetidos
        System.out.println("Coordenadas de celdas con valores iguales:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = i; x < m; x++) {
                    for (int y = (x == i ? j + 1 : 0); y < n; y++) {
                        if (matriz[i][j] == matriz[x][y]) {
                            System.out.println("Valor: " + matriz[i][j] + " en (" + i + "," + j + ") y (" + x + "," + y + ")");
                        }
                    }
                }
            }
        }

        // Imprimir matriz nuevamente
        System.out.println("Contenido final de la matriz:");
        for (int[] fila : matriz) {
            System.out.println(Arrays.toString(fila));
        }
    }

}
