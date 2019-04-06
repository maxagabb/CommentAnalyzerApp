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

import javax.swing.Box;
import javax.swing.BoxLayout;
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
		fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.PAGE_AXIS));
		fieldPanel.setBorder(new EtchedBorder());
		fieldPanel.add(Box.createRigidArea(new Dimension(180,20)));
		fieldPanel.add(makeInputPanel("username", nameField));
		fieldPanel.add(makeInputPanel("password", passwordField));
		if (getEmailField()!=null) {
			fieldPanel.add(makeInputPanel("Email", getEmailField()));}
		JPanel fieldPanel2 = new JPanel();
		fieldPanel2.add(fieldPanel);
		JPanel fieldPanel3 = new JPanel();
		fieldPanel3.setLayout(new GridBagLayout());
		fieldPanel3.add(fieldPanel2);
		this.add(fieldPanel3);
	}
	
	private JPanel makeInputPanel(String name, JTextField field) {
		field.setColumns(12);
		JPanel result = new JPanel();
		JLabel label = new JLabel(name);
		JPanel fieldPanel = new JPanel();
		fieldPanel.add(field);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setAlignmentX(CENTER_ALIGNMENT);
		
		result.setLayout(new BoxLayout(result, BoxLayout.PAGE_AXIS));
		result.add(label);
		result.add(fieldPanel);
		result.add(Box.createRigidArea(new Dimension(180,20)));
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
	
	public static void main(String[] args) {
		/*
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setBounds(800, 400, 1200, 800);
		frame.add(new WelcomePage(frame));
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
	}

}
