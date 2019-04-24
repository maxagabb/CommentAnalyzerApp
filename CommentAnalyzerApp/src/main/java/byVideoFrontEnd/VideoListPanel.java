package byVideoFrontEnd;

import business.Content;
import business.ContentListPanel;
import business.ContentPanel;
import business.Video1;
import commentsFrontEnd.CommentPage;
import javafx.stage.Stage;

public class VideoListPanel extends ContentListPanel{
	public VideoListPanel(Stage stage) {
		super(stage);
	}

        @Override
	public void emptyList() {
		panels.removeAll(panels);
	}

	@Override
	public void addPanel(Content content) {
		panels.add(new VideoPanel((Video1) content));
	}

	@Override
	protected void makeSearchByPage(ContentPanel panel) {
		this.page = new CommentPage(stage, new TaskBar(stage),(VideoPanel)panel);
	}
}
