package mainFrontEnd;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPage extends JPanel{

	public MainPage(JFrame frame, TaskBar bar) {
		this.bar = bar;
		this.frame = frame;
		setPage();
	}
	
	private void setPage() {
		this.setLayout(new BorderLayout());
		add(bar,BorderLayout.NORTH);
	}

	private JFrame frame;
	private TaskBar bar;
}
