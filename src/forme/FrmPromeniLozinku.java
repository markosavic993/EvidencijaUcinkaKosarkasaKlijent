/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Korisnik;
import domen.Kosarkas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import komunikacija.KlijentKomunikacija;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konstante;

/**
 *
 * @author Marko
 */
public class FrmPromeniLozinku extends javax.swing.JDialog {

    FrmPocetna forma;

    /**
     * Creates new form FrmPromeniLozinku
     */
    public FrmPromeniLozinku(java.awt.Frame parent, boolean modal, FrmPocetna forma) {
        super(parent, modal);
        initComponents();
        this.forma = forma;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbtnPotvrdi = new javax.swing.JButton();
        jtxtNova = new javax.swing.JPasswordField();
        jtxtPotvrdi = new javax.swing.JPasswordField();
        jtxtStara = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Promena lozinke");

        jLabel1.setText("Unesite staru lozinku:");

        jLabel2.setText("Unesite novu lozinku:");

        jLabel3.setText("Potvrdite novu lozinku:");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxtNova)
                            .addComponent(jtxtStara, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jbtnPotvrdi)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jtxtPotvrdi, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtStara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtNova, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtPotvrdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jbtnPotvrdi)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnPotvrdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPotvrdiActionPerformed
        String stara = jtxtStara.getText().trim();
        String nova = jtxtNova.getText().trim();
        String potvrda = jtxtPotvrdi.getText().trim();

        if (validanUnos(stara, nova, potvrda)) {
            TransferObjekatZahtev toZahtev = new TransferObjekatZahtev();
            TransferObjekatOdgovor toOdgovor = new TransferObjekatOdgovor();
            toZahtev.setOperacija(Konstante.VRATI_SVE_KORISNIKE);
            toZahtev.setParametar(null);
            KlijentKomunikacija.getInstance().posaljiZahtev(toZahtev);
            try {
                toOdgovor = KlijentKomunikacija.getInstance().primiOdgovor();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(FrmPromeniLozinku.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (toOdgovor.getIzuzetak() != null) {
                try {
                    throw (Exception) toOdgovor.getIzuzetak();
                } catch (Exception ex) {
                    Logger.getLogger(FrmLogovanje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            List<Korisnik> lk = (List<Korisnik>) toOdgovor.getRezultat();
            for (Korisnik k : lk) {
                if (k.getPassword().equals(stara)) {
                    k.setPassword(nova);

                    toZahtev.setOperacija(Konstante.PROMENI_LOZINKU_KORISNIKA);
                    toZahtev.setParametar(k);
                    KlijentKomunikacija.getInstance().posaljiZahtev(toZahtev);
                    try {
                        toOdgovor = KlijentKomunikacija.getInstance().primiOdgovor();
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(FrmPromeniLozinku.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (toOdgovor.getIzuzetak() != null) {
                        try {
                            throw (Exception) toOdgovor.getIzuzetak();
                        } catch (Exception ex) {
                            Logger.getLogger(FrmLogovanje.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
             
                    JOptionPane.showMessageDialog(this, "Lozinka uspešno promenjena!");
                    forma.dispose();
                    FrmLogovanje frm = new FrmLogovanje();
                    frm.setVisible(true);
                    this.dispose();
                    return;
                }

            }
            JOptionPane.showMessageDialog(this, "Pogrešna lozinka!");
        }
    }//GEN-LAST:event_jbtnPotvrdiActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPromeniLozinku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPromeniLozinku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPromeniLozinku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPromeniLozinku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                FrmPromeniLozinku dialog = new FrmPromeniLozinku(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                FrmPromeniLozinku dialog = new FrmPromeniLozinku(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbtnPotvrdi;
    private javax.swing.JPasswordField jtxtNova;
    private javax.swing.JPasswordField jtxtPotvrdi;
    private javax.swing.JPasswordField jtxtStara;
    // End of variables declaration//GEN-END:variables

    private void pozicionirajFormu() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    private boolean validanUnos(String stara, String nova, String potvrda) {
        if (stara.isEmpty() || stara.length() < 4) {
            JOptionPane.showMessageDialog(this, "Lozinka mora sadržati bar 4 karaktera!", "Greška!", JOptionPane.WARNING_MESSAGE);
            jtxtStara.setBorder(BorderFactory.createLineBorder(Color.RED));
            jtxtStara.requestFocusInWindow();
            jtxtNova.setBorder(null);
            jtxtPotvrdi.setBorder(null);
            return false;
        }

        if (nova.isEmpty() || nova.length() < 4) {
            JOptionPane.showMessageDialog(this, "Lozinka mora sadržati bar 4 karaktera!", "Greška!", JOptionPane.WARNING_MESSAGE);
            jtxtNova.setBorder(BorderFactory.createLineBorder(Color.RED));
            jtxtNova.requestFocusInWindow();
            jtxtStara.setBorder(null);
            jtxtPotvrdi.setBorder(null);
            return false;
        }

        if (!nova.equals(potvrda)) {
            JOptionPane.showMessageDialog(this, "Lozinke se ne poklapaju!", "Greška!", JOptionPane.WARNING_MESSAGE);
            jtxtNova.setBorder(BorderFactory.createLineBorder(Color.RED));
            jtxtNova.requestFocusInWindow();
            jtxtPotvrdi.setBorder(BorderFactory.createLineBorder(Color.RED));
            jtxtStara.setBorder(null);
            return false;
        }

        return true;
    }

}
