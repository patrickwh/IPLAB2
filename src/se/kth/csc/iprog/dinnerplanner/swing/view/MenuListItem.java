package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import se.kth.csc.iprog.dinnerplanner.model.ChangeMessage;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class MenuListItem extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;
	
	Dish dish=new Dish();
	int guestNum=0;
	JLabel nameLabel=new JLabel();
	JLabel costLabel=new JLabel();
	JPanel labelPanel=new JPanel();
	JLabel imageLabel=new JLabel();
	JLabel removeButton= new JLabel();
	DinnerModel model;

	public MenuListItem(Dish d,int num,DinnerModel m)
	{
		this.guestNum=num;
		this.dish=d;
		this.model=m;
		model.addObserver(this);;
		
		this.imageLabel.setPreferredSize(new Dimension(Constants.menuEntryPicWidth,
				Constants.menuEntryPicWidth));
		Image img=new ImageIcon(Constants.homeDir+Constants.pictureDir+dish.getImage()).getImage();
		Image newImg=img.getScaledInstance(Constants.menuEntryPicWidth,
				Constants.menuEntryPicWidth, Image.SCALE_SMOOTH);
		this.imageLabel.setIcon(new ImageIcon(newImg));
		this.imageLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		this.nameLabel.setText("   "+dish.getypeStr()+" : "+dish.getName());
		this.nameLabel.setPreferredSize(new Dimension(Constants.menuEntryLabelWidth,
			Constants.menuEntryLabelHeight));
		this.costLabel.setText("   Cost : $ "+dish.getCost());
		this.costLabel.setPreferredSize(new Dimension(Constants.menuEntryLabelWidth,
				Constants.menuEntryLabelHeight));
		this.labelPanel.setLayout(new BorderLayout());
		this.labelPanel.add(nameLabel,BorderLayout.NORTH);
		this.labelPanel.add(costLabel,BorderLayout.SOUTH);
		this.labelPanel.setPreferredSize(new Dimension(Constants.menuEntryLabelWidth,
				Constants.menuEntryHeight));
		
		img=new ImageIcon(Constants.homeDir+Constants.pictureDir+"remove.jpg").getImage();
		newImg=img.getScaledInstance(Constants.menuEntryPicWidth,
				Constants.menuEntryPicWidth, Image.SCALE_SMOOTH);
		this.removeButton.setIcon(new ImageIcon(newImg));
		this.removeButton.setPreferredSize(new Dimension(Constants.menuEntryPicWidth,
				Constants.menuEntryPicWidth));
		//this.removeButton.setBackground(Color.RED);	
		this.removeButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
				MenuListItem.this.model.removeDishFromMenu(MenuListItem.this.dish);
			}
			@Override
			public void mouseEntered(MouseEvent e)
			{
				MenuListItem.this.removeButton.setBorder(
						BorderFactory.createRaisedBevelBorder());
			}
			public void mouseExited(MouseEvent e)
			{
				MenuListItem.this.removeButton.setBorder(null);
			}
		});
		
		this.setPreferredSize(new Dimension(Constants.menuEntryWidth,
			Constants.menuEntryHeight));
		this.setLayout(new BorderLayout());
		this.add(imageLabel, BorderLayout.WEST);
		this.add(this.labelPanel,BorderLayout.CENTER);
		this.add(removeButton, BorderLayout.EAST);
		this.setBorder(BorderFactory.createEtchedBorder());
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
				DishNameDisplayWindow dndw=new DishNameDisplayWindow(MenuListItem.this.dish,
						MenuListItem.this.guestNum,model);
				dndw.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e)
			{
				MenuListItem.this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,3));
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				MenuListItem.this.setBorder(BorderFactory.createEtchedBorder());
			}
		});
	}

	@Override
	public void update(Observable obs, Object obj) {
		ChangeMessage cm=(ChangeMessage) obj;
		if(cm.getType()==ChangeMessage.GuestNumChanged)
		{
			this.guestNum=(Integer) cm.getData();
		}	
	}

}
