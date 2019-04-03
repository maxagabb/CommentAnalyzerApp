package business;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mainFrontEnd.TaskBar;
import mainFrontEnd.VideoPanel;

public abstract class ContentListPanel extends JPanel{
	
	public ContentListPanel(JFrame frame) {
		this.frame = frame;}
	public void emptyList() {};
	
	public abstract void setPanel();
	public abstract void addPanel(Content content);
	protected ArrayList panels = new ArrayList();
	protected JFrame frame;
	protected ContentPanel panel;

}
