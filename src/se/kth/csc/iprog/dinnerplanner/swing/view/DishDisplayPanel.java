package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class DishDisplayPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	Dish dish=new Dish("French toast",Dish.STARTER,"toast","In a large mixing bowl, "
			+ "beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. "
			+ "Soak bread slices in the egg mixture until saturated. Heat a lightly oiled "
			+ "griddle or frying pan over medium high heat. Brown slices on both sides, "
			+ "sprinkle with cinnamon and serve hot.");
	ImageIcon dishIcon;
	JLabel dishImageLabel;
	JLabel dishNameLabel=new JLabel();
	int ID;
	
	public DishDisplayPanel(int id)
	{
		this.ID=id;
		this.dishIcon=new ImageIcon(Constants.homeDir+Constants.pictureDir+dish.getImage()
				+Constants.pictureSuffix);
		this.dishImageLabel=new JLabel();		
		this.dishNameLabel.setText(dish.getName());
		
		this.setPreferredSize(new Dimension(Constants.dishDisplayWidth+Constants.interDishDisplayMargin,
				Constants.dishDisplayHeight+Constants.interDishDisplayMargin));
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.dishImageLabel.setPreferredSize(new Dimension(Constants.dishDisplayWidth,
				Constants.dishDisplayWidth));
		this.dishNameLabel.setPreferredSize(new Dimension(Constants.dishDisplayWidth,
			    Constants.dishNameDisplayLabelHeight));
		this.dishNameLabel.setHorizontalAlignment(JLabel.CENTER);
		this.dishNameLabel.setVerticalAlignment(JLabel.CENTER);
		
		Font nameFont=new Font("Britannic", Font.BOLD,20);
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
			public void mouseClicked(MouseEvent e)
			{
				System.out.println(" this is clicked "+DishDisplayPanel.this.ID);
			}
			@Override
			public void mouseEntered(MouseEvent e)
			{
				DishDisplayPanel.this.setBorder(BorderFactory.createRaisedBevelBorder());
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				DishDisplayPanel.this.setBorder(BorderFactory.createLoweredBevelBorder());
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				// when drag dish into the menu
			}
		});
	}

}
