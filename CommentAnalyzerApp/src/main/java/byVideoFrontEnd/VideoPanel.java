package byVideoFrontEnd;

import business.ContentPanel;
import business.Video1;

public class VideoPanel extends ContentPanel{
	
	public VideoPanel(Video1 content) {
		super(content);
	}
	public String getVideoID() {
		return content.getID();
	}
}