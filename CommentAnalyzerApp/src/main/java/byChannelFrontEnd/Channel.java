package byChannelFrontEnd;

import business.Content;

public class Channel extends Content{
	public Channel(String channel) {
		this.channel = channel;
	}
	public String getName() {
		return channel;
	}
	private String channel;
}
