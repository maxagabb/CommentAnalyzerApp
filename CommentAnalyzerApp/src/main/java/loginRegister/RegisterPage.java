package loginRegister;

import java.awt.BorderLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RegisterPage extends InputPage{

	/**
	 * concrete child of InputPage,
	 * implements nextPage,setPageName
	 * and getEmailField
	 * @param frame
	 * @invariant this != null
	 */
	public RegisterPage(JFrame frame) {
		super(frame);
	}

	@Override
	/**
	 * if all fields are set, write to userList file,
	 * dispose current frame and setup login page
	 * @precondition list != null
	 * @postcondition this.frame = null
	 */
	protected void nextPage(UserList list) {
		ArrayList<String> input = new ArrayList<String>();
		try {
			input.add(inputName);
			input.add(inputPassword);
			input.add(inputEmail);
			list.printToFile(input);
			frame.dispose();

			JFrame frame = new JFrame();
			frame.add(new LoginPage(frame));
			frame.setBounds(800, 400, 200, 200);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		catch(Exception E) {
			JOptionPane.showMessageDialog(frame, "invalid Input");
		}
	}

	@Override
	/**
	 * returns email text field attached to KeyListener
	 * @precondition none
	 * @postcondition returns emailField
	 */
	protected JTextField getEmailField() {
		final JTextField emailField = new JTextField();
		emailField.addKeyListener(
				new KeyListener() {
					@Override
					public void keyPressed(KeyEvent e) {}
					@Override
					public void keyReleased(KeyEvent e) {
						inputEmail = emailField.getText();}
					@Override
					public void keyTyped(KeyEvent e) {}
				});
		return emailField;
	}

	@Override
	/**
	 * Appends name JLabel to North of panel
	 * @precondition this != null
	 * @postcondition this != null
	 */
	protected void setPageName() {
		this.add(new JLabel("Register Screen"), BorderLayout.NORTH);
	}
	
	private String inputEmail;
}
