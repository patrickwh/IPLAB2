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
        this.setSize(new Dimension(600,720));
        // The top layout
        title.setText("Dinner menu preparation");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setPreferredSize(new Dimension(600,100));

        JPanel topP = new JPanel();
        topP.setLayout(new BorderLayout());
        topP.add(title,BorderLayout.NORTH);
//        topP.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
//                Constants.dishNameInformationPanelHeight));
        topP.setSize(new Dimension(600,300));


        starter.setText("Starter:"+dishStarter.getName());
        starter.setHorizontalAlignment(JLabel.CENTER);
        starter.setSize(new Dimension(600,60));
//        starter.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
//                Constants.dishNameInformationPanelHeight));

        topP.add(starter,BorderLayout.CENTER);
        JPanel staPanel = new JPanel();
        starterContent.setText(dishStarter.getDescription());
//        starterContent.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
//                Constants.dishNameInformationPanelHeight));
        //title.setPreferredSize(new Dimension(20,20));
        starterContent.setSize(new Dimension(600,140));
        starterContent.setLineWrap(true);
        staPanel.add(starterContent,BorderLayout.CENTER);
        topP.add(staPanel,BorderLayout.SOUTH);
        //topP.add(starterContent,BorderLayout.SOUTH);

        //topP.setPreferredSize(new Dimension(100,100));

        // The middle layout
        JPanel midP = new JPanel();
        midP.setSize(new Dimension(600,210));
        main.setSize(new Dimension(600,60));
        mainContent.setSize(new Dimension(600,150));
        midP.setLayout(new BorderLayout());
        main.setText("Main:"+dishMain.getName());
        main.setHorizontalAlignment(JLabel.CENTER);
//        main.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
//                Constants.dishNameInformationPanelHeight));
        mainContent.setText(dishMain.getDescription());
        mainContent.setLineWrap(true);
        midP.add(main,BorderLayout.NORTH);
        JPanel mainPanel = new JPanel();
        mainPanel.add(mainContent,BorderLayout.CENTER);
        midP.add(mainPanel,BorderLayout.SOUTH);
        //midP.add(mainContent,BorderLayout.SOUTH);
//        mainContent.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
//                Constants.dishNameInformationPanelHeight));
//        midP.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
//                Constants.dishNameInformationPanelHeight));

        // The bottom layout
        JPanel bottP = new JPanel();
        bottP.setSize(new Dimension(600,210));
        desert.setSize(new Dimension(600,60));
        desertContent.setSize(new Dimension(600,150));
        bottP.setLayout(new BorderLayout());
//        bottP.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
//                Constants.dishNameInformationPanelHeight));
        desert.setText("Desert:"+dishDesert.getName());
        desert.setHorizontalAlignment(JLabel.CENTER);
//        desert.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
//                Constants.dishNameInformationPanelHeight));
        bottP.add(desert,BorderLayout.NORTH);
        desertContent.setText(dishDesert.getDescription());
        desertContent.setLineWrap(true);
        JPanel bottPanel = new JPanel();
        bottPanel.add(desertContent,BorderLayout.CENTER);
        bottP.add(bottPanel,BorderLayout.SOUTH);
        //bottP.add(desertContent,BorderLayout.SOUTH);

//        desertContent.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
//                Constants.dishNameInformationPanelHeight));
        title.setFont(font);
        starter.setFont(font);
        main.setFont(font);
        desert.setFont(font);

        add(topP,BorderLayout.NORTH);

        add(midP,BorderLayout.CENTER);

        add(bottP,BorderLayout.SOUTH);


    }


    public void  creatAndShowGUI(){
        //creat and setup the window
        JFrame frame = new JFrame("Dinner planner-Preparation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        this.setOpaque(true);
        frame.setContentPane(this);
//        this.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
//                Constants.dishNameInformationPanelHeight));
//        frame.setBounds(20,20,Constants.dishNameDisplayWindowWidth,
//                Constants.dishNameInformationPanelHeight);
        frame.setSize(600,480);
        this.setSize(600,480);
        //frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        Dish temp1 = new Dish("Apple",1,"xxx","Stockholm, the capital of Sweden, encompasses 14 islands of the vast Stockholm archipelago on the Baltic Sea. The cobblestone streets and ochre-colored buildings of medieval Gamla Stan, the old town, are home to a 13th-century cathedral, the royal palace of Kungliga Slottet and its underground armory, cafes and restaurants. Ferries and sightseeing boats shuttle passengers between islands, beneath more than 50 bridges.");
        Dish temp2 = new Dish("Banana",2,"@@@","Madrid, Spain's central capital, is a city of elegant boulevards and expansive, manicured parks such as the Buen Retiro. It’s renowned for its rich repositories of European art, including the Prado Museum’s works by Goya, Velázquez and other Spanish masters. The heart of old Hapsburg Madrid is the portico-lined Plaza Mayor, and nearby is the baroque Royal Palace and Armory, displaying historic weaponry.");
        Dish temp3 = new Dish("Orange",3,"$$$","Tokyo, Japan’s bustling capital, mixes the ultramodern and the traditional, from neon-lit skyscrapers and anime shops to cherry trees and temples. The opulent Meiji Shinto Shrine is known for its towering gate and surrounding forests. The Imperial Palace sits amid sprawling public gardens. The city is famed for its vibrant food scene, and its Shibuya and Harajuku districts are the heart of its trendy teen fashion scene.");

        PreparationPanel newPrepPane = new PreparationPanel(temp1,temp2,temp3);
        //PreparationPanel pp = new PreparationPanel();
        newPrepPane.creatAndShowGUI();
    }
}
