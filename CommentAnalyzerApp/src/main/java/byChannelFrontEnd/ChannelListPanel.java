package byChannelFrontEnd;


import business.Content;
import business.ContentListPanel;
import business.ContentPanel;
import byVideoFrontEnd.ByVideoPage;
import byVideoFrontEnd.TaskBar;
import javafx.stage.Stage;

public class ChannelListPanel extends ContentListPanel{

	public ChannelListPanel(Stage stage) {
		super(stage);
	}
	
        @Override
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
