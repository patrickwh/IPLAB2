package se.kth.csc.iprog.dinnerplanner.swing.view;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.net.NoRouteToHostException;

/**
 * Created by Jiansun on 16/2/10.
 */
public class PreparationPanel extends JPanel{


    public PreparationPanel(){
        //super(new BorderLayout());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        Label title = new Label("Dinner menu preparation",Label.CENTER);
        Label starter = new Label("Starter:",Label.CENTER);
        Label main = new Label("Main:",Label.CENTER);
        Label desert = new Label("Desert:",Label.CENTER);
        TextArea starterContent = new TextArea("Just some text how to make it");
        TextArea mainContent = new TextArea("***********");
        TextArea desertContent = new TextArea("############");

        add(title);
        add(starter);
        add(starterContent);
        add(main);
        add(mainContent);
        add(desert);
        add(desertContent);

    }




    private static  void  creatAndShowGUI(){
        //creat and setup the window
        JFrame fram = new JFrame("Dinner planner-Preparation");
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PreparationPanel newPrepPane = new PreparationPanel();
        newPrepPane.setOpaque(true);
        //newPrepPane.setLayout(new BoxLayout(newPrepPane,BoxLayout.Y_AXIS));
        fram.setContentPane(newPrepPane);
        fram.setSize(800,600);
        fram.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                creatAndShowGUI();
            }
        });
    }
}
