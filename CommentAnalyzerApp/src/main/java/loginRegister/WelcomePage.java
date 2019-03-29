package loginRegister;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePage extends JPanel{
	/**
	 * @invariant this != null
	 * @precondition frame != null
	 * @postcondition this != null
	 * @param frame
	 */
	public WelcomePage(JFrame frame) {
		this.frame = frame;
		setPage();
	}
	/**
	 * sets buttons redirecting to login and 
	 * register pages
	 * @precondition this != null
	 * @postcondition frame = null
	 */
	private void setPage() {
		this.add(new JLabel("Welcome Screen"), BorderLayout.NORTH);
		JButton login = new JButton("login");
		login.addActionListener(e ->{
			frame.dispose();
			JFrame frame = new JFrame();
			frame.setBounds(800, 400, 200, 200);
			frame.add(new LoginPage(frame));
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		});
		JButton register = new JButton("register");
		register.addActionListener(e ->{
			frame.dispose();
			JFrame frame = new JFrame();
			frame.setBounds(800, 400, 200, 200);
			frame.add(new RegisterPage(frame));
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(login,BorderLayout.CENTER);
		buttonPanel.add(register,BorderLayout.CENTER);
		this.add(buttonPanel);
	}
	private JFrame frame;
}
