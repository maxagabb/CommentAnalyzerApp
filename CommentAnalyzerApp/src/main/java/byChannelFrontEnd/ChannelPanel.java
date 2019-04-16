package byChannelFrontEnd;

import business.ContentPanel;

public class ChannelPanel extends ContentPanel{
	public ChannelPanel(Channel1 content) {
		super(content);
	}
	public String getName() {
		return content.getName();
	}
	public String getChannelID() {
		return content.getID();
	}
	
}
