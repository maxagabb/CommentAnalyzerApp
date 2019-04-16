package byChannelFrontEnd;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import business.Content;
import business.ContentListPanel;
import business.ContentPanel;
import business.Video1;
import byVideoFrontEnd.ByVideoPage;
import byVideoFrontEnd.TaskBar;
import byVideoFrontEnd.VideoListPanel;
import byVideoFrontEnd.VideoPanel;
import commentsFrontEnd.CommentPage;
import javafx.stage.Stage;

public class ChannelListPanel extends ContentListPanel{

	public ChannelListPanel(Stage stage) {
		super(stage);
	}
	
	public void emptyList() {
		panels.removeAll(panels);
	}

	@Override
	public void addPanel(Content content) {
		panels.add(new ChannelPanel((Channel1) content));
	}

	@Override
	protected void makeSearchByPage(ContentPanel panel) {
		this.page = new ByVideoPage(stage,new TaskBar(stage),(ChannelPanel)panel);
	}

}
