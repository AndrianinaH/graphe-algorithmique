package M1.Frame;

import com.alee.laf.WebLookAndFeel;

import javax.swing.*;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class SuppArcFrame extends javax.swing.JFrame {

    /**
     * Creates new form ArcSupprFrame
     */
    public SuppArcFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        arcsCombo = new javax.swing.JComboBox<>();
        suppressionButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Liste des Arcs :");

        suppressionButton.setText("Supprimer");
        suppressionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppressionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(suppressionButton)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                                .addComponent(arcsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(57, 57, 57))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(arcsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addComponent(suppressionButton)
                                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void suppressionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppressionButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_suppressionButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])throws Exception {
        UIManager.setLookAndFeel ( new WebLookAndFeel() );
        UIManager.setLookAndFeel ( "com.alee.laf.WebLookAndFeel" );
        UIManager.setLookAndFeel ( WebLookAndFeel.class.getCanonicalName() );
        new SuppArcFrame().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> arcsCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton suppressionButton;
    // End of variables declaration//GEN-END:variables

    public JComboBox<String> getArcsCombo() {
        return arcsCombo;
    }

    public void setArcsCombo(JComboBox<String> arcsCombo) {
        this.arcsCombo = arcsCombo;
    }

    public JButton getSuppressionButton() {
        return suppressionButton;
    }

    public void setSuppressionButton(JButton suppressionButton) {
        this.suppressionButton = suppressionButton;
    }

}
