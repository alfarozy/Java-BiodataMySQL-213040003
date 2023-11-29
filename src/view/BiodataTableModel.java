package view;

import javax.swing.table.*;
import model.Biodata;
import java.util.List;

public class BiodataTableModel extends AbstractTableModel {
    private String[] columnNames = { "Nama", "No Telp", "Alamat", "Jenis Kelamin" };
    private List<Biodata> data;

    public BiodataTableModel(List<Biodata> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Biodata rowItem = data.get(row);
        String value = "";

        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
            case 1:
                value = rowItem.getNoTelp();
                break;
            case 2:
                value = rowItem.getAlamat();
                break;
            case 3:
                value = rowItem.getJenisKelamin();
                break;
            case 4:
                value = rowItem.getId();
                break;
        }

        return value;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Biodata value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void remove(int value) {
        data.remove(value);
        fireTableRowsDeleted(data.size() - 1, data.size() - 1);
    }
    
    
    public Biodata getBiodata(int row) {
        if (row >= 0 && row < data.size()) {
            return data.get(row);
        } else {
            return null;
        }
    }
}
