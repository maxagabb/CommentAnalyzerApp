package mainFrontEnd;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainPage extends JPanel{

	public MainPage(JFrame frame, TaskBar bar) {
		this.bar = bar;
		this.frame = frame;
		setPage();
	}
	
	private void setPage() {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 50, 0, 50));
		add(bar,BorderLayout.NORTH);
	}

	private JFrame frame;
	private TaskBar bar;
}
