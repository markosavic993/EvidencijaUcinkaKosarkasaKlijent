/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.komponente;

import domen.Kosarkas;
import domen.TipUcinka;
import domen.Ucinak;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class TblModelPrikazUcinaka extends AbstractTableModel{

    List<TipUcinka> tipoviUcinaka;
    List<Ucinak> ucinci;
    List<Kosarkas> kosarkasi = new ArrayList<>();

    public TblModelPrikazUcinaka(List<TipUcinka> tipoviUcinaka, List<Ucinak> ucinci) {
        this.tipoviUcinaka = tipoviUcinaka;
        this.ucinci = ucinci;
        for (Ucinak ucinak : ucinci) {
            if(!kosarkasi.contains(ucinak.getKosarkas())) {
                kosarkasi.add(ucinak.getKosarkas());
            }
        }
    }
    
    
    
    @Override
    public int getRowCount() {
        return kosarkasi.size();
    }

    @Override
    public int getColumnCount() {
        return tipoviUcinaka.size() + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) return kosarkasi.get(rowIndex);
        else {
            for (Ucinak ucinak : ucinci) {
                if(ucinak.getKosarkas().equals(kosarkasi.get(rowIndex)) && ucinak.equals(tipoviUcinaka.get(columnIndex-1))) {
                    return ucinak.getVrednost();
                }
            }
            return "NP";
        }
    }

    @Override
    public String getColumnName(int column) {
        if(column == 0) return "Košarkaš";
        else {
            return ""+tipoviUcinaka.get(column-1);
        }
    }
    
    
    
}
