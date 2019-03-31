package mainFrontEnd;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import api.Retriever;
import api.Video1;
import api.VideoRetriever;

public class ByVideoPage extends SearchByPage{
	public ByVideoPage(JFrame frame,TaskBar bar) {
		super(frame,bar);
	}

	@Override
	protected void createPanels(ArrayList retrieverInput) {
		if(panel != null) {
		panel.removeAll();
		panel.validate();
		panel.emptyList();
		}
		else {
		panel = new VideoListPanel(frame);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		}
		for (Video1 video: (ArrayList<Video1>)retrieverInput) {
			panel.addPanel(new VideoPanel(video));
		}
		
		panel.setPanel();
		this.add(panel);
		
	}

	@Override
	protected Retriever createRetriever() {
		// TODO Auto-generated method stub
		return new VideoRetriever();
	}
	private VideoListPanel panel;
}
