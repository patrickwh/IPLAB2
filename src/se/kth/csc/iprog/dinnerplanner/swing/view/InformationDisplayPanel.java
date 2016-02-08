package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class InformationDisplayPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	JLabel guestNumLabel=new JLabel("Number of people");
	JPanel guestNumPanel=new JPanel();
	JSpinner guestNumSpinner=new JSpinner();
	JLabel costLabel=new JLabel("Total cost:");
	JLabel numberLabel=new JLabel("$0.00");
	JPanel costPanel=new JPanel();
	JPanel topPanel=new JPanel();

	public InformationDisplayPanel()
	{
		this.setPreferredSize(new Dimension(Constants.informationPanelWidth,Constants.height));
		Font smallTextFont=new Font ("Bodoni MT",Font.BOLD,18);
		this.guestNumLabel.setFont(smallTextFont);
		this.guestNumPanel.setLayout(new BorderLayout());
		this.guestNumLabel.setPreferredSize(new Dimension(Constants.guestNumLabelWidth,
				Constants.guestNumLabelHeight));
		this.guestNumPanel.setPreferredSize(new Dimension(Constants.informationPanelWidth,
				Constants.guestNumLabelHeight));
		this.guestNumPanel.add(this.guestNumLabel,BorderLayout.WEST);
		this.guestNumPanel.add(this.guestNumSpinner,BorderLayout.CENTER);
		
		this.costLabel.setFont(smallTextFont);
		this.costLabel.setPreferredSize(new Dimension(Constants.guestNumLabelWidth,
				Constants.guestNumLabelHeight));
		this.costPanel.setPreferredSize(new Dimension(Constants.informationPanelWidth,
				Constants.guestNumLabelHeight));
		this.costPanel.setLayout(new BorderLayout());
		this.costPanel.add(costLabel, BorderLayout.WEST);
		this.costPanel.add(numberLabel, BorderLayout.CENTER);
		
		this.topPanel.setPreferredSize(new Dimension(Constants.informationPanelWidth,
				Constants.informationPanelHeight));
		this.topPanel.add(this.guestNumPanel,BorderLayout.NORTH);
		this.topPanel.add(this.costPanel,BorderLayout.SOUTH);
		
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		//topPanel.setLayout(new GridLayout(2,2));
		this.topPanel.setLayout(new BorderLayout());
		this.guestNumSpinner.setPreferredSize(new Dimension(Constants.guestNumSpinnerWidth,
				Constants.guestNumLabelHeight));
		this.guestNumSpinner.setFont(smallTextFont);
		this.guestNumSpinner.setPreferredSize(new Dimension(Constants.guestNumSpinnerWidth,
				Constants.guestNumSpinnerHeight));
//		this.topPanel.add(guestNumLabel);
//		this.topPanel.add(guestNumSpinner);
//		this.topPanel.add(costLabel);
//		this.topPanel.add(numberLabel);
		this.topPanel.add(guestNumPanel, BorderLayout.NORTH);
		this.topPanel.add(costPanel, BorderLayout.SOUTH);
		this.numberLabel.setFont(smallTextFont);
		this.numberLabel.setPreferredSize(new Dimension(Constants.guestNumSpinnerWidth,
				Constants.guestNumLabelHeight));
		this.topPanel.setBorder(BorderFactory.createEmptyBorder(Constants.borderMargin, 
				Constants.borderMargin, Constants.borderMargin, Constants.borderMargin));
	}
}
