package commentsFrontEnd;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class CommentListPanel extends ContentListPanel{
	public CommentListPanel(Stage stage) {
		super(stage);
		//gbc = new GridBagConstraints();
		//gbc.insets = new Insets(20, 0, 20, 0);
		//gbc.ipady = 10;
		//this.frame = frame;
	}

	public void setPanel(){
		//this.setLayout(new GridBagLayout());
		//gbc.anchor = GridBagConstraints.WEST;
		//gbc.gridx = 0;
		//gbc.gridy = 0;
		
		this.setPadding(new Insets(25));
		for(CommentPanel panel: panels) {
			panel.setPanel();
			panel.setPadding(new Insets(20));
			//panel.setAlignmentX(LEFT_ALIGNMENT);
			this.getChildren().add(panel);
			
			//gbc.gridy++;
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
		this.getChildren().clear();
		//this.removeAll();
		//this.revalidate();
		//this.repaint();
		setPanel();
	}
	protected void makeSearchByPage(Stage stage, TaskBar taskBar, ContentPanel panel) {}
	private ArrayList<CommentPanel> panels = new ArrayList<CommentPanel>();
	ArrayList<CommentPanel> allPanels= new ArrayList<CommentPanel>();
}
