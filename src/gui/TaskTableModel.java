package gui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TaskTableModel extends AbstractTableModel {
    private int countColumn = 1;
    private ArrayList<String[]> dataArrayList;

    public TaskTableModel() {
        dataArrayList = new ArrayList<String[]>();
        for (int i = 0; i < dataArrayList.size(); i++) {
            dataArrayList.add(new String[countColumn]);
        }
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return countColumn;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "имя";
        }
        return "";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
        dataArrayList.get(rowIndex)[columnIndex] = String.valueOf(aValue);
    }

    public void addDate(String[] rows) {
        String[] rowTable = new String[getColumnCount()];
        rowTable = rows;
        dataArrayList.add(rowTable);
    }

    public void removeIsAll() {
        dataArrayList = new ArrayList<String[]>();
    }

    public void removeIsRows(int rows) {
        dataArrayList.remove(rows);
    }


}
