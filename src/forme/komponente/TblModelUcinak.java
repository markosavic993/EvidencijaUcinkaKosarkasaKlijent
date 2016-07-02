/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.komponente;

import domen.Ucinak;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class TblModelUcinak extends AbstractTableModel{
    
    List<Ucinak> listaUcinaka;

    public TblModelUcinak() {
        listaUcinaka = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return listaUcinaka.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ucinak u = listaUcinaka.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return u.getKosarkas().getTimZaKojiNastupa();
            case 1: return u.getKosarkas();
            case 2: return u.getTipUcinka().getNaziv();
            case 3: return u.getVrednost();
                default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Tim";
            case 1: return "Košarkaš";
            case 2: return "Tip učinka";
            case 3: return "Vrednost";
                default: return "n/a";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        listaUcinaka.get(rowIndex).setVrednost(Integer.parseInt((String) aValue));
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public void dodajRed(Ucinak u) {
        listaUcinaka.add(u);
        fireTableDataChanged();
    }
    
    public void obrisiRed(int ucinak) {
        listaUcinaka.remove(ucinak);
        fireTableDataChanged();
    }
    
    public void isprazniListuUcinaka() {
        listaUcinaka.clear();
        fireTableDataChanged();
    }

    public List<Ucinak> getListaUcinaka() {
        return listaUcinaka;
    }
    
}
