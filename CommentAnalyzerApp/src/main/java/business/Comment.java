package business;

public class Comment extends Content{
	public Comment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return comment;
	}
	
	private String comment;
}
