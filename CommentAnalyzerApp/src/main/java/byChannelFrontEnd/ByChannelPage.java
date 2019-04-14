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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ByChannelPage extends SearchByPage{
	public ByChannelPage(Stage stage,TaskBar bar) {
		super(stage, bar);
	}

	@Override
	protected void setInitialContent() {
			createJTextFields();
	}
	@Override
	protected HBox getTitle() {
		HBox panel = new HBox();
		Text label = new Text("Channel Selection Page");
		//JLabel label = new JLabel("Channel Selection Page");
		panel.getChildren().add(label);
		return panel;
	}
	protected void addContentListPanel(ContentListPanel panel) {
		if(panel != null) {
			//panel.getChildren().clear();
			panel.emptyList();
		}
		else 
			this.panel = new ChannelListPanel(stage);
	}
	
	@Override
	protected void youtubeRetrieverSetup() {
		retriever = new ChannelRetriever();
	}
	private String channelName;

}

