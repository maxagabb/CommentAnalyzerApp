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

public class VideoListPanel extends JPanel implements Runnable{
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
			this.panel = panel;
			panel.setPanel();
			panel.setAlignmentX(LEFT_ALIGNMENT);
			VideoListPanel self  = this;
			panel.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					Thread thread = new Thread(self);
					thread.start();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
	@Override
	public void run() {
		CommentPage page = new CommentPage(frame, new TaskBar(frame), panel.getVideoID());
		page.setPage();
		JScrollPane pane = new JScrollPane(page);
		frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();
		frame.add(pane);
		frame.repaint();
		
	}
	private VideoPanel panel;
	private JFrame frame;
	private ArrayList<VideoPanel> panels = new ArrayList<VideoPanel>();


}
