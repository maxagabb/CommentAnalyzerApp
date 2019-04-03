package byChannelFrontEnd;

import business.Content;

public class Channel extends Content{
	public Channel(String channel) {
		this.channel = channel;
	}
	public String getChannel() {
		return channel;
	}
	private String channel;
}
