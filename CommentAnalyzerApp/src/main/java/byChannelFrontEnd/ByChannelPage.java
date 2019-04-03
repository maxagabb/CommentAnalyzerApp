package byChannelFrontEnd;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fasterxml.jackson.core.JsonParseException;

import api.ChannelRetriever;
import api.Retriever;
import api.VideoRetriever;
import business.ContentListPanel;
import business.Video1;
import byVideoFrontEnd.SearchByPage;
import byVideoFrontEnd.TaskBar;
import byVideoFrontEnd.VideoListPanel;

public class ByChannelPage extends SearchByPage{
	public ByChannelPage(JFrame frame,TaskBar bar) {
		super(frame,bar);
	}
	@Override
	protected Retriever createRetriever() {
		// TODO Auto-generated method stub
		return new ChannelRetriever();
	}

	@Override
	protected void setInitialContent() {
		if(channelName == null) 
			createJTextFields();
	}
	@Override
	protected JLabel getTitle() {
		JLabel label = new JLabel("Channel Selection Page");
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
			this.panel = new ChannelListPanel(frame);
	}
	private String channelName;
}

