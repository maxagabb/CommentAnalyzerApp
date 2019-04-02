package mainFrontEnd;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import analysisFrontEnd.CommentPage;

public class VideoListPanel extends JPanel{
	public VideoListPanel(JFrame frame) {
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
		for(VideoPanel panel: panels) {
			
			panel.setPanel();
			panel.setAlignmentX(LEFT_ALIGNMENT);
			panel.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					JFrame nextFrame = new JFrame();
					nextFrame.setBounds(frame.getX(), frame.getY(), 
							frame.getWidth(), frame.getHeight());
					frame.dispose();
					CommentPage page = new CommentPage(nextFrame, new TaskBar(nextFrame), panel.getVideoID());
					page.setPage();
					JScrollPane pane = new JScrollPane(page);
					nextFrame.add(pane);
					nextFrame.setVisible(true);
					nextFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {}
				@Override
				public void mouseExited(MouseEvent arg0) {}
				@Override
				public void mousePressed(MouseEvent arg0) {}
				@Override
				public void mouseReleased(MouseEvent arg0) {}
			});
			this.add(panel, gbc);
			gbc.gridy++;
		}
	}
	public void addPanel(VideoPanel videoPanel) {
		panels.add(videoPanel);
	}
	private JFrame frame;
	private ArrayList<VideoPanel> panels = new ArrayList<VideoPanel>();

}
