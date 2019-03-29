package loginRegister;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author mgabb2015
 */
public abstract class InputPage extends JPanel{

	protected abstract void setPageName();
	protected abstract void nextPage(UserList list);
	protected JTextField getEmailField() {return null;}

	/**
	 * Constructs InputPage by setting frame attribute
	 * and calling setPage()
	 * @precondition frame != null
	 * @postcondition this != null
	 * @param frame
	 */
	public InputPage(JFrame frame) {
		this.frame = frame;
		setPage();
	}
	/**
	 * setPage is template method, calling
	 * concrete method setPagename()
	 * @precondition none
	 * @postcondition this != null
	 */
	public void setPage() {
		this.setLayout(new BorderLayout());
		setPageName();
		setJTextFields();
		setSubmitButton();
	}
	/**
	 * sets name, password (and email) fields,
	 * getEmail() is implemented by concrete classes
	 * @precondition none
	 * @postcondition this != null
	 */
	protected void setJTextFields() {
		final JTextField nameField = new JTextField();
		nameField.addKeyListener(
				new KeyListener() {
					@Override
					public void keyPressed(KeyEvent e) {}
					@Override
					public void keyReleased(KeyEvent e) {
						inputName = nameField.getText();}
					@Override
					public void keyTyped(KeyEvent e) {}
				});
		final JTextField passwordField = new JTextField();
		passwordField.addKeyListener(
				new KeyListener() {
					@Override
					public void keyPressed(KeyEvent e) {}
					@Override
					public void keyReleased(KeyEvent e) {
						inputPassword = passwordField.getText();}
					@Override
					public void keyTyped(KeyEvent e) {}
				});

		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(3,2,15,15));

		fieldPanel.add(new JLabel("username"));
		fieldPanel.add(nameField);
		fieldPanel.add(new JLabel("password"));
		fieldPanel.add(passwordField);
		if (getEmailField()!=null) {
			fieldPanel.add(new JLabel("email"));
			fieldPanel.add(getEmailField());}
		this.add(fieldPanel, BorderLayout.CENTER);
	}
	/**
	 * sets the submit button and adds to the page,
	 * the listener calls nextPage(), a concrete method
	 * @precondition none
	 * @postcondition this != null
	 */
	protected void setSubmitButton() {
		JButton submit = new JButton("submit");
		submit.addActionListener(e ->{
			File userFile = new File("users.csv");
			UserList list = new UserList(userFile);
			nextPage(list);
		});
		this.add(submit,BorderLayout.SOUTH);
	}

	protected JFrame frame;
	protected String inputName;
	protected String inputPassword;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(800, 400, 200, 200);
		frame.add(new WelcomePage(frame));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
