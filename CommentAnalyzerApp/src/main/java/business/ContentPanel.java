package business;

import javax.swing.JPanel;

public abstract class ContentPanel extends JPanel {
	public ContentPanel(Content content) {
		this.content = content;
	}
	
	public void setPanel() {}
	public String getVideoID() {return null;}
	protected Content content;
}
