package loginRegister;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;


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
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 50, 20, 50);
		gbc.ipady = 10;
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
		fieldPanel.setLayout(new GridBagLayout());
		//gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1; 
		gbc.gridx = 1;
		gbc.gridy = 0;
		fieldPanel.add(makeInputPanel("username", nameField), gbc);
		gbc.gridy++;
		fieldPanel.add(makeInputPanel("password", passwordField), gbc);
		if (getEmailField()!=null) {
			gbc.gridy++;
			fieldPanel.add(makeInputPanel("Email", getEmailField()), gbc);}
		this.add(fieldPanel, BorderLayout.CENTER);
	}
	
	private JPanel makeInputPanel(String name, JTextField field) {
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbc2.weightx = 1; 
		gbc2.gridx = 1;
		gbc2.gridy = 0; 
		JLabel label = new JLabel(name);
		label.setHorizontalAlignment(JLabel.CENTER);
		JPanel result = new JPanel();
		result.setLayout(new GridBagLayout());
		result.add(label, gbc2);
		gbc2.gridy++;
		result.add(field, gbc2);
		result.setBorder(new EtchedBorder());
		return result;
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
	protected GridBagConstraints gbc;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setBounds(800, 400, 600, 400);
		frame.add(new WelcomePage(frame));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
