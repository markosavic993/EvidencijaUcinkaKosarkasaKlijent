/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.komponente;

import domen.TipUcinka;
import forme.FrmLogovanje;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import komunikacija.KlijentKomunikacija;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konstante;

/**
 *
 * @author Marko
 */
public class TblModelTipUcinka extends AbstractTableModel {
    TransferObjekatZahtev toZahtev = new TransferObjekatZahtev();
    TransferObjekatOdgovor toOdgovor = new TransferObjekatOdgovor();
    List<TipUcinka> listaTipova;

    public TblModelTipUcinka(List<TipUcinka> listaTipova) {
        this.listaTipova = listaTipova;
    }

    public List<TipUcinka> getListaTipova() {
        return listaTipova;
    }
    
    @Override
    public int getRowCount() {
        return listaTipova.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TipUcinka tip = listaTipova.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return tip.getNaziv();
            case 1: return tip.getOpis();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Naziv tipa";
            case 1: return "Kratak opis";
            default: return "n/a";
        }
    }

    public void obrisiTip(int selectedRow) {
        toZahtev.setOperacija(Konstante.IZBRISI_TIP_UCINKA);
        toZahtev.setParametar(listaTipova.get(selectedRow));
        KlijentKomunikacija.getInstance().posaljiZahtev(toZahtev);
        try {
            toOdgovor = KlijentKomunikacija.getInstance().primiOdgovor();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TblModelTipUcinka.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (toOdgovor.getIzuzetak() != null) {
            try {
                throw (Exception) toOdgovor.getIzuzetak();
            } catch (Exception ex) {
                Logger.getLogger(FrmLogovanje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        listaTipova.remove(selectedRow);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TipUcinka tip = listaTipova.get(rowIndex);
        switch(columnIndex) {
            case 0: tip.setNaziv((String) aValue);
            case 1: tip.setOpis((String) aValue);
        }
    }
    
    
}
