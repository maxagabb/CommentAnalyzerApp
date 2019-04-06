package commentsFrontEnd;

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

import business.Comment;
import business.Content;
import business.ContentListPanel;
import business.ContentPanel;
import byVideoFrontEnd.TaskBar;

public class CommentListPanel extends ContentListPanel{
	public CommentListPanel(JFrame frame) {
		super(frame);
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
	
	public void addPanel(Content content) {
		panels.add(new CommentPanel((Comment) content));
		allPanels.add(new CommentPanel((Comment) content));
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
	protected void makeSearchByPage(JFrame frame2, TaskBar taskBar, ContentPanel panel) {}
	private GridBagConstraints gbc;
	private JFrame frame;
	private ArrayList<CommentPanel> panels = new ArrayList<CommentPanel>();
	ArrayList<CommentPanel> allPanels= new ArrayList<CommentPanel>();
}
