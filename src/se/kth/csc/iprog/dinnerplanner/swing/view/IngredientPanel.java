package se.kth.csc.iprog.dinnerplanner.swing.view;

import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jiansun on 16/2/9.
 */
public class IngredientPanel extends JPanel {

    public IngredientPanel() {

        super(new BorderLayout());


        ArrayList<Ingredient> tempList = new ArrayList<Ingredient>();

        Ingredient temp1 = new Ingredient("Carrot", 5, "", 3);
        Ingredient temp2 = new Ingredient("Duck breast", 200, "g", 10);

        tempList.add(temp1);
        tempList.add(temp2);

        // Table head
        String[] columnNames = {"Ingredient", "Quality", "Cost"};

        // Table content
        Object[][] data = {
                {temp1.getName(), temp1.getQuantity() + temp1.getUnit(), temp1.getPrice() + "$"},
                {temp2.getName(), temp2.getQuantity() + temp2.getUnit(), temp2.getPrice() + "$"},
        };

        final JTable table = new JTable(data, columnNames);
        table.setBackground(Color.orange);

        JScrollPane scrollPanepanel = new JScrollPane(table);

        add(scrollPanepanel);


    }

    private static void creatAndShowGUI() {
        //creat and set up the window
        JFrame frame = new JFrame("Dinner Planner - Ingredients");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creat and set up the content pane

        IngredientPanel newContentPane = new IngredientPanel();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        //display

        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                creatAndShowGUI();
            }
        });
    }
}
