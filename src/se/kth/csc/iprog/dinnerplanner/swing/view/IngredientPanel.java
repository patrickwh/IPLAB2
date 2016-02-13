package se.kth.csc.iprog.dinnerplanner.swing.view;

import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class IngredientPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    Font tableFont = new Font("Gill Sans MT", Font.PLAIN, 16);


    //    public IngredientPanel() {
//        super(new GridLayout(1, 0));
//        JTable table = new JTable(new MyTableModel());
//        table.setPreferredScrollableViewportSize(new Dimension(800, 60));
//        table.setFillsViewportHeight(true);
//        table.setAutoCreateRowSorter(true);
//
//        //Create the scroll pane and add the table to it.
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        //Add the scroll pane to this panel.
//        add(scrollPane);
//        //table.setFont(tableFont);
//
//    }
//
//    class MyTableModel extends AbstractTableModel {
//        private String[] columnNames = { "Ingredients", "Quantity", "Cost"};
//
//        private Object[][] data = { { "Egg", "11"+" ",  new Integer(5)+"$" },
//                { "Duck", "300"+"g",  new Integer(8)+"$" },
//                { "Pork", "180"+"g",  new Integer(2)+"$"},
//        };
//
//
//
//        public int getColumnCount() {
//            return columnNames.length;
//        }
//
//        public int getRowCount() {
//            return data.length;
//        }
//
//        public String getColumnName(int col) {
//            return columnNames[col];
//        }
//
//        public Object getValueAt(int row, int col) {
//            return data[row][col];
//        }
//
//
//
//    }
//
//    class EvenOddRenderer implements TableCellRenderer {
//
//        public  final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
//
//        public Component getTableCellRendererComponent(JTable table, Object value,
//                                                       boolean isSelected, boolean hasFocus, int row, int column) {
//            Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
//                    table, value, isSelected, hasFocus, row, column);
//            ((JLabel) renderer).setOpaque(true);
//            Color foreground, background;
//            if (isSelected) {
//                foreground = Color.yellow;
//                background = Color.green;
//            } else {
//                if (row % 2 == 0) {
//                    foreground = Color.blue;
//                    background = Color.white;
//                } else {
//                    foreground = Color.white;
//                    background = Color.blue;
//                }
//            }
//            renderer.setForeground(foreground);
//            renderer.setBackground(background);
//            return renderer;
//        }
//    }
//
//
//
    public static void main(String[] args) {

        IngredientPanel newContentPane = new IngredientPanel();
        newContentPane.creatAndShowGUI();
    }

    public void creatAndShowGUI() {
        JFrame frame = new JFrame("Dinner Planner - Ingredients");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create and set up the content pane.
        //IngredientPanel newContentPane = new IngredientPanel();
        this.setOpaque(true); //content panes must be opaque
        frame.setContentPane(this); //Display the window.
        TableCellRenderer renderer = new EvenOddRenderer();

        Object rows[][] = {{"Egg", "11" + " ", new Integer(5) + "$"},
                {"Duck", "300" + "g", new Integer(8) + "$"},
                {"Pork", "180" + "g", new Integer(2) + "$"},
        };
        Object headers[] = {"Ingredients", "Quantity", "Cost"};

        JTable table = new JTable(rows, headers);
        table.setFont(tableFont);
        table.setDefaultRenderer(Object.class, renderer);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setFont(tableFont);
        //frame.setSize(800, 600);
        frame.pack();
        frame.setVisible(true);
    }
//}


    class EvenOddRenderer implements TableCellRenderer {

        public final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            ((JLabel) renderer).setOpaque(true);
            Color foreground, background;
            if (isSelected) {
                foreground = Color.yellow;
                background = Color.green;
            } else {
                if (row % 2 == 0) {
                    foreground = Color.gray;
                    background = Color.lightGray;
                } else {
                    foreground = Color.lightGray;
                    background = Color.gray;
                }
            }
            renderer.setForeground(foreground);
            renderer.setBackground(background);
            return renderer;
        }
    }
}


