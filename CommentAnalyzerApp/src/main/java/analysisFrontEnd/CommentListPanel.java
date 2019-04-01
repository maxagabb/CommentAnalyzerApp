package analysisFrontEnd;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CommentListPanel extends JPanel{
	public CommentListPanel(JFrame frame) {
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 0, 20, 0);
		gbc.ipady = 10;
		this.frame = frame;
	}

	public void setPanel(){
		this.setLayout(new GridBagLayout());
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		for(CommentPanel panel: panels) {
			panel.setPanel();
			panel.setAlignmentX(LEFT_ALIGNMENT);
			this.add(panel, gbc);
			gbc.gridy++;
		}
	}
	
	public void addPanel(CommentPanel commentPanel) {
		panels.add(commentPanel);
		allPanels.add(commentPanel);
	}
	
	public void parseComments(String text) {
		panels.clear();
		for(CommentPanel panel : allPanels) {
		    panels.add(panel.clone());
		}
		ArrayList<CommentPanel> panelsToRemove = new ArrayList<CommentPanel>();
		for(CommentPanel panel: panels) {
			if (panel.contains(text) == false) {
				panelsToRemove.add(panel);
			}
		}
		panels.removeAll(panelsToRemove);
		this.removeAll();
		this.revalidate();
		this.repaint();
		setPanel();
	}
	private GridBagConstraints gbc;
	private JFrame frame;
	private ArrayList<CommentPanel> panels = new ArrayList<CommentPanel>();
	ArrayList<CommentPanel> allPanels= new ArrayList<CommentPanel>();

}
