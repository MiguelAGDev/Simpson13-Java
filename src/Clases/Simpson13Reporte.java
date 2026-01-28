package Clases;

public class Simpson13Reporte {

    public static String generar(
            double[] x,
            double[] y,
            int n,
            double h,
            double cuartaDerivadaMax,
            double errorEstimado
    ) {

        StringBuilder r = new StringBuilder();

        r.append("=== DATOS DE ENTRADA ===\n");
        r.append(String.format("Intervalo inicial: %.4f\n", x[0]));
        r.append(String.format("Intervalo final  : %.4f\n", x[n]));
        r.append(String.format("Subdivisiones    : %d\n", n));
        r.append(String.format("Valor de h       : %.4f\n\n", h));

        r.append("=== TABLA DE PUNTOS ===\n");

        double sumaImpares = 0;
        double sumaPares = 0;

        for (int i = 0; i <= n; i++) {

            String tipo;

            if (i == 0 || i == n) {
                tipo = "Borde";
            } else if ((i & 1) == 1) {
                tipo = "Impar";
                sumaImpares += y[i];
            } else {
                tipo = "Par";
                sumaPares += y[i];
            }

            r.append(String.format(
                    "i=%d | x=%.4f | f=%.4f | %s\n",
                    i, x[i], y[i], tipo
            ));
        }

        r.append("\n=== ACUMULADOS ===\n");
        r.append(String.format("∑ impares : %.4f\n", sumaImpares));
        r.append(String.format("∑ pares   : %.4f\n\n", sumaPares));

        r.append("=== RESULTADO NUMÉRICO ===\n");

        double I = (h / 3.0) * (y[0] + 4 * sumaImpares + 2 * sumaPares + y[n]);

        r.append(String.format("Valor aproximado de la integral: %.4f\n\n", I));

        r.append("=== ESTIMACIÓN DE ERROR ===\n");
        //r.append(String.format("Máximo |f''''(x)| : %.6f\n", cuartaDerivadaMax));
        r.append(String.format("Ea   : %.4f\n", errorEstimado));

        return r.toString();
    }
}
