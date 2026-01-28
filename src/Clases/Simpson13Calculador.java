package Clases;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.matheclipse.core.eval.*;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

public class Simpson13Calculador {

    /// Metodo par arealizar el calculo de metodo simpsotn 1/3
    public static double calcularSimpson(String funcion, double a, double b, int n, StringBuilder log) throws IllegalArgumentException {


        /// Validamos cada una de las entradas del metodo
        if (a >= b) throw new IllegalArgumentException("El limite inferior (a) debe ser menor que el limite superior (b).");
        if (n < 2) throw new IllegalArgumentException("El numero de segmentos debe ser al menos 2.");
        if (n % 2 != 0) throw new IllegalArgumentException("El numero de segmentos debe ser par.");
        /// Calculamos h
        double h = (b - a) / n;

        double sumaPares = 0;
        double sumaImpares = 0;
        /// Declaramos un ciclo para realizar cada uno de los calculos en f(xn)
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            double fx = evaluar(funcion, x);
            if (i % 2 == 0) {
                sumaPares += fx;
            } else {
                sumaImpares += fx;
            }
        }

        /// Evaluamos los extremos
        double f0 = evaluar(funcion, a);
        double fn = evaluar(funcion, b);


        /// Aproximacion integral
        double integral = (h / 3) * (f0 + 4 * sumaImpares + 2 * sumaPares + fn);

        return integral;
    }


    /// Metodo que construye la expresion matematica para evaluarla
    public static double evaluar(String funcion, double x) {
        Expression e = new ExpressionBuilder(funcion)
                           .variable("x")
                           .build()
                           .setVariable("x", x);

        return e.evaluate();
    }


    public static String obtenerCuartaDerivada(String funcion) {
        ExprEvaluator util = new ExprEvaluator(false, (short) 100);

        /// D(D(D(D(f,x),x),x),x)
        String cmd = "D(D(D(D(" + funcion + ", x), x), x), x)";
        IExpr d4 = util.eval(cmd);

        return d4.toString();   // esto exp4j lo puede evaluar
    }

    public static double evaluarCuartaDerivada(String funcion, double x) {
        String d4 = obtenerCuartaDerivada(funcion);
        return evaluar(d4, x);
    }

    public static double maximoCuartaDerivada(String funcion, double a, double b) {
        double max = 0;
        for (int i = 0; i <= 1000; i++) {
            double x = a + (b - a) * i / 1000.0;
            double val = Math.abs(evaluarCuartaDerivada(funcion, x));
            if (val > max) max = val;
        }
        return max;
    }

    public static double errorTruncamiento(String funcion, double a, double b, int n) {
        double h = (b - a) / n;
        double maxDerivada = maximoCuartaDerivada(funcion, a, b);
        return ((b - a) * Math.pow(h, 4) / 180.0) * maxDerivada;
    }

    public static double errorAproximacion(String funcion, double a, double b, int n) {
        ExprEvaluator util = new ExprEvaluator(false, (short)100);

        String integralCmd = "N( Integrate(" + funcion + ", {x," + a + "," + b + "}) )";
        IExpr integralExactaExpr = util.eval(integralCmd);

        double integralExacta = Double.parseDouble(integralExactaExpr.toString());
        double aproximada = calcularSimpson(funcion, a, b, n, null);

        return Math.abs((integralExacta - aproximada)/integralExacta) * 100;
    }
}
