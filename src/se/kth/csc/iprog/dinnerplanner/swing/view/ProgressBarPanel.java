package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import se.kth.csc.iprog.dinnerplanner.model.ChangeMessage;

public class ProgressBarPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	JProgressBar bar=new JProgressBar();
	JTextField text=new JTextField();
	
	public ProgressBarPanel()
	{
		Font font=new Font("Britannic",Font.ITALIC,16);
		int margin=10;
		
		this.setPreferredSize(new Dimension(0,0));
		this.setLayout(new BorderLayout());
		this.add(text,BorderLayout.NORTH);
		this.add(bar, BorderLayout.CENTER);
		this.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
		
		this.bar.setPreferredSize(new Dimension(Constants.progressBarWidth,
				Constants.progressBarHeight));
		this.bar.setMinimum(Constants.progressBarMin);
		this.bar.setMaximum(Constants.progressBarMax);
		this.bar.setValue(Constants.progressBarMin);
		this.bar.setForeground(Color.GREEN);
		
		this.text.setText("   Done");
		this.text.setEditable(false);
		this.text.setFont(font);
		this.text.setPreferredSize(new Dimension(Constants.progressIndicatingTextWidth,
				Constants.progressIndicatingTextHeight));
	}

	@Override
	public void update(Observable obs, Object obj) {
		ChangeMessage cm=(ChangeMessage) obj;
		if(cm.getType()==ChangeMessage.currentLoadedNumChanged)
		{
			@SuppressWarnings("unchecked")
			ArrayList<Integer> num=(ArrayList<Integer>) cm.getData();
			if(num.get(1)==num.get(0))
			{
				text.setText("   Loading Data Is Done !!");
				bar.setValue(Constants.progressBarMax);
			}
			else
			{
				text.setText("   Data Loading: "+num.get(1)+" of "+num.get(0));
				bar.setValue((int) (Constants.progressBarMax*(num.get(1)/(double)num.get(0))));
			}
			this.validate();
			this.repaint();
		}
		else if(cm.getType()==ChangeMessage.loadingStateChanged)
		{
			boolean state=(boolean) cm.getData();
			if(state)
			{
				this.setPreferredSize(new Dimension(Constants.progressBarPanelWidth,
						Constants.progressBarPanelHeight));
			}
		}
	}
}
