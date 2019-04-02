package loginRegister;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

import javax.swing.BorderFactory;
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
	public RegisterPage(JFrame frame ) {
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
			
			JFrame nextFrame = new JFrame();
			nextFrame.setBounds(frame.getX(), frame.getY(), 
					frame.getWidth(), frame.getHeight());
			frame.dispose();
			nextFrame.add(new LoginPage(nextFrame));
			nextFrame.setVisible(true);
			nextFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
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
		JLabel register = new JLabel("Register Screen");
		register.setHorizontalAlignment(JLabel.CENTER);
		this.add(register, BorderLayout.NORTH);
	}
	
	private String inputEmail;
}
