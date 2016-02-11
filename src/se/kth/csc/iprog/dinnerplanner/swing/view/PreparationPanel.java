package se.kth.csc.iprog.dinnerplanner.swing.view;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.awt.*;

import javax.swing.*;

/**
 * Created by Jiansun on 16/2/10.
 */
public class PreparationPanel extends JPanel{
	private static final long serialVersionUID = 1L;
//    String dishStarter;
//    String dishMain;
//    String dishDesert;
    JLabel title=new JLabel();
    Font font = new Font("Courier", Font.BOLD,30);
    JLabel starter = new JLabel();
    JLabel main = new JLabel();
    JLabel desert = new JLabel();
    JTextArea starterContent = new JTextArea();
    JTextArea mainContent = new JTextArea();
    JTextArea desertContent = new JTextArea();




    public PreparationPanel(Dish dishStarter,Dish dishMain, Dish dishDesert ){
        super(new BorderLayout());

        // The top layout
        title.setText("Dinner menu preparation");
        title.setHorizontalAlignment(JLabel.CENTER);

        JPanel topP = new JPanel();
        topP.setLayout(new BorderLayout());

        topP.add(title,BorderLayout.NORTH);
        starter.setText("Starter:"+dishStarter.getName());
        starter.setHorizontalAlignment(JLabel.CENTER);

        topP.add(starter,BorderLayout.CENTER);
        starterContent.setText(dishStarter.getDescription());
        starterContent.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
                Constants.dishNameInformationPanelHeight));
        //title.setPreferredSize(new Dimension(20,20));
        topP.add(starterContent,BorderLayout.SOUTH);

        //topP.setPreferredSize(new Dimension(100,100));

        // The middle layout
        JPanel midP = new JPanel();
        midP.setLayout(new BorderLayout());
        main.setText("Main:"+dishMain.getName());
        main.setHorizontalAlignment(JLabel.CENTER);
        mainContent.setText(dishMain.getDescription());
        midP.add(main,BorderLayout.NORTH);
        midP.add(mainContent,BorderLayout.SOUTH);
        mainContent.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
                Constants.dishNameInformationPanelHeight));
        midP.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
                Constants.dishNameInformationPanelHeight));

        // The bottom layout
        JPanel bottP = new JPanel();
        bottP.setLayout(new BorderLayout());
        desert.setText("Desert:"+dishDesert.getName());
        desert.setHorizontalAlignment(JLabel.CENTER);
        bottP.add(desert,BorderLayout.NORTH);
        desertContent.setText(dishDesert.getDescription());
        bottP.add(desertContent,BorderLayout.SOUTH);

        desertContent.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
                Constants.dishNameInformationPanelHeight));
        title.setFont(font);
        starter.setFont(font);
        main.setFont(font);
        desert.setFont(font);

        add(topP,BorderLayout.NORTH);

        add(midP,BorderLayout.CENTER);

        add(bottP,BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
                Constants.dishNameInformationPanelHeight));
        setPreferredSize(new Dimension(800,600));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(20,20)));
        add(Box.createRigidArea(new Dimension(20,20)));
        add(Box.createRigidArea(new Dimension(20,20)));
        bottP.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
         Constants.dishNameInformationPanelHeight));
        title.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
        Constants.dishNameInformationPanelHeight));
        topP.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
        Constants.dishNameInformationPanelHeight));
        starter.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
        Constants.dishNameInformationPanelHeight));

    }

//    public PreparationPanel() {
//
//    }

    public void  creatAndShowGUI(){
        //creat and setup the window
        JFrame frame = new JFrame("Dinner planner-Preparation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        this.setOpaque(true);
        //this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        frame.setContentPane(this);
//        this.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
//                Constants.dishNameInformationPanelHeight));
        frame.setSize(800,750);
        //frame.pack();
        frame.setVisible(true);
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
