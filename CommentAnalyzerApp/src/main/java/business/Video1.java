package business;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

public class Video1 extends Content{

	public Video1(String ID, String name) {
		this.ID = ID;
		this.name = name;
	}
	public Video1(SearchResult result) {
		this.ID = result.getId().getVideoId();
		this.name = result.getSnippet().getTitle();
		this.thumbnailURL = result.getSnippet().getThumbnails().getDefault().getUrl();
	}
	public Video1(String error) {
		this.name = error;
		this.thumbnailURL = "iconfinder_sign-error_299045.png";
	}
	public String getID() {
		return ID;
	}
	public String getname() {
		return name;
	}
	public String getthumbnailURL() {
		return thumbnailURL;
	}
	public String toString() {
		return ID + " " + name;
	}
	
	private String ID;
	private String name;
	private String thumbnailURL;
}
