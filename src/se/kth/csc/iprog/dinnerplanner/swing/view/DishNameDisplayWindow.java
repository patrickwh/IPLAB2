package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class DishNameDisplayWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	Dish dish;
	int guestNum=0;
	JPanel content=new JPanel();
	JPanel nameInformationPanel=new JPanel();
	JLabel image=new JLabel();
	JPanel imagePanel=new JPanel();
	JPanel namePanel=new JPanel();
	JLabel name=new JLabel();
	
	public DishNameDisplayWindow(Dish d,int num)
	{
		this.dish=d;
		this.guestNum=num;
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds((Constants.widthDf-Constants.dishNameDisplayWindowWidth)/2,
				(Constants.heightDf-Constants.dishNameDisplayWindowHeight)/2, 
				Constants.dishNameDisplayWindowWidth, Constants.dishNameDisplayWindowHeight);
		this.setContentPane(this.content);
		
		Image img=new ImageIcon(Constants.homeDir+Constants.pictureDir+this.dish.getImage()).getImage();
		Image newImg=img.getScaledInstance(Constants.dishNameImageWidth, Constants.dishNameImageWidth, 
				Image.SCALE_SMOOTH);
		this.image.setIcon(new ImageIcon(newImg));
		this.imagePanel.setPreferredSize(new Dimension(Constants.dishNameInformationPanelHeight,
				Constants.dishNameInformationPanelHeight));
		this.imagePanel.setLayout(new BorderLayout());
		this.imagePanel.add(image,BorderLayout.CENTER);
		this.imagePanel.setBorder(BorderFactory.createEmptyBorder(Constants.dishNameBorder, 
				Constants.dishNameBorder, Constants.dishNameBorder, Constants.dishNameBorder));
		
		this.namePanel.setPreferredSize(new Dimension(Constants.dishNameNamePanelWidth,
				Constants.dishNameInformationPanelHeight));
	}

}
