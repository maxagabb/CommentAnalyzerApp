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
		this.qualityThumbnailURL = result.getSnippet().getThumbnails().getHigh().getUrl();
	}
	public Video1(String error) {
		this.name = error;
		this.thumbnailURL = "iconfinderSignError299045.png";
	}
	public Video1(PlaylistItem item) {
		this.ID = item.getSnippet().getResourceId().getVideoId();
		this.name = item.getSnippet().getTitle();
		this.thumbnailURL = item.getSnippet().getThumbnails().getDefault().getUrl();
	}
	public String getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public String getthumbnailURL() {
		return thumbnailURL;
	}
	public String getQualityThumbnail() {
		return qualityThumbnailURL;
	}
	public String toString() {
		return ID + " " + name;
	}
	private String qualityThumbnailURL;
	private String ID;
	private String name;
	private String thumbnailURL;
}
