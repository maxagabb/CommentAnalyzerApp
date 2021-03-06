package business;


import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.SearchResult;

public class Video1 extends Content{

	public Video1(SearchResult result) {
		this.ID = result.getId().getVideoId();
		this.text = result.getSnippet().getTitle();
		this.thumbnailURL = result.getSnippet().getThumbnails().getDefault().getUrl();
	}
	public Video1(String error) {
		this.text = error;
		this.thumbnailURL = "iconfinderSignError299045.png";
	}
	public Video1(PlaylistItem item) {
		this.ID = item.getSnippet().getResourceId().getVideoId();
		this.text = item.getSnippet().getTitle();
		this.thumbnailURL = item.getSnippet().getThumbnails().getDefault().getUrl();
	}
        @Override
	public String getID() {
		return ID;
	}
        @Override
	public String getthumbnailURL() {
		return thumbnailURL;
	}
        @Override
	public String toString() {
            if (ID!=null)
		return ID + ", " + text;
            return "no videoID, " + text;
	}
	//private String qualityThumbnailURL;
	private String ID;
	private String thumbnailURL;
}
