package main.java.ClassAdapter;

import javax.swing.table.AbstractTableModel;

public class RocketTableModel extends AbstractTableModel {

    protected Rocket[] rockets;
    protected String[] columnsNames = new String[]{"Name", "Price", "Apogee"};

    public RocketTableModel(Rocket[] rockets) {
        this.rockets = rockets;
    }

    @Override
    public int getRowCount() {
        return rockets.length;
    }

    @Override
    public int getColumnCount() {
        return columnsNames.length;
    }

    @Override
    public String getColumnName(int i) {
        return columnsNames[i];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> rockets[rowIndex].getName();
            case 1 -> rockets[rowIndex].getPrice();
            case 2 -> rockets[rowIndex].getApogee();
            default -> null;
        };
    }
}
