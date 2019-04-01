package business;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mainFrontEnd.TaskBar;
import mainFrontEnd.VideoPanel;

public abstract class ContentListPanel extends JPanel{
	public ContentListPanel(JFrame frame, TaskBar bar) {
		this.bar = bar;
		this.frame = frame;}
	
	public void addPanel(JPanel panel) {
		panels.add(panel);
	}
	
	
	private ArrayList panels = new ArrayList();
	private JFrame frame;
	private TaskBar bar;
}
