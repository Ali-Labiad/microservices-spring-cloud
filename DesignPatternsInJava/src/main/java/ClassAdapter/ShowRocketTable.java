package main.java.ClassAdapter;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class ShowRocketTable {
    public static void main(String[] args) {
        setFonts();
        JTable table = new JTable(getRocketTable());
        table.setRowHeight(36);
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(
                new java.awt.Dimension(300, 100));
        display(pane, " Rockets");
    }

    public static void display(Component c, String title) {
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(c);
        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static RocketTableModel getRocketTable() {
        Rocket r1 = new Rocket(
                "Shooter", BigDecimal.valueOf(65), 1.0);
        Rocket r2 = new Rocket(
                "Orbit", BigDecimal.valueOf(65), 2.0);
        return new RocketTableModel(new Rocket[]{r1, r2});
    }

    private static void setFonts() {
        Font font = new Font("Dialog", Font.PLAIN, 18);
        UIManager.put("Table.font", font);
        UIManager.put("TableHeader.font", font);
    }
}
