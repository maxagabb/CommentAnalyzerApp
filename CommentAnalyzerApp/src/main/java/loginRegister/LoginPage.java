package loginRegister;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

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
			/*frame.dispose();
			JFrame frame = new JFrame();
			frame.add(new MainPage(frame));
			frame.setBounds(800, 400, 200, 200);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
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
