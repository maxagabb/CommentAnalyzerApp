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
import business.ContentListPanel;
import business.Video1;

public class ByVideoPage extends SearchByPage{
	public ByVideoPage(JFrame frame,TaskBar bar) {
		super(frame,bar);
		//this.panel = new VideoListPanel(frame);
	}
	public ByVideoPage(JFrame frame,TaskBar bar, String channelName) {
		super(frame,bar);
		this.channelName = channelName;
		//this.panel = new VideoListPanel(frame);
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
				createPanels(retrieverInput, panel);
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
	protected void addContentListPanel(ContentListPanel panel) {
		if(panel != null) {
			panel.removeAll();
			panel.validate();
			panel.emptyList();
		}
		else 
			this.panel = new VideoListPanel(frame);
	}
	private String channelName;
}

