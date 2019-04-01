package mainFrontEnd;

import java.awt.FlowLayout;
import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import loginRegister.LoginPage;
import loginRegister.WelcomePage;

public class TaskBar extends JPanel{
	public TaskBar(JFrame frame) {
		this.frame = frame;
		setBar();
	}

	private void setBar() {
		this.setLayout(new FlowLayout());
		ArrayList<String> buttonNames = new ArrayList<String>();
		buttonNames.add("Manage Favorites");buttonNames.add("By Video");
		buttonNames.add("By Channel");buttonNames.add("By Favorites");
		Stream<JButton> stream = buttonNames.stream().map( JButton::new);

		ArrayList<JButton> buttons = (ArrayList<JButton>) stream.collect(Collectors.toList());
		Iterator<JButton> iterator = buttons.iterator();
		while(iterator.hasNext()) {
			JButton button = iterator.next();
			button.addActionListener(e->{
				
				JFrame nextFrame = new JFrame();
				nextFrame.setBounds(frame.getX(), frame.getY(), 
						frame.getWidth(), frame.getHeight());
				frame.dispose();
				JScrollPane pane = new JScrollPane(getPage(button.getText(), nextFrame));
				nextFrame.add(pane);
				nextFrame.setVisible(true);
				nextFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			});
			this.add(button);
		}
	}
	private JPanel getPage(String name, JFrame frame) {
		if(name.equals("Manage Favorites"))
			return new WelcomePage(frame);
		else if(name.equals("By Video")) {
			ByVideoPage page = new ByVideoPage(frame, new TaskBar(frame));
			page.setPage();
			return page;
		}
		else return new JPanel();
		
		
	}
	private JFrame frame;
}
