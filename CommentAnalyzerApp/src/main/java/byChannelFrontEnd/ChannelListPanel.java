package byChannelFrontEnd;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import analysisFrontEnd.CommentPage;
import business.Content;
import business.ContentListPanel;
import business.ContentPanel;
import byVideoFrontEnd.TaskBar;
import byVideoFrontEnd.VideoListPanel;
import byVideoFrontEnd.VideoPanel;

public class ChannelListPanel extends ContentListPanel implements Runnable{

	public ChannelListPanel(JFrame frame) {
		super(frame);
	}

	@SuppressWarnings("unchecked")
	public void setPanel(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		for(ContentPanel panel: (ArrayList<VideoPanel>)panels) {
			this.panel = panel;
			panel.setPanel();
			panel.setAlignmentX(LEFT_ALIGNMENT);
			ChannelListPanel self  = this;
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

	@Override
	public void addPanel(Content content) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void makeSearchByPage(JFrame frame2, TaskBar taskBar, String videoID) {
	}

}
