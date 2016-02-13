package se.kth.csc.iprog.dinnerplanner.swing.view;

import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class IngredientPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    Font tableFont=new  Font("Gill Sans MT",Font.PLAIN,20);

    public IngredientPanel() {
        super(new GridLayout(1, 0));
        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(800, 60));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
        //table.setFont(tableFont);
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = { "Ingredients", "Quantity", "Cost"};

        private Object[][] data = { { "Egg", "11"+" ",  new Integer(5)+"$" },
                { "Duck", "300"+"g",  new Integer(8)+"$" },
                { "Pork", "180"+"g",  new Integer(2)+"$"},
        };

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
//        public boolean isCellEditable(int row, int col) { //Note that the data/cell address is constant,
//            //no matter where the cell appears onscreen.
//            if (col < 2) {
//                return false;
//            } else {
//                return true;
//            }
//        } /* * Don't need to implement this method unless your table's * data can change. */
//        }


    }


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
        //frame.pack();
        frame.setFont(tableFont);
        frame.setSize(800,600);
        frame.setVisible(true);
    }
}

