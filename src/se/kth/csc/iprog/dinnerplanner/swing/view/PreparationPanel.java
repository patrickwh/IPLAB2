package se.kth.csc.iprog.dinnerplanner.swing.view;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.awt.*;

import javax.swing.*;

/**
 * Created by Jiansun on 16/2/10.
 */
public class PreparationPanel extends JPanel{
	private static final long serialVersionUID = 1L;
    String dishStarter;
    String dishMain;
    String dishDesert;
    JLabel title;
    Font font = new Font("Courier", Font.BOLD,30);
    JLabel starter;
    JLabel main;
    JLabel desert;
    JTextArea starterContent;
    JTextArea mainContent;
    JTextArea desertContent;




    public PreparationPanel(Dish dishStarter,Dish dishMain, Dish dishDesert ){
        //super(new BorderLayout());
        title.setText("Dinner menu preparation");
        title.setHorizontalAlignment(JLabel.CENTER);
        starter.setText("Starter:"+dishStarter.getName());
        starter.setHorizontalAlignment(JLabel.CENTER);
        main.setText("Main:"+dishMain.getName());
        main.setHorizontalAlignment(JLabel.CENTER);
        desert.setText("Desert:"+dishDesert.getName());
        desert.setHorizontalAlignment(JLabel.CENTER);
        starterContent.setText(dishStarter.getDescription());
        mainContent.setText(dishMain.getDescription());
        desertContent.setText(dishDesert.getDescription());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        title.setFont(font);
        starter.setFont(font);
        main.setFont(font);
        desert.setFont(font);
        //setPreferredSize(new Dimension(800,600));
        add(Box.createRigidArea(new Dimension(20,20)));
        add(title);
        add(Box.createRigidArea(new Dimension(20,20)));
        add(starter);
        add(Box.createRigidArea(new Dimension(20,20)));
        add(starterContent);
        add(Box.createRigidArea(new Dimension(20,20)));
        add(main);
        add(Box.createRigidArea(new Dimension(20,20)));
        add(mainContent);
        add(Box.createRigidArea(new Dimension(20,20)));
        add(desert);
        add(Box.createRigidArea(new Dimension(20,20)));
        add(desertContent);

    }

//    public PreparationPanel() {
//
//    }

    public void  creatAndShowGUI(){
        //creat and setup the window
        JFrame fram = new JFrame("Dinner planner-Preparation");
        fram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        this.setOpaque(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        fram.setContentPane(this);
        //fram.setSize(800,600);
        fram.pack();
        fram.setVisible(true);
    }

    public static void main(String[] args) {

        Dish temp1 = new Dish("Apple",1,"xxx","Stockholm");
        Dish temp2 = new Dish("Banana",2,"@@@","Madrid");
        Dish temp3 = new Dish("Orange",3,"$$$","Tokyo");

        PreparationPanel newPrepPane = new PreparationPanel(temp1,temp2,temp3);
        //PreparationPanel pp = new PreparationPanel();
        newPrepPane.creatAndShowGUI();
    }
}
