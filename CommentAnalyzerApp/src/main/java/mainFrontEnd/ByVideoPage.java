package mainFrontEnd;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fasterxml.jackson.core.JsonParseException;

import api.Retriever;
import api.VideoRetriever;
import business.Video1;

public class ByVideoPage extends SearchByPage{
	public ByVideoPage(JFrame frame,TaskBar bar) {
		super(frame,bar);
	}
	public ByVideoPage(JFrame frame,TaskBar bar, String channelName) {
		super(frame,bar);
		this.channelName = channelName;
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

	@Override
	protected void setInitialContent() {
		if(channelName == null) 
			createJTextFields();
		else {
			try {
				retrieverInput = retriever.retrieveFromChannel(channelName);
				createPanels(retrieverInput);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	@Override
	protected JLabel getTitle() {
		JLabel label;
		if(channelName ==null) {
			label = new JLabel("Video Selection Page");
		}
		else {
			label = new JLabel(channelName+"\tvideos");
		}
		label.setHorizontalAlignment(JLabel.CENTER);
		return label;
	}
	private VideoListPanel panel;
	private String channelName;

}
