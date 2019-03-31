package loginRegister;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import mainFrontEnd.MainPage;
import mainFrontEnd.TaskBar;

public class LoginPage extends InputPage{

	/**
	 * concrete child of InputPage,
	 * implements nextPage and setPageName
	 * @param frame
	 * @invariant this != null
	 */
	public LoginPage(JFrame frame) {
		super(frame);
	}

	@Override
	/**
	 * if user exists, dispose current
	 * frame and setup the main page
	 * @precondition list != null
	 * @postcondition this.frame = null
	 */
	protected void nextPage(UserList list) {
		ArrayList<String> input = new ArrayList<String>();
		input.add(inputName);
		input.add(inputPassword);
		if(list.validate(input)) {
			System.out.print("success");
			
			JFrame nextFrame = new JFrame();
			nextFrame.setBounds(frame.getX(), frame.getY(), 
					frame.getWidth(), frame.getHeight());
			frame.dispose();
			JScrollPane pane = new JScrollPane(new MainPage(nextFrame,
					new TaskBar(nextFrame)));
			nextFrame.add(pane);
			nextFrame.setVisible(true);
			nextFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		
		else
			JOptionPane.showMessageDialog(frame, "Login failed");
	}

	@Override
	/**
	 * Appends name JLabel to North of panel
	 * @precondition this != null
	 * @postcondition this != null
	 */
	protected void setPageName() {
		JLabel login = new JLabel("Login Screen");
		login.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		login.setHorizontalAlignment(JLabel.CENTER);
		this.add(login, BorderLayout.NORTH);
	}
}
