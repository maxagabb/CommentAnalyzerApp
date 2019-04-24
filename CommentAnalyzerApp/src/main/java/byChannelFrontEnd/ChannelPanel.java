package byChannelFrontEnd;

import business.ContentPanel;

public class ChannelPanel extends ContentPanel{
	public ChannelPanel(Channel1 content) {
		super(content);
	}
        /**
         * returns channelID of channel held in panel
         * @return content.getID()
         * @precondition none
         * @postcondition content.getID() != null
         */
	public String getChannelID() {
		return content.getID();
	}
	
}
