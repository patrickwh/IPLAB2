package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Label;
import java.awt.TextArea;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by Jiansun on 16/2/10.
 */
public class PreparationPanel extends JPanel{
	private static final long serialVersionUID = 1L;

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

    public void  creatAndShowGUI(){
        //creat and setup the window
        JFrame fram = new JFrame("Dinner planner-Preparation");
        fram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PreparationPanel newPrepPane = new PreparationPanel();
        newPrepPane.setOpaque(true);
        newPrepPane.setLayout(new BoxLayout(newPrepPane,BoxLayout.Y_AXIS));
        fram.setContentPane(newPrepPane);
        fram.setSize(800,600);
        fram.setVisible(true);
    }

    public static void main(String[] args) {

        PreparationPanel pp = new PreparationPanel();
        pp.creatAndShowGUI();
    }
}
