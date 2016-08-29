/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Korisnik;
import domen.Utakmica;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.KlijentKomunikacija;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konstante;

/**
 *
 * @author Marko
 */
public class FrmIzborUtakmice extends javax.swing.JDialog {

    /**
     * Creates new form FrmIzborUtakmice
     */
    
    Korisnik k;
    
    public FrmIzborUtakmice(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        napuniCombo();
        pozicionirajFormu();
    }

    public FrmIzborUtakmice(java.awt.Frame parent, boolean modal, Korisnik k) {
        super(parent, modal);
        initComponents();
        this.k = k;
        napuniCombo();
        pozicionirajFormu();
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
        jcomboUtakmice = new javax.swing.JComboBox();
        jbtnPotvrdi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Izbor utakmice");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Izaberite utakmicu:");

        jcomboUtakmice.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbtnPotvrdi.setText("Potvrdi");
        jbtnPotvrdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPotvrdiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcomboUtakmice, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jbtnPotvrdi)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(314, 314, 314))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jcomboUtakmice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jbtnPotvrdi)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnPotvrdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPotvrdiActionPerformed
        Utakmica u = (Utakmica) jcomboUtakmice.getSelectedItem();
        FrmDodajUcinak forma = new FrmDodajUcinak(null, true, u, k);
        forma.setVisible(true);
    }//GEN-LAST:event_jbtnPotvrdiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmIzborUtakmice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmIzborUtakmice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmIzborUtakmice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmIzborUtakmice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                FrmIzborUtakmice dialog = new FrmIzborUtakmice(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbtnPotvrdi;
    private javax.swing.JComboBox jcomboUtakmice;
    // End of variables declaration//GEN-END:variables

    private void napuniCombo() {
        jcomboUtakmice.removeAllItems();

        TransferObjekatZahtev toZahtev = new TransferObjekatZahtev();
        TransferObjekatOdgovor toOdgovor = new TransferObjekatOdgovor();
        toZahtev.setOperacija(Konstante.VRATI_UTAKMICE);
        toZahtev.setParametar(null);
        KlijentKomunikacija.getInstance().posaljiZahtev(toZahtev);
        try {
            toOdgovor = KlijentKomunikacija.getInstance().primiOdgovor();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FrmIzborUtakmice.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (toOdgovor.getIzuzetak() != null) {
            try {
                throw (Exception) toOdgovor.getIzuzetak();
            } catch (Exception ex) {
                Logger.getLogger(FrmLogovanje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (Utakmica u : (List<Utakmica>) toOdgovor.getRezultat()) {
            jcomboUtakmice.addItem(u);
        }
    }

    private void pozicionirajFormu() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }
}
