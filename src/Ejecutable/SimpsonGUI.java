/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ejecutable;

import Clases.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.scilab.forge.jlatexmath.*;


/**
 *
 * @author angel
 */
public class SimpsonGUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SimpsonGUI.class.getName());
   
    /**
     * Creates new form SimpsonGUI
     */
    public SimpsonGUI() {
        initComponents();
        validador.setDaemon(true);
        validador.start();
        this.jL_Validacion.setVisible(false);
        
    }
    
    Thread validador = new Thread(() -> {
        while (true) {
            try {
                String poli =  this.jTF_Expresion.getText().trim();//jTF_Expresion.getText().trim();
                double a = Double.parseDouble(this.jTF_a.getText().trim());
                double b = Double.parseDouble(this.jTF_b.getText().trim());

                // Validación estricta del polinomio con x
                if (!LatexIntegralPolinomio.esPolinomioValido(poli)) {
                    this.jL_Validacion.setVisible(true);
                    this.jL_Validacion.setBackground(new Color(230, 41, 70));
                    this.jL_Validacion.setText("Expresión no válida");
                    this.jB_Calcular.setEnabled(false);
                    this.jL_MathLEX.setIcon(null);
                    this.jL_MathLEX.setText("");
                    continue;
                }
                
                // Intento de generar LaTeX
                try {
                    String latex = LatexIntegralPolinomio.generarIntegral(poli, a, b);
                    if (latex == null || latex.isEmpty()) throw new Exception();

                    TeXFormula formula = new TeXFormula(latex);

                    // Tu línea original, pero no existe getImage()
                    // TeXIcon icon = formula.createTeXIcon(TeXFormula.BOLD, 26);
                    TeXIcon icon = formula.createTeXIcon(1, 26);
                    icon.setInsets(new java.awt.Insets(5, 5, 5, 5));

                    /// ===============================
                    /// REEMPLAZA getImage() POR ESTO
                    /// ===============================
                    java.awt.image.BufferedImage img =
                            new java.awt.image.BufferedImage(
                                    icon.getIconWidth(),
                                    icon.getIconHeight(),
                                    java.awt.image.BufferedImage.TYPE_INT_ARGB
                            );

                    java.awt.Graphics2D g2 = img.createGraphics();
                    g2.setColor(new java.awt.Color(0,0,0,0));
                    g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
                    icon.paintIcon(null, g2, 0, 0);
                    g2.dispose();

                    ImageIcon image = new ImageIcon(img);
                    this.jL_MathLEX.setIcon(image);
                    this.jL_MathLEX.setText("");

                    /// ===============================
                    /// VALIDACIÓN CORRECTA
                    /// ===============================
                    
                    
                    if(a>=b){
                        this.jL_Validacion.setVisible(true);
                        this.jL_Validacion.setBackground(new Color(230, 41, 70));
                        this.jL_Validacion.setText("Expresión no válida");
                        this.jB_Calcular.setEnabled(false);
                    }else{
                        this.jL_Validacion.setVisible(true);
                        this.jL_Validacion.setBackground(new Color(145, 239, 144));
                        this.jL_Validacion.setText("Expresion Valida");
                        this.jB_Calcular.setEnabled(true);
                    }

                    
                } catch (Exception ex) {
                    this.jL_Validacion.setVisible(true);
                    this.jL_Validacion.setBackground(new Color(230, 41, 70));
                    this.jL_Validacion.setText("Expresión no válida");
                    this.jB_Calcular.setEnabled(false);
                }

            } catch (Exception ex) {
                // Error en a o b
                this.jL_Validacion.setVisible(true);
                this.jL_Validacion.setBackground(new Color(230, 41, 70));
                this.jL_Validacion.setText("Expresión no válida");
                this.jB_Calcular.setEnabled(false);
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                break;
            }
        }
    });
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_Main = new javax.swing.JPanel();
        jP_Grafica = new javax.swing.JPanel();
        jL_Titulo = new javax.swing.JLabel();
        jL_Procedimiento = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTA_Procedimiento = new javax.swing.JTextArea();
        jL_InsertarExpresion = new javax.swing.JLabel();
        jTF_Expresion = new javax.swing.JTextField();
        jL_a = new javax.swing.JLabel();
        jTF_a = new javax.swing.JTextField();
        jL_b = new javax.swing.JLabel();
        jTF_b = new javax.swing.JTextField();
        jL_n = new javax.swing.JLabel();
        jS_n = new javax.swing.JSpinner();
        jL_MathLEX = new javax.swing.JLabel();
        jL_Validacion = new javax.swing.JLabel();
        jB_Calcular = new javax.swing.JButton();
        jB_Ejemplo = new javax.swing.JButton();
        jB_Limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simpson 1/3");

        jP_Main.setBackground(new java.awt.Color(181, 225, 255));

        jP_Grafica.setPreferredSize(new java.awt.Dimension(330, 330));

        javax.swing.GroupLayout jP_GraficaLayout = new javax.swing.GroupLayout(jP_Grafica);
        jP_Grafica.setLayout(jP_GraficaLayout);
        jP_GraficaLayout.setHorizontalGroup(
            jP_GraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        jP_GraficaLayout.setVerticalGroup(
            jP_GraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jL_Titulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jL_Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Titulo.setText("Método Simpson 1/3 Multiple");
        jL_Titulo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jL_Procedimiento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jL_Procedimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Procedimiento.setText("MathLex");
        jL_Procedimiento.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTA_Procedimiento.setColumns(20);
        jTA_Procedimiento.setRows(5);
        jScrollPane1.setViewportView(jTA_Procedimiento);

        jL_InsertarExpresion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jL_InsertarExpresion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_InsertarExpresion.setText("Inserta Expresion");
        jL_InsertarExpresion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTF_Expresion.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jTF_Expresion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTF_Expresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_ExpresionActionPerformed(evt);
            }
        });

        jL_a.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jL_a.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_a.setText("a:");

        jTF_a.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTF_a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTF_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_aActionPerformed(evt);
            }
        });

        jL_b.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jL_b.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_b.setText("b:");

        jTF_b.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTF_b.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTF_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_bActionPerformed(evt);
            }
        });

        jL_n.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jL_n.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_n.setText("n:");

        jS_n.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jS_n.setModel(new javax.swing.SpinnerNumberModel(2, 2, null, 1));
        jS_n.setToolTipText("");

        jL_MathLEX.setBackground(new java.awt.Color(255, 255, 255));
        jL_MathLEX.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jL_MathLEX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_MathLEX.setOpaque(true);

        jL_Validacion.setBackground(new java.awt.Color(255, 255, 255));
        jL_Validacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jL_Validacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Validacion.setOpaque(true);

        jB_Calcular.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jB_Calcular.setText("Calcular");
        jB_Calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_CalcularActionPerformed(evt);
            }
        });

        jB_Ejemplo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jB_Ejemplo.setText("Ejemplo");
        jB_Ejemplo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_EjemploActionPerformed(evt);
            }
        });

        jB_Limpiar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jB_Limpiar.setText("Limpiar");
        jB_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_LimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jP_MainLayout = new javax.swing.GroupLayout(jP_Main);
        jP_Main.setLayout(jP_MainLayout);
        jP_MainLayout.setHorizontalGroup(
            jP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_MainLayout.createSequentialGroup()
                .addGroup(jP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jP_MainLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jL_Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jP_MainLayout.createSequentialGroup()
                        .addGroup(jP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jP_MainLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jL_InsertarExpresion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTF_Expresion)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_MainLayout.createSequentialGroup()
                                        .addComponent(jL_a, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jTF_a, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                        .addComponent(jL_b, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jTF_b, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                        .addComponent(jL_n, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(jS_n, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jL_Validacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jP_MainLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jB_Calcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jP_MainLayout.createSequentialGroup()
                                        .addComponent(jB_Ejemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jB_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1))))
                        .addGap(18, 18, 18)
                        .addComponent(jP_Grafica, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jP_MainLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jL_Procedimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jL_MathLEX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(15, 15, 15))
        );
        jP_MainLayout.setVerticalGroup(
            jP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jL_Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jP_MainLayout.createSequentialGroup()
                        .addComponent(jL_InsertarExpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jTF_Expresion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(jP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTF_a, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_a, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTF_b, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_b, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_n, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jS_n, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(jL_Validacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jP_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jB_Ejemplo)
                            .addComponent(jB_Limpiar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jB_Calcular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jP_Grafica, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_Procedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_MathLEX, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jP_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jP_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTF_ExpresionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_ExpresionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_ExpresionActionPerformed

    private void jTF_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_aActionPerformed

    private void jTF_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_bActionPerformed

    private void jB_CalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_CalcularActionPerformed

        
    try {
        // === 1. Leer datos de entrada ===
        String funcion = jTF_Expresion.getText().trim();
        double a = Double.parseDouble(jTF_a.getText().trim());
        double b = Double.parseDouble(jTF_b.getText().trim());
        int n = Integer.parseInt(jS_n.getValue().toString());

        // === 2. Crear log interno (si lo usas en tu calculador) ===
        StringBuilder log = new StringBuilder();

        // === 3. Calcular integral con Simpson 1/3 múltiple ===
        double integral = Simpson13Calculador.calcularSimpson(funcion, a, b, n, log);

        // === 4. Construir arreglos x[i] y y[i] ===
        double h = (b - a) / n;
        double[] x = new double[n + 1];
        double[] y = new double[n + 1];

        for (int i = 0; i <= n; i++) {
            x[i] = a + i * h;
            y[i] = Simpson13Calculador.evaluar(funcion, x[i]); // f(x)
        }

        // === 5. Derivada cuarta real y error ===
        double cuartaDer = Simpson13Calculador.maximoCuartaDerivada(funcion, a, b);
        double errorEst = Simpson13Calculador.errorTruncamiento(funcion, a, b, n);
        double errorTrun = Simpson13Calculador.errorAproximacion(funcion, a, b, n);

        // === 6. Generar reporte en formato limpio ===
        String reporte = Simpson13Reporte.generar(x, y, n, h, cuartaDer, errorEst);

        // === 7. Mostrar resultado en tu TextArea ===
        jTA_Procedimiento.setText(reporte);
        jTA_Procedimiento.append(String.format("Ea   : %.4f\n", errorTrun));
        
            // 4. Graficar
        ChartPanel grafica = GraphSimpson.generarGrafica(funcion, a, b, n);

        jP_Grafica.removeAll();
        jP_Grafica.setLayout(new java.awt.BorderLayout());
        jP_Grafica.add(grafica, java.awt.BorderLayout.CENTER);
        jP_Grafica.validate();
        jP_Grafica.repaint();
        
    } catch (Exception ex) {
        jTA_Procedimiento.setText("ERROR: " + ex.getMessage());
    }

        
        
    }//GEN-LAST:event_jB_CalcularActionPerformed

    private void jB_EjemploActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_EjemploActionPerformed
        
        jTF_Expresion.setText("-46+45.4x-13.8x^2+1.71x^3-0.0729x^4");
        jTF_a.setText("2");
        jTF_b.setText("10");
        jS_n.setValue(4);
            

    }//GEN-LAST:event_jB_EjemploActionPerformed

    private void jB_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_LimpiarActionPerformed
        jTF_Expresion.setText("");
        jTF_a.setText("");
        jTF_b.setText("");
        jL_MathLEX.setIcon(null);
        jS_n.setValue(2);

    }//GEN-LAST:event_jB_LimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new SimpsonGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_Calcular;
    private javax.swing.JButton jB_Ejemplo;
    private javax.swing.JButton jB_Limpiar;
    private javax.swing.JLabel jL_InsertarExpresion;
    private javax.swing.JLabel jL_MathLEX;
    private javax.swing.JLabel jL_Procedimiento;
    private javax.swing.JLabel jL_Titulo;
    private javax.swing.JLabel jL_Validacion;
    private javax.swing.JLabel jL_a;
    private javax.swing.JLabel jL_b;
    private javax.swing.JLabel jL_n;
    private javax.swing.JPanel jP_Grafica;
    private javax.swing.JPanel jP_Main;
    private javax.swing.JSpinner jS_n;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTA_Procedimiento;
    private javax.swing.JTextField jTF_Expresion;
    private javax.swing.JTextField jTF_a;
    private javax.swing.JTextField jTF_b;
    // End of variables declaration//GEN-END:variables
}
