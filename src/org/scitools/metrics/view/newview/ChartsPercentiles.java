/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scitools.metrics.view.newview;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.RefineryUtilities;
import org.scitools.metrics.control.CGAllFInalCharts;
import org.scitools.metrics.model.beans.MetricaLinhas;
import org.scitools.metrics.model.util.ManipulaDir;
import org.scitools.metrics.view.util.Chart;

/**
 *
 * @author fernando
 */
public class ChartsPercentiles extends javax.swing.JFrame {

	private static final long serialVersionUID = -209268259356964717L;
	/**
     * Creates new form ChartsPercentiles
     */
    public ChartsPercentiles() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton21 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scitools/metrics/view/newview/imgs/save.png"))); // NOI18N
        jButton21.setText("Save");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton21, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 4, Short.MAX_VALUE)
                .addGap(649, 649, 649))
            .addComponent(jTabbedPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        String caminho = "";
        JFileChooser arquivo = new JFileChooser();
        arquivo.setDialogType(JFileChooser.SAVE_DIALOG);
        arquivo.setMultiSelectionEnabled(false);
        arquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        arquivo.setCurrentDirectory(new File(pathOutput));
        int retorno = arquivo.showSaveDialog(null);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            caminho = arquivo.getSelectedFile().getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(null, "Inválid Folder");
        }
        if (!caminho.isEmpty()) {
            for (JFreeChart g : graficos) {
                try {
                    ChartUtilities.saveChartAsJPEG(new File(caminho + "//Percentile-" + g.getTitle().getText() + ".jpg"), g, 800, 600);
                } catch (IOException x) {
                    System.err.println("Erro na gravação");
                    JOptionPane.showMessageDialog(null, "Error of file.");
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null, "Error of file.");
                    //x.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton21ActionPerformed
    private String pathOutput;
    private ArrayList<JFreeChart> graficos;

    public void geraGraficosP(String pathOutput) {
        this.pathOutput = pathOutput;
        graficos = new ArrayList<JFreeChart>();
        CGAllFInalCharts k = new CGAllFInalCharts();
        ArrayList<String> nomesArquivosPercentiles = new ArrayList<String>();
        //pega o nome dos aruqivos
        ManipulaDir.listar(new File(pathOutput + "//data-percentiles"), 0, nomesArquivosPercentiles);
        ArrayList<MetricaLinhas> mls = k.generateDataP(pathOutput + "//data-percentiles", nomesArquivosPercentiles);
        
        for (MetricaLinhas ml : mls) {
            Chart demo = new Chart(ml.getK(), ml.getNomeMetrica().replaceAll("metric_", "").replaceAll(".csv", ""),jTabbedPane1.getWidth()-10, jTabbedPane1.getHeight()-60,
                    ml.getLinhas(), ml.getNomeMetrica().replaceAll("metric_", "").replaceAll(".csv", ""), "p%", ml.getNomeMetrica().replaceAll("metric_", "").replaceAll(".csv", ""), false,true);
            demo.pack();
            RefineryUtilities.centerFrameOnScreen(demo);
            graficos.add(demo.getJFreechart());
            JPanel jp = new JPanel();
            jp.add(demo.getContentPane());
            jTabbedPane1.addTab(ml.getNomeMetrica().replaceAll("metric_", "").replaceAll(".csv", ""), jp);
        }
    }

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChartsPercentiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChartsPercentiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChartsPercentiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChartsPercentiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChartsPercentiles().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
