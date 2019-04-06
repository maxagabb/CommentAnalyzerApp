package byVideoFrontEnd;

import java.awt.BorderLayout;
import java.awt.Component;
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
import business.ContentPanel;
import business.Video1;
import byChannelFrontEnd.Channel1;
import byChannelFrontEnd.ChannelPanel;

public class ByVideoPage extends SearchByPage{
	public ByVideoPage(JFrame frame,TaskBar bar) {
		super(frame,bar);
		//this.panel = new VideoListPanel(frame);
	}
	public ByVideoPage(JFrame frame,TaskBar bar, ContentPanel panel) {
		super(frame,bar);
		this.channelName = panel.getName();
		this.channelID = panel.getChannelID();
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
				retrieverInput = retriever.retrieveFromChannel(channelID);
				addContentListPanel(panel);
				createPanels(retrieverInput, panel);
				this.revalidate();
				this.repaint();
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
			label = new JLabel(channelName);
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
	private String channelID;
}

