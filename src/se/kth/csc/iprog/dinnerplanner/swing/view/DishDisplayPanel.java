package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class DishDisplayPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	Dish dish=new Dish();
	ImageIcon dishIcon;
	JLabel dishImageLabel;
	JLabel dishNameLabel=new JLabel();
	int ID;
	
	public DishDisplayPanel(Dish d,int id)
	{
		
		this.setPreferredSize(new Dimension(Constants.dishDisplayWidth+Constants.interDishDisplayMargin,
				Constants.dishDisplayHeight+Constants.interDishDisplayMargin));
		//if(d==null) return;
		
		this.ID=id;
		this.dish=d;
		this.dishIcon=new ImageIcon(Constants.homeDir+Constants.pictureDir+dish.getImage());
		this.dishImageLabel=new JLabel();		
		this.dishNameLabel.setText(dish.getName());
		
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.dishImageLabel.setPreferredSize(new Dimension(Constants.dishDisplayWidth,
				Constants.dishDisplayWidth));
		this.dishImageLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		this.dishNameLabel.setPreferredSize(new Dimension(Constants.dishDisplayWidth,
			    Constants.dishNameDisplayLabelHeight));
		this.dishNameLabel.setHorizontalAlignment(JLabel.CENTER);
		this.dishNameLabel.setVerticalAlignment(JLabel.CENTER);		
		
		Font nameFont=new Font("Segoe Print", Font.BOLD,20);
		this.dishNameLabel.setFont(nameFont);
		
		Image img=this.dishIcon.getImage();
		Image newImg=img.getScaledInstance
				(Constants.dishDisplayWidth,Constants.dishDisplayWidth,Image.SCALE_SMOOTH);
		ImageIcon newIcon=new ImageIcon(newImg);
		this.dishImageLabel.setIcon(newIcon);
		//this.dishImageLabel.setIcon(this.dishIcon);
		this.setLayout(new BorderLayout());
		this.add(this.dishImageLabel,BorderLayout.NORTH);
		this.add(this.dishNameLabel,BorderLayout.CENTER);
		
		this.setFocusable(true);
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e)
			{
				DishDisplayPanel.this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,5));
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				DishDisplayPanel.this.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		});
		
		DragSource dragSource=DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer(this, 
				DnDConstants.ACTION_COPY, new DishDragGestureListener());
	}
	
	public Dish getDish()
	{
		return this.dish;
	}

}
