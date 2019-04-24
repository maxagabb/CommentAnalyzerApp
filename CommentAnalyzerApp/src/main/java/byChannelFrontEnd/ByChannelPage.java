package byChannelFrontEnd;



import api.ChannelRetriever;
import business.ContentListPanel;
import byVideoFrontEnd.SearchByPage;
import byVideoFrontEnd.TaskBar;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ByChannelPage extends SearchByPage<Channel1>{
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
		panel.getChildren().add(label);
		return panel;
	}
	protected void addContentListPanel(ContentListPanel panel) {
		if(panel != null) {
			panel.emptyList();
		}
		else 
			this.panel = new ChannelListPanel(stage);
	}

	@Override
	protected void youtubeRetrieverSetup() {
		retriever = new ChannelRetriever();
	}
	//private String channelName;

}

