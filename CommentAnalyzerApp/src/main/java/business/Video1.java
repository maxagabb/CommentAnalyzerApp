package business;

import java.io.IOException;

import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

public class Video1 extends Content{

	public Video1(SearchResult result) {
		this.ID = result.getId().getVideoId();
		this.name = result.getSnippet().getTitle();
		this.thumbnailURL = result.getSnippet().getThumbnails().getDefault().getUrl();
	}
	public Video1(String error) {
		this.name = "Some Error:\t" + error;
		this.thumbnailURL = "iconfinder_sign-error_299045.png";
	}
	public Video1(PlaylistItem item) {
		try {
			System.out.print(item.toPrettyString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ID = item.getSnippet().getResourceId().getVideoId();
		this.name = item.getSnippet().getTitle();
		this.thumbnailURL = item.getSnippet().getThumbnails().getDefault().getUrl();
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
