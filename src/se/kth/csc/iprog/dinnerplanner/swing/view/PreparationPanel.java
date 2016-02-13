package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import se.kth.csc.iprog.dinnerplanner.model.ChangeMessage;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

/**
 * Created by Jiansun on 16/2/10.
 */
public class PreparationPanel extends JPanel implements Observer
{
	
	private static final long serialVersionUID = 1L;
    JLabel title=new JLabel();
    JLabel starter = new JLabel();
    JLabel main = new JLabel();
    JLabel desert = new JLabel();
    JTextArea starterContent = new JTextArea();
    JTextArea mainContent = new JTextArea();
    JTextArea desertContent = new JTextArea();
    JPanel topP = new JPanel();
    JPanel midP = new JPanel();
    JPanel bottP = new JPanel();
    JScrollPane starterScroll;
    JScrollPane mainScroll;
    JScrollPane desertScroll;
    Dish dishStarter;
    Dish dishMain;
	Dish dishDesert;

    public PreparationPanel(Dish dishStarter,Dish dishMain,Dish dishDesert)
    {
        super(new BorderLayout());
        this.setPreferredSize(new Dimension(Constants.preparationPanelWidth,
        		Constants.preparationPanelHeight));
        this.dishStarter=dishStarter;
        this.dishMain=dishMain;
        this.dishDesert=dishDesert;
        
    	Font titleFont=new Font("Forte",Font.BOLD,32);
    	Font nameFont=new Font("Segoe Print",Font.BOLD,22);
    	Font descriptionFont=new Font("Segoe Print",Font.PLAIN,18);
    	
        // The top layout
        title.setText("Dinner menu preparation");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setPreferredSize(new Dimension(Constants.preparationPanelWidth,
        		Constants.preparationPanelTitleHeight));
        title.setFont(titleFont);

        starter.setText("Starter: "+dishStarter.getName());
        starter.setHorizontalAlignment(JLabel.CENTER);
        starter.setVerticalAlignment(JLabel.CENTER); 
        starter.setFont(nameFont);
        starter.setPreferredSize(new Dimension(Constants.preparationPanelWidth,
        		Constants.preparationPanelNameHeight));
        
        starterContent.setText(dishStarter.getDescription());
        starterContent.setLineWrap(true);
        starterContent.setEditable(false);
        starterContent.setOpaque(false);
        starterContent.setFont(descriptionFont);
        starterScroll=new JScrollPane(starterContent,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        starterScroll.setPreferredSize(new Dimension(Constants.preparationPanelWidth,
        		Constants.preparationPanelDescriptionHeight));
        starterScroll.setBorder(BorderFactory.createEmptyBorder(0, Constants.borderMargin,
        		0, Constants.borderMargin));
  
        topP.setPreferredSize(new Dimension(Constants.preparationPanelWidth,
        		Constants.preparationPanelTopHeight));
        topP.setLayout(new BorderLayout());
        topP.add(title,BorderLayout.NORTH);
        topP.add(starter,BorderLayout.CENTER);
        topP.add(starterScroll,BorderLayout.SOUTH);

        // The middle layout
        main.setPreferredSize(new Dimension(Constants.preparationPanelWidth,
        		Constants.preparationPanelNameHeight));
        main.setText("Main: "+dishMain.getName());
        main.setHorizontalAlignment(JLabel.CENTER);
        main.setVerticalAlignment(JLabel.CENTER);
        main.setFont(nameFont);
        
        mainContent.setText(dishMain.getDescription());
        mainContent.setLineWrap(true);
        mainContent.setEditable(false);
        mainContent.setOpaque(false);
        mainContent.setFont(descriptionFont);
        mainScroll=new JScrollPane(mainContent,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScroll.setPreferredSize(new Dimension(Constants.preparationPanelWidth,
        		Constants.preparationPanelDescriptionHeight)); 
        mainScroll.setBorder(BorderFactory.createEmptyBorder(0, Constants.borderMargin,
        		0, Constants.borderMargin));
         
        midP.setPreferredSize(new Dimension(Constants.preparationPanelWidth,
        		Constants.preparationPanelCenterHeight)); 
        midP.setLayout(new BorderLayout());
        midP.add(main,BorderLayout.NORTH);
        midP.add(mainScroll,BorderLayout.CENTER);

        desert.setText("Desert: "+dishDesert.getName());
        desert.setHorizontalAlignment(JLabel.CENTER);
        desert.setVerticalAlignment(JLabel.CENTER);
        desert.setFont(nameFont);
        
        desertContent.setPreferredSize(new Dimension(Constants.preparationPanelWidth,
        		Constants.preparationPanelDescriptionHeight));
        desertContent.setText(dishDesert.getDescription());
        desertContent.setLineWrap(true);
        desertContent.setEditable(false);
        desertContent.setOpaque(false);
        desertContent.setFont(descriptionFont);
        desertScroll=new JScrollPane(desertContent,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        desertScroll.setPreferredSize(new Dimension(Constants.preparationPanelWidth,
        		Constants.preparationPanelDescriptionHeight));
        desertScroll.setBorder(BorderFactory.createEmptyBorder(0, Constants.borderMargin,
        		0, Constants.borderMargin));
        
        bottP.setPreferredSize(new Dimension(Constants.preparationPanelWidth,
        		Constants.preparetionPanelBottomHeight));
        bottP.setLayout(new BorderLayout());
        bottP.add(desert,BorderLayout.NORTH);
        bottP.add(desertScroll,BorderLayout.SOUTH);

        starterContent.setEditable(false);
        mainContent.setEditable(false);
        desertContent.setEditable(false);

        add(topP,BorderLayout.NORTH);
        add(midP,BorderLayout.CENTER);
        add(bottP,BorderLayout.SOUTH);
    }

    public void  creatAndShowGUI(){
        //creat and setup the window
        JFrame frame = new JFrame("Dinner planner-Preparation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(this);
        frame.setBounds((Constants.widthDf-Constants.preparationPanelWidth)/2,
        		(Constants.heightDf-Constants.preparationPanelHeight)/2,Constants.preparationPanelWidth,
                Constants.preparationPanelHeight);
        frame.pack();
        frame.setVisible(true);
    }

    public void setDishes(ArrayList<Dish> list)
    {
    	int num=list.size();
    	for(int i=0;i<num;i++)
    	{
    		if(list.get(i).getType()==Dish.STARTER)
    		{
    			this.dishStarter=list.get(i);
    		}
    		else if(list.get(i).getType()==Dish.MAIN)
    		{
    			this.dishMain=list.get(i);
    		}
    		else
    		{
    			this.dishDesert=list.get(i);
    		}
    	}
    	starter.setText("Starter: "+dishStarter.getName());
    	main.setText("Main: "+dishMain.getName());
    	desert.setText("Desert: "+dishDesert.getName());
    	starterContent.setText(dishStarter.getDescription());
        mainContent.setText(dishMain.getDescription());
    	desertContent.setText(dishDesert.getDescription());
    }

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable obs, Object obj) {
		ChangeMessage cm=(ChangeMessage) obj;
		if(cm.getType()==ChangeMessage.MenuCahngedForPreparation)
		{
			this.setDishes((ArrayList<Dish>)cm.getData());
		}
	}
}
