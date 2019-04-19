package byChannelFrontEnd;

import com.google.api.services.youtube.model.SearchResult;

import business.Content;

public class Channel1 extends Content{
	public Channel1(SearchResult result) {
		this.ID = result.getId().getChannelId();
		this.channel = result.getSnippet().getTitle();
		this.thumbnailURL = result.getSnippet().getThumbnails().getDefault().getUrl();
	}
	public Channel1 (String error) {
		this.channel = error;
		this.thumbnailURL = "iconfinderSignError299045.png";
	}
	public String getName() {
		return channel;
	}

	public String getID() {
		return ID;
	}
	public String getthumbnailURL() {
		return thumbnailURL;
	}
	private String channel;
	private String ID;
	private String thumbnailURL;
}
