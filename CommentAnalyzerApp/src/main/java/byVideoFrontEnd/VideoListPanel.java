package byVideoFrontEnd;

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

import business.Content;
import business.ContentListPanel;
import business.ContentPanel;
import business.Video1;
import commentsFrontEnd.CommentPage;
import javafx.stage.Stage;

public class VideoListPanel extends ContentListPanel{
	public VideoListPanel(Stage stage) {
		super(stage);
	}

	public void emptyList() {
		panels.removeAll(panels);
	}

	@Override
	public void addPanel(Content content) {
		panels.add(new VideoPanel((Video1) content));
	}

	@Override
	protected void makeSearchByPage(Stage stage, TaskBar taskBar, ContentPanel panel) {
		this.page = new CommentPage(stage, taskBar,(VideoPanel)panel);
	}
}
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