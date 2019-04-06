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
	protected void setInitialContent() {
			createJTextFields();
	}
	@Override
	protected JPanel getTitle() {
		JLabel label = new JLabel("Channel Selection Page");
		label.setHorizontalAlignment(JLabel.CENTER);
		JPanel panel = new JPanel();
		panel.add(label);
		return panel;
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
	
	@Override
	protected void youtubeRetrieverSetup() {
		retriever = new ChannelRetriever();
	}
	private String channelName;

}

