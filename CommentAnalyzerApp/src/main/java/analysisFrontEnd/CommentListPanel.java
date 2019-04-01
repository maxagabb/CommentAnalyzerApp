package analysisFrontEnd;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CommentListPanel extends JPanel{
	public CommentListPanel(JFrame frame) {
		this.frame = frame;
	}
	
	public void emptyList() {
		panels.removeAll(panels);
	}

	public void setPanel(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
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
	}
	private JFrame frame;
	private ArrayList<CommentPanel> panels = new ArrayList<CommentPanel>();

}
