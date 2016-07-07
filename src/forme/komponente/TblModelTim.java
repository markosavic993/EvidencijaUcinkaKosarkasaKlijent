/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.komponente;

import domen.Kosarkas;
import domen.Tim;
import forme.FrmLogovanje;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import komunikacija.KlijentKomunikacija;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konstante;

/**
 *
 * @author Marko
 */
public class TblModelTim extends AbstractTableModel{
    
    List<Tim> lt;

    public TblModelTim(List<Tim> lt) {
        this.lt = lt;
        System.out.println(lt.size());
    }
    

    @Override
    public int getRowCount() {
        return lt.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tim t = lt.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return t.getNaziv();
            case 1: return t.getGodinaOsnivanja();
            case 2: return t.getGrad();
            case 3: return t.getNazivHale();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Naziv";
            case 1: return "Godina osnivanja";
            case 2: return "Grad";
            case 3: return "Hala";
            default: return "n/a";
        }
    }

    
    public void obrisiTim(int t) {
        TransferObjekatZahtev toZahtev = new TransferObjekatZahtev();
        TransferObjekatOdgovor toOdgovor = new TransferObjekatOdgovor();
        toZahtev.setOperacija(Konstante.IZBRISI_TIM);
        toZahtev.setParametar(lt.get(t));
        KlijentKomunikacija.getInstance().posaljiZahtev(toZahtev);
        try {
            toOdgovor = KlijentKomunikacija.getInstance().primiOdgovor();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TblModelTim.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (toOdgovor.getIzuzetak() != null) {
            try {
                JOptionPane.showMessageDialog(null, "Tim ne može biti obrisan dok god ima zabeleženih utakmica u kojima je učestvovao!", "Greška!", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(FrmLogovanje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        lt.remove(t);
        fireTableDataChanged();
    }

    public List<Tim> getLt() {
        return lt;
    }
    
    public void osvezi() {
        fireTableDataChanged();
    }
    
    
}
