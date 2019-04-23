package byVideoFrontEnd;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



import com.fasterxml.jackson.core.JsonParseException;


import api.VideoRetriever;
import business.Content;
import business.ContentListPanel;
import business.Video1;
import byChannelFrontEnd.ChannelPanel;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ByVideoPage extends SearchByPage<Object, Video1>{
	public ByVideoPage(Stage stage,TaskBar bar) {
		super(stage,bar);
		//this.panel = new VideoListPanel(frame);
	}
	public ByVideoPage(Stage stage,TaskBar bar, ChannelPanel channelPanel) {
		super(stage,bar);
		this.channelName = channelPanel.getPanelText();
		this.channelID = channelPanel.getChannelID();
	}

	@Override
	protected void setInitialContent() {
		if(channelName == null) 
			createJTextFields();
		else {
			addContentListPanel(panel);
			createPanels((ArrayList<Content>) retrieverInput.get("videos"), panel);
		}
	}
	@Override
	protected HBox getTitle() {
		HBox panel = new HBox();
		if(channelName ==null) {
			Text nameLabel = new Text("Video Selection Page");
			panel.getChildren().add(nameLabel);
		}
		else {
			try {
				URL imageUrl = new URL((String) retrieverInput.get("bannerURL"));
				InputStream in = imageUrl.openStream();
				this.image = new Image(in);
				in.close();
				ImageView imageView = new ImageView(image);
				panel.getChildren().add(imageView);
			}
			catch(MalformedURLException e) {
				Text text = new Text(channelName);
				text.setId("title-text");
				panel.getChildren().add(text);
			}
			catch(IOException e) {
				Text errorText = new Text("A Problem occured when retrieving channel videos");
				errorText.setId("title-text");
				panel.getChildren().add(errorText);
			}
			panel.setPadding(new Insets(25));
			panel.setSpacing(25);
		}

		return panel;
	}
	protected void addContentListPanel(ContentListPanel panel) {
		if(panel != null) {
			panel.emptyList();
		}
		else 
			this.panel = new VideoListPanel(stage);
	}

	@Override
	protected void youtubeRetrieverSetup() {
		retriever = new VideoRetriever();
		if(channelName == null) {}
		else {
			try {
				retrieverInput = retriever.retrieveFromChannel(channelID);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private String channelName;
	private String channelID;

}

