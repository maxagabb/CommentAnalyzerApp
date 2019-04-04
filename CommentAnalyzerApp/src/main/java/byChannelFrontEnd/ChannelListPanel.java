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
import business.Video1;
import byVideoFrontEnd.ByVideoPage;
import byVideoFrontEnd.TaskBar;
import byVideoFrontEnd.VideoListPanel;
import byVideoFrontEnd.VideoPanel;

public class ChannelListPanel extends ContentListPanel{

	public ChannelListPanel(JFrame frame) {
		super(frame);
	}
	
	public void emptyList() {
		panels.removeAll(panels);
	}

	@Override
	public void addPanel(Content content) {
		panels.add(new ChannelPanel((Channel1) content));
	}

	@Override
	protected void makeSearchByPage(JFrame frame2, TaskBar taskBar, String videoID, String channelID) {
		System.out.print(channelID + "\n\n\n\n");
		this.page = new ByVideoPage(frame2,taskBar,channelID);
	}

}
