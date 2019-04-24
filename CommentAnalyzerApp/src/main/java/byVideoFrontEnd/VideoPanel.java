package byVideoFrontEnd;

import business.ContentPanel;
import business.Video1;

public class VideoPanel extends ContentPanel{
	
	public VideoPanel(Video1 content) {
		super(content);
	}
        /**
         * returns videoID of video held in panel
         * @return content.getID
         * @precondition this.content != null
         * @postcondition returns videoID
         */
	public String getVideoID() {
		return content.getID();
	}
}