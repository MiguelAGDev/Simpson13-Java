package Clases;

public class LatexIntegralPolinomio {

    public static String generarIntegral(String polinomio, double a, double b) {
        if (!esPolinomioValido(polinomio)) return null;

        String poliLatex = convertirPolinomio(polinomio);
        return "\\int_{" + a + "}^{" + b + "} \\left(" + poliLatex + "\\right) \\, dx";
    }


    public static String convertirPolinomio(String poli) {
        if (poli == null || poli.isEmpty()) return "";

        poli = poli.replace(" ", "");
        String[] terminos = poli.replace("-", "+-").split("\\+");

        StringBuilder sb = new StringBuilder();

        for (String t : terminos) {
            if (t.isEmpty()) continue;

            // Si es negativo, NO antepongas " + "
            if (t.startsWith("-")) {
                sb.append(" ").append(formatearTermino(t));
            } else {
                if (sb.length() > 0) sb.append(" + ");
                sb.append(formatearTermino(t));
            }
        }

        return sb.toString().trim();
    }


    public static boolean esPolinomioValido(String poli) {
        if (poli == null || poli.trim().isEmpty()) return false;

        String p = poli.replace(" ", "");

        if (!p.matches("[0-9.xX^+\\-]*")) return false;

        if (p.matches(".*[a-wA-Wy-zY-Z].*")) return false;

        if (p.startsWith("^") || p.endsWith("^")) return false;

        String[] terms = p.replace("-", "+-").split("\\+");
        for (String t : terms) {
            if (t.isEmpty()) continue;

            if (!t.contains("x")) {
                if (!t.matches("[+-]?[0-9]*\\.?[0-9]+")) return false;
            } else {
                if (!t.matches("[+-]?([0-9]*\\.?[0-9]+)?x(\\^[0-9]+)?")) return false;
            }
        }

        return true;
    }


    private static String formatearTermino(String t) {

        if (!t.contains("x")) return t;

        String coef;
        String exp;

        if (t.contains("^")) {
            String[] parts = t.split("\\^");
            exp = parts[1];
            coef = parts[0].replace("x", "");
        } else {
            coef = t.replace("x", "");
            exp = "1";
        }

        // Casos:
        // coef = ""   → término era "x"     → dejar como "" (no poner 1)
        // coef = "-"  → término era "-x"    → dejar "-"

        if (coef.equals("+")) coef = "";
        if (coef.equals("")) coef = "";     // NO poner "1"
        if (coef.equals("-")) coef = "-";

        return coef + "x^{" + exp + "}";
    }
}
