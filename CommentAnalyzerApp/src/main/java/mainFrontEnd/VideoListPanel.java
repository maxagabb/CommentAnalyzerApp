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
import business.Content;
import business.ContentListPanel;
import business.ContentPanel;
import business.Video1;

public class VideoListPanel extends ContentListPanel implements Runnable{
	public VideoListPanel(JFrame frame) {
		super(frame);
	}

	public void emptyList() {
		panels.removeAll(panels);
	}

	@SuppressWarnings("unchecked")
	public void setPanel(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		/*
		ArrayList<Thread>threads = new ArrayList();
		panels.stream()
		.forEach(e  ->{
			Thread t = new Thread(()-> {
			this.panel = (ContentPanel) e;
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

		});
			threads.add(t);
			});

		threads.stream().forEach(Thread::run);*/

		for(ContentPanel panel: (ArrayList<VideoPanel>)panels) {
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
	@Override
	public void addPanel(Content content) {
		panels.add(new VideoPanel((Video1) content));
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
}
