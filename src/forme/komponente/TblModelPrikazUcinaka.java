/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.komponente;

import domen.Kosarkas;
import domen.TipUcinka;
import domen.UcinakKosarkasa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class TblModelPrikazUcinaka extends AbstractTableModel{

    List<TipUcinka> tipoviUcinaka;
    List<UcinakKosarkasa> ucinci;
    List<Kosarkas> kosarkasi;

    public TblModelPrikazUcinaka(List<TipUcinka> tipoviUcinaka, List<UcinakKosarkasa> ucinci, List<Kosarkas> kosarkasi) {
        this.tipoviUcinaka = tipoviUcinaka;
        this.ucinci = ucinci;
        this.kosarkasi = kosarkasi;
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
            for (UcinakKosarkasa ucinak : ucinci) {
                if(ucinak.getKosarkas().equals(kosarkasi.get(rowIndex)) && ucinak.getTipUcinka().equals(tipoviUcinaka.get(columnIndex-1))) {
                    //System.out.println(ucinak.getKosarkas() +":"+ kosarkasi.get(rowIndex) + "**********" +ucinak.getTipUcinka());
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
